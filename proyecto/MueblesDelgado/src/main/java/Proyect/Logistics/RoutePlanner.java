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
    private String warehouseLocation = null;
    private LocalTime startTime = LocalTime.of(8, 0); // Jornada laboral inicia a las 8:00 AM
    private GeoDataProvider geoDataProvider = new GeoDataProvider();
    private final int MAX_WORK_DAYS = 3; // Máximo 3 días hábiles para entrega
    private final long MAX_HOURS_PER_DAY = 3; // Máximo 6 horas de trabajo por día
    private int routeDayCounter = 1; // Contador para diferenciar las rutas por día

    public RoutePlanner(String p_wareHouseLocation, LocalTime p_startTime) {
        setWarehouseLocation(p_wareHouseLocation);
        setStartTime(p_startTime);
    }

    private void setWarehouseLocation(String p_warehouseLocation) {
        this.warehouseLocation = p_warehouseLocation;
    }

    private void setStartTime(LocalTime p_startTime) {
        this.startTime = p_startTime;
    }

    public List<List<Route>> planOptimalRoutes(List<Order> p_orders) throws Exception {
        List<List<Route>> allRoutes = new ArrayList<>(); // Lista que contendrá las rutas por día
        List<Route> currentDayRoutes = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        PriorityQueue<Order> orderQueue = new PriorityQueue<>(Comparator.comparing(Order::getDeliveryDate));
        orderQueue.addAll(p_orders);

        while (!orderQueue.isEmpty()) {
            List<String> destinations = new ArrayList<>();
            List<Duration> travelTimes = new ArrayList<>();
            float totalDistance = 0.0f;
            LocalTime estimatedTime = startTime;
            List<Order> ordersForToday = new ArrayList<>();

            while (!orderQueue.isEmpty() && ordersForToday.size() < 5) {
                Order currentOrder = orderQueue.poll();

                // Verificar si el pedido es fuera de Yucatán
                if (isOutsideYucatan(currentOrder.getDestination())) {
                    Route specialRoute = createSpecialRoute(currentOrder);
                    allRoutes.add(List.of(specialRoute));
                    continue;
                }

                if (!isOrderDeliveredInTime(currentOrder, currentDate)) {
                    continue;
                }

                double[] originCoordinates = geoDataProvider.getCoordinatesFromAddress(warehouseLocation);
                double[] destinationCoordinates = geoDataProvider.getCoordinatesFromAddress(currentOrder.getDestination());
                double distance = geoDataProvider.calculateDistanceBetweenTwoPoints(originCoordinates, destinationCoordinates);
                double duration = geoDataProvider.calculateDurationBetweenTwoPoints(originCoordinates, destinationCoordinates);

                Duration assemblyTime = currentOrder.getTotalAssemblyTime();
                Duration totalOrderDuration = Duration.ofMinutes((long) duration).plus(assemblyTime);

                // Calcular tiempo de regreso al almacén
                double returnDuration = geoDataProvider.calculateDurationBetweenTwoPoints(destinationCoordinates, originCoordinates);
                LocalTime totalTimeIncludingReturn = estimatedTime.plus(totalOrderDuration).plusMinutes((long) returnDuration);

                // Verificar si excede el límite de horas de trabajo, considerando el regreso
                if (Duration.between(startTime, totalTimeIncludingReturn).compareTo(Duration.ofHours(MAX_HOURS_PER_DAY)) > 0) {
                    // Devolver el pedido a la cola y finalizar el día
                    orderQueue.add(currentOrder);
                    break;
                }

                destinations.add(currentOrder.getDestination());
                travelTimes.add(totalOrderDuration);
                totalDistance += distance;

                estimatedTime = estimatedTime.plus(totalOrderDuration);

                ordersForToday.add(currentOrder);
            }

            if (!ordersForToday.isEmpty()) {
                Route route = new Route(routeDayCounter, warehouseLocation, destinations, travelTimes, totalDistance, estimatedTime);
                currentDayRoutes.add(route);
            }

            // Si terminamos con las rutas de hoy, pasamos al siguiente día
            if (orderQueue.isEmpty() || ordersForToday.size() >= 5) {
                allRoutes.add(new ArrayList<>(currentDayRoutes));
                currentDayRoutes.clear();
                routeDayCounter++;
                currentDate = moveToNextWorkday(currentDate);
                estimatedTime = startTime;
            }
        }

        // Agregar las rutas del último día si quedan
        if (!currentDayRoutes.isEmpty()) {
            allRoutes.add(new ArrayList<>(currentDayRoutes));
        }

        return allRoutes;
    }

    // Métodos de ayuda
    private boolean isOrderDeliveredInTime(Order order, LocalDate currentDate) {
        LocalDate deliveryDeadline = currentDate.plusDays(MAX_WORK_DAYS);
        return !order.getDeliveryDate().isAfter(deliveryDeadline);
    }

    private boolean isOutsideYucatan(String destination) {
        return !destination.toLowerCase().contains("yucatán");
    }

    private Route createSpecialRoute(Order order) throws Exception {
        double[] originCoordinates = geoDataProvider.getCoordinatesFromAddress(warehouseLocation);
        double[] destinationCoordinates = geoDataProvider.getCoordinatesFromAddress(order.getDestination());
        double distance = geoDataProvider.calculateDistanceBetweenTwoPoints(originCoordinates, destinationCoordinates);
        double duration = geoDataProvider.calculateDurationBetweenTwoPoints(originCoordinates, destinationCoordinates);
        Duration assemblyTime = order.getTotalAssemblyTime();

        List<String> destinations = List.of(order.getDestination());
        List<Duration> travelTimes = List.of(Duration.ofMinutes((long) duration).plus(assemblyTime));

        return new Route(routeDayCounter++, warehouseLocation, destinations, travelTimes, (float) distance, startTime.plusMinutes((long) (duration + assemblyTime.toMinutes())));
    }

    private LocalDate moveToNextWorkday(LocalDate currentDate) {
        currentDate = currentDate.plusDays(1);
        if (currentDate.getDayOfWeek().getValue() > 5) {
            currentDate = currentDate.plusDays(8 - currentDate.getDayOfWeek().getValue());
        }
        return currentDate;
    }
}
