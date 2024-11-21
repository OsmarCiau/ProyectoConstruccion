package Proyect.Logistics;

import Proyect.StoreKeeper.Order;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;


@Service
public class RoutePlanner {
    private String warehouseLocation;
    private LocalTime startTime;
    private GeoDataProvider geoDataProvider;
    private final int MAX_WORK_DAYS = 3;
    private final long MAX_HOURS_PER_DAY = 3;

    @Autowired
    public RoutePlanner(@Value("${logistics.warehouseLocation}") String warehouseLocation,
                        @Value("${logistics.startTime}") String startTime) {
        this.warehouseLocation = warehouseLocation;
        this.startTime = LocalTime.parse(startTime);  // Convertir el String en LocalTime
        this.geoDataProvider = new GeoDataProvider();

    }
    // Devolver lista de rutas por camión
    public List<Route> planOptimalRoutes(List<Order> orders, int numberOfTrucks) throws Exception {
        List<Route> allRoutes = new ArrayList<>();
        PriorityQueue<Order> orderQueue = initializeOrderQueue(orders);

        LocalDate currentDate = LocalDate.now();
        while (!orderQueue.isEmpty()) {
            List<Route> dailyRoutes = planDailyRoutes(orderQueue, currentDate, numberOfTrucks);
            if (!dailyRoutes.isEmpty()) {
                allRoutes.addAll(dailyRoutes); // Agregar todas las rutas del día
                currentDate = moveToNextWorkday(currentDate); // Pasamos al siguiente día laboral
            }
        }
        return allRoutes;
    }

    private PriorityQueue<Order> initializeOrderQueue(List<Order> orders) {
        PriorityQueue<Order> orderQueue = new PriorityQueue<>(Comparator.comparing(Order::getDeliveryDate));
        orderQueue.addAll(orders);
        return orderQueue;
    }

    private List<Route> planDailyRoutes(PriorityQueue<Order> orderQueue, LocalDate currentDate, int numberOfTrucks) throws Exception {
        List<Route> dailyRoutes = new ArrayList<>();
        List<Order> ordersForToday = new ArrayList<>();
        LocalTime estimatedTime = startTime;
        float totalDistance = 0.0f;
        int truckCounter = 0; // Para distribuir las rutas entre los camiones

        // Asignar las rutas entre los camiones
        while (!orderQueue.isEmpty()) {
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
                orderQueue.add(currentOrder);  // Si se exceden las horas, dejamos la orden para el siguiente ciclo
                break;
            }

            // Asignamos la orden al camión actual
            ordersForToday.add(currentOrder);
            totalDistance += distance;
            estimatedTime = timeIncludingReturn.minus(Duration.ofMinutes((long) geoDataProvider.calculateDurationBetweenTwoPoints(
                    geoDataProvider.getCoordinatesFromAddress(currentOrder.getDestination()),
                    geoDataProvider.getCoordinatesFromAddress(warehouseLocation))));

            // Cuando un camión alcanza su capacidad máxima de entregas, se asignan las rutas al siguiente camión
            if (ordersForToday.size() == (orderQueue.size() / numberOfTrucks) + 1) {
                dailyRoutes.add(createRoute(ordersForToday, totalDistance, estimatedTime));
                truckCounter++;
                ordersForToday.clear();  // Limpiamos las rutas para el siguiente camión
            }
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

        return new Route(warehouseLocation, destinations, travelTimes, totalDistance, estimatedTime);
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
