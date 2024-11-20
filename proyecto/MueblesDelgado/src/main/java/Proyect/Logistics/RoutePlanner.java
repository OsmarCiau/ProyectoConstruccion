package Proyect.Logistics;

import Proyect.StoreKeeper.Order;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.time.LocalDate;
import java.time.LocalTime;

public class RoutePlanner {
    private String warehouseLocation = null;
    private LocalTime startTime = LocalTime.of(0, 0);
    private GeoDataProvider geoDataProvider = new GeoDataProvider();
    private final LocalTime WORKDAY_END_TIME = LocalTime.of(18, 0);  // Fin de jornada laboral (8 horas)
    private final int MAX_WORK_DAYS = 3; // Máximo 3 días hábiles para entrega
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
        PriorityQueue<Order> orderQueue = new PriorityQueue<>(Comparator.comparingInt(Order::getOrderID));
        orderQueue.addAll(p_orders);
    
        while (!orderQueue.isEmpty()) {
            List<String> destinations = new ArrayList<>();
            List<LocalTime> travelTimes = new ArrayList<>();
            float totalDistance = 0.0f;
            LocalTime estimatedTime = startTime;
            List<Order> ordersForToday = new ArrayList<>();
    
            while (!orderQueue.isEmpty() && canDeliverOrderInDay(estimatedTime) && ordersForToday.size() < 5) {
                Order currentOrder = orderQueue.poll();
                if (!isOrderDeliveredInTime(currentOrder, currentDate)) {
                    continue;
                }
    
                double[] originCoordinates = geoDataProvider.getCoordinatesFromAddress(warehouseLocation);
                double[] destinationCoordinates = geoDataProvider.getCoordinatesFromAddress(currentOrder.getDestination());
                double distance = geoDataProvider.calculateDistanceBetweenTwoPoints(originCoordinates, destinationCoordinates);
                double duration = geoDataProvider.calculateDurationBetweenTwoPoints(originCoordinates, destinationCoordinates);
    
                int assemblyTime = currentOrder.getTotalAssemblyTime().toMinutesPart();
                LocalTime totalTimeForDestination = estimatedTime.plusMinutes((long) duration + assemblyTime);
    
                if (estimatedTime.plusMinutes((long) duration).isAfter(WORKDAY_END_TIME)) {
                    orderQueue.add(currentOrder); // Si no cabe, devolver el pedido a la cola para el siguiente día
                    break;
                }
    
                destinations.add(currentOrder.getDestination());
                travelTimes.add(totalTimeForDestination);
                totalDistance += distance;
    
                estimatedTime = totalTimeForDestination;
    
                ordersForToday.add(currentOrder);
            }
    
            if (!ordersForToday.isEmpty()) {
                Route route = new Route(routeDayCounter, warehouseLocation, destinations, travelTimes, totalDistance, estimatedTime);
                currentDayRoutes.add(route);
            }

            // Si terminamos con las rutas de hoy, pasamos al siguiente día
            if (estimatedTime.isAfter(WORKDAY_END_TIME)) {
                allRoutes.add(new ArrayList<>(currentDayRoutes)); // Añadir las rutas del día
                currentDayRoutes.clear(); // Limpiar las rutas del día para el siguiente
                currentDate = moveToNextWorkday(currentDate);
                routeDayCounter++; // Incrementamos el contador para el próximo día
                estimatedTime = startTime; // Reiniciar la hora de trabajo para el próximo día
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

    private boolean canDeliverOrderInDay(LocalTime estimatedTime) {
        return estimatedTime.isBefore(WORKDAY_END_TIME);
    }

    private LocalDate moveToNextWorkday(LocalDate currentDate) {
        currentDate = currentDate.plusDays(1);
        if (currentDate.getDayOfWeek().getValue() > 5) {
            currentDate = currentDate.plusDays(8 - currentDate.getDayOfWeek().getValue());
        }
        return currentDate;
    }
}
