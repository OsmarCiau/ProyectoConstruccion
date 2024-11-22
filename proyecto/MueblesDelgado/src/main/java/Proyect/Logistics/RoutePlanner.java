package Proyect.Logistics;

import Proyect.StoreKeeper.Order;
import Proyect.StoreKeeper.Platform;
import Proyect.StoreKeeper.StoreKeeper;

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
    private StoreKeeper storeKeeper;

    @Autowired
    public RoutePlanner(@Value("${logistics.warehouseLocation}") String warehouseLocation,
                        @Value("${logistics.startTime}") String startTime) {
        this.warehouseLocation = warehouseLocation;
        this.startTime = LocalTime.parse(startTime);  // Convertir el String en LocalTime
        this.geoDataProvider = new GeoDataProvider();
        this.storeKeeper = new StoreKeeper();
    }
    // Devolver lista de rutas por camión
    public List<Route> planOptimalRoutes(List<Order> p_orders, int p_numberOfTrucks) throws Exception {
        List<Route> allRoutes = new ArrayList<>();
        PriorityQueue<Order> orderQueue = initializeOrderQueue(p_orders);

        LocalDate currentDate = LocalDate.now();
        while (!orderQueue.isEmpty()) {
            List<Route> dailyRoutes = planDailyRoutes(orderQueue, currentDate, p_numberOfTrucks);
            if (!dailyRoutes.isEmpty()) {
                allRoutes.addAll(dailyRoutes); // Agregar todas las rutas del día
                currentDate = moveToNextWorkday(currentDate); // Pasamos al siguiente día laboral
            }
        }
        return allRoutes;
    }

    private PriorityQueue<Order> initializeOrderQueue(List<Order> p_orders) {
        PriorityQueue<Order> orderQueue = new PriorityQueue<>(Comparator.comparing(Order::getDeliveryDate));
        orderQueue.addAll(p_orders);
        return orderQueue;
    }

    private List<Route> planDailyRoutes(PriorityQueue<Order> p_orderQueue, LocalDate p_currentDate, int p_numberOfTrucks) throws Exception {
        List<Route> dailyRoutes = new ArrayList<>();
        List<Order> ordersForToday = new ArrayList<>();
        LocalTime estimatedTime = startTime;
        float totalDistance = 0.0f;
        int truckCounter = 0; // Para distribuir las rutas entre los camiones

        // Asignar las rutas entre los camiones
        while (!p_orderQueue.isEmpty()) {
            Order currentOrder = p_orderQueue.poll();

            if (isOutsideYucatan(currentOrder.getDestination())) {
                dailyRoutes.add(createSpecialRoute(currentOrder));
                continue;
            }

            if (!isOrderDeliverableInTime(currentOrder, p_currentDate)) {
                continue;
            }

            float distance = calculateDistance(currentOrder);
            Duration totalOrderDuration = calculateOrderDuration(currentOrder);
            LocalTime timeIncludingReturn = calculateTotalTimeWithReturn(currentOrder, estimatedTime, totalOrderDuration);

            if (exceedsWorkHours(timeIncludingReturn)) {
                p_orderQueue.add(currentOrder);  // Si se exceden las horas, dejamos la orden para el siguiente ciclo
                break;
            }

            // Asignamos la orden al camión actual
            ordersForToday.add(currentOrder);
            totalDistance += distance;
            estimatedTime = timeIncludingReturn.minus(Duration.ofMinutes((long) geoDataProvider.calculateDurationBetweenTwoPoints(
                    geoDataProvider.getCoordinatesFromAddress(currentOrder.getDestination()),
                    geoDataProvider.getCoordinatesFromAddress(warehouseLocation))));

            // Cuando un camión alcanza su capacidad máxima de entregas, se asignan las rutas al siguiente camión
            if (ordersForToday.size() == (p_orderQueue.size() / p_numberOfTrucks) + 1) {
                Route route = createRoute(ordersForToday, totalDistance, estimatedTime);
                dailyRoutes.add(route);

                // Aquí es donde liberamos el espacio en el almacén cuando la ruta es programada
                releaseStorageSpace(route);

                truckCounter++;
                ordersForToday.clear();  // Limpiamos las rutas para el siguiente camión
            }
        }

        return dailyRoutes;
    }

    // Método para liberar el espacio de almacenamiento cuando se programa la ruta
    private void releaseStorageSpace(Route p_route) {
        for (Order order : p_route.getOrders()) {
            for (Platform platform : order.getPlatforms()) {
                storeKeeper.retirePlatformFromCell(platform);
            }
        }
    }

    private boolean isOrderDeliverableInTime(Order p_order, LocalDate p_currentDate) {
        LocalDate deliveryDeadline = p_currentDate.plusDays(MAX_WORK_DAYS);
        return !p_order.getDeliveryDate().isAfter(deliveryDeadline);
    }

    private boolean isOutsideYucatan(String p_destination) {
        return !p_destination.toLowerCase().contains("yucatán");
    }

    private Route createSpecialRoute(Order p_order) throws Exception {
        float distance = calculateDistance(p_order);
        Duration totalOrderDuration = calculateOrderDuration(p_order);
        LocalTime estimatedTime = startTime.plus(totalOrderDuration);

        return new Route(
                warehouseLocation,
                List.of(p_order.getDestination()),
                List.of(totalOrderDuration),
                distance,
                estimatedTime
        );
    }

    private Route createRoute(List<Order> p_orders, float p_totalDistance, LocalTime p_estimatedTime) {
        List<String> destinations = new ArrayList<>();
        List<Duration> travelTimes = new ArrayList<>();

        for (Order order : p_orders) {
            destinations.add(order.getDestination());
            travelTimes.add(order.getTotalAssemblyTime());
        }

        return new Route(warehouseLocation, destinations, travelTimes, p_totalDistance, p_estimatedTime);
    }

    private float calculateDistance(Order p_order) throws Exception {
        double[] originCoordinates = geoDataProvider.getCoordinatesFromAddress(warehouseLocation);
        double[] destinationCoordinates = geoDataProvider.getCoordinatesFromAddress(p_order.getDestination());
        return (float) geoDataProvider.calculateDistanceBetweenTwoPoints(originCoordinates, destinationCoordinates);
    }

    private Duration calculateOrderDuration(Order p_order) throws Exception {
        double[] originCoordinates = geoDataProvider.getCoordinatesFromAddress(warehouseLocation);
        double[] destinationCoordinates = geoDataProvider.getCoordinatesFromAddress(p_order.getDestination());
        double travelTime = geoDataProvider.calculateDurationBetweenTwoPoints(originCoordinates, destinationCoordinates);
        return Duration.ofMinutes((long) travelTime).plus(p_order.getTotalAssemblyTime());
    }

    private LocalTime calculateTotalTimeWithReturn(Order p_order, LocalTime p_estimatedTime, Duration p_orderDuration) throws Exception {
        double[] destinationCoordinates = geoDataProvider.getCoordinatesFromAddress(p_order.getDestination());
        double[] originCoordinates = geoDataProvider.getCoordinatesFromAddress(warehouseLocation);
        double returnDuration = geoDataProvider.calculateDurationBetweenTwoPoints(destinationCoordinates, originCoordinates);
        return p_estimatedTime.plus(p_orderDuration).plusMinutes((long) returnDuration);
    }

    private boolean exceedsWorkHours(LocalTime p_timeIncludingReturn) {
        return Duration.between(startTime, p_timeIncludingReturn).compareTo(Duration.ofHours(MAX_HOURS_PER_DAY)) > 0;
    }

    private LocalDate moveToNextWorkday(LocalDate p_currentDate) {
        p_currentDate = p_currentDate.plusDays(1);
        if (p_currentDate.getDayOfWeek().getValue() > 5) {
            p_currentDate = p_currentDate.plusDays(8 - p_currentDate.getDayOfWeek().getValue());
        }
        return p_currentDate;
    }
}
