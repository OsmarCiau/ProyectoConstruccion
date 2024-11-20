package Proyect.Logistics;

import Proyect.StoreKeeper.Order;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class RoutePlanner {
    private String warehouseLocation;
    private LocalTime startTime;
    private GeoDataProvider geoDataProvider;
    private final int MAX_WORK_DAYS = 3;
    private final long MAX_HOURS_PER_DAY = 3;
    private int routeDayCounter = 1;

    public RoutePlanner(String warehouseLocation, LocalTime startTime) {
        this.warehouseLocation = warehouseLocation;
        this.startTime = startTime;
        this.geoDataProvider = new GeoDataProvider();
    }

    public List<List<Route>> planOptimalRoutes(List<Order> orders) throws Exception {
        List<List<Route>> allRoutes = new ArrayList<>();
        PriorityQueue<Order> orderQueue = initializeOrderQueue(orders);

        LocalDate currentDate = LocalDate.now();
        while (!orderQueue.isEmpty()) {
            List<Route> dailyRoutes = planDailyRoutes(orderQueue, currentDate);
            if (!dailyRoutes.isEmpty()) {
                allRoutes.add(dailyRoutes);
                routeDayCounter++;
                currentDate = moveToNextWorkday(currentDate);
            }
        }
        return allRoutes;
    }

    private PriorityQueue<Order> initializeOrderQueue(List<Order> orders) {
        PriorityQueue<Order> orderQueue = new PriorityQueue<>(Comparator.comparing(Order::getDeliveryDate));
        orderQueue.addAll(orders);
        return orderQueue;
    }

    private List<Route> planDailyRoutes(PriorityQueue<Order> orderQueue, LocalDate currentDate) throws Exception {
        List<Route> dailyRoutes = new ArrayList<>();
        List<Order> ordersForToday = new ArrayList<>();
        LocalTime estimatedTime = startTime;
        float totalDistance = 0.0f;

        while (!orderQueue.isEmpty() && ordersForToday.size() < 5) {
            Order currentOrder = orderQueue.poll();

            if (isOutsideYucatan(currentOrder.getDestination())) {
                dailyRoutes.add(createSpecialRoute(currentOrder));
                continue;
            }

            if (!isOrderDeliverableInTime(currentOrder, currentDate)) {
                continue;
            }

            float distance = calculateDistance(currentOrder);
            Duration totalOrderDuration = calculateOrderDuration(currentOrder);
            LocalTime timeIncludingReturn = calculateTotalTimeWithReturn(currentOrder, estimatedTime, totalOrderDuration);

            if (exceedsWorkHours(timeIncludingReturn)) {
                orderQueue.add(currentOrder);
                break;
            }

            ordersForToday.add(currentOrder);
            totalDistance += distance;
            estimatedTime = timeIncludingReturn.minus(Duration.ofMinutes((long) geoDataProvider.calculateDurationBetweenTwoPoints(
                    geoDataProvider.getCoordinatesFromAddress(currentOrder.getDestination()),
                    geoDataProvider.getCoordinatesFromAddress(warehouseLocation))));
        }

        if (!ordersForToday.isEmpty()) {
            dailyRoutes.add(createRoute(ordersForToday, totalDistance, estimatedTime));
        }
        return dailyRoutes;
    }

    private boolean isOrderDeliverableInTime(Order order, LocalDate currentDate) {
        LocalDate deliveryDeadline = currentDate.plusDays(MAX_WORK_DAYS);
        return !order.getDeliveryDate().isAfter(deliveryDeadline);
    }

    private boolean isOutsideYucatan(String destination) {
        return !destination.toLowerCase().contains("yucatán");
    }

    private Route createSpecialRoute(Order order) throws Exception {
        float distance = calculateDistance(order);
        Duration totalOrderDuration = calculateOrderDuration(order);
        LocalTime estimatedTime = startTime.plus(totalOrderDuration);

        return new Route(
                routeDayCounter++,
                warehouseLocation,
                List.of(order.getDestination()),
                List.of(totalOrderDuration),
                distance,
                estimatedTime
        );
    }

    private Route createRoute(List<Order> orders, float totalDistance, LocalTime estimatedTime) {
        List<String> destinations = new ArrayList<>();
        List<Duration> travelTimes = new ArrayList<>();

        for (Order order : orders) {
            destinations.add(order.getDestination());
            travelTimes.add(order.getTotalAssemblyTime());
        }

        return new Route(routeDayCounter, warehouseLocation, destinations, travelTimes, totalDistance, estimatedTime);
    }

    private float calculateDistance(Order order) throws Exception {
        double[] originCoordinates = geoDataProvider.getCoordinatesFromAddress(warehouseLocation);
        double[] destinationCoordinates = geoDataProvider.getCoordinatesFromAddress(order.getDestination());
        return (float) geoDataProvider.calculateDistanceBetweenTwoPoints(originCoordinates, destinationCoordinates);
    }

    private Duration calculateOrderDuration(Order order) throws Exception {
        double[] originCoordinates = geoDataProvider.getCoordinatesFromAddress(warehouseLocation);
        double[] destinationCoordinates = geoDataProvider.getCoordinatesFromAddress(order.getDestination());
        double travelTime = geoDataProvider.calculateDurationBetweenTwoPoints(originCoordinates, destinationCoordinates);
        return Duration.ofMinutes((long) travelTime).plus(order.getTotalAssemblyTime());
    }

    private LocalTime calculateTotalTimeWithReturn(Order order, LocalTime estimatedTime, Duration orderDuration) throws Exception {
        double[] destinationCoordinates = geoDataProvider.getCoordinatesFromAddress(order.getDestination());
        double[] originCoordinates = geoDataProvider.getCoordinatesFromAddress(warehouseLocation);
        double returnDuration = geoDataProvider.calculateDurationBetweenTwoPoints(destinationCoordinates, originCoordinates);
        return estimatedTime.plus(orderDuration).plusMinutes((long) returnDuration);
    }

    private boolean exceedsWorkHours(LocalTime timeIncludingReturn) {
        return Duration.between(startTime, timeIncludingReturn).compareTo(Duration.ofHours(MAX_HOURS_PER_DAY)) > 0;
    }

    private LocalDate moveToNextWorkday(LocalDate currentDate) {
        currentDate = currentDate.plusDays(1);
        if (currentDate.getDayOfWeek().getValue() > 5) {
            currentDate = currentDate.plusDays(8 - currentDate.getDayOfWeek().getValue());
        }
        return currentDate;
    }
}
