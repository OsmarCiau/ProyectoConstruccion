package Proyect.Logistics;

import Proyect.StoreKeeper.Order;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;


import Proyect.Validations.ValidationUtils;

public class RoutePlanner {
    private String warehouseLocation = null;
    private LocalTime startTime = LocalTime.of(0, 0);
    private GeoDataProvider geoDataProvider = new GeoDataProvider();
    private final LocalTime WORKDAY_END_TIME = LocalTime.of(18, 0);  // Fin de jornada laboral (8 horas)
    private final int MAX_WORK_DAYS = 3; // Máximo 3 días hábiles para entrega

    public RoutePlanner(String p_wareHouseLocation, LocalTime p_startTime) {
        setWarehouseLocation(p_wareHouseLocation);
        setStartTime(p_startTime);
    }

    // Método para establecer la ubicación del almacén
    private void setWarehouseLocation(String p_warehouseLocation) {
        ValidationUtils.validateNonNull(p_warehouseLocation, "Warehouse Location");
        this.warehouseLocation = p_warehouseLocation;
    }

    // Método para establecer la hora de inicio del día
    private void setStartTime(LocalTime p_startTime) {
        ValidationUtils.validateStartTime(p_startTime, "Start Time");
        this.startTime = p_startTime;
    }

    public String getWarehouseLocation() {
        return warehouseLocation;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    // Método que estima el tiempo de entrega para un pedido, considerando el ensamblaje y la distancia
    public LocalTime estimateDeliveryTimeForOrder(Order p_order) throws Exception {
        LocalTime assemblyTime = LocalTime.ofSecondOfDay(p_order.getTotalAssemblyTime().getSeconds());

        // Obtener las coordenadas del almacén y de la dirección de entrega
        double[] warehouseCoordinates = geoDataProvider.getCoordinatesFromAddress(warehouseLocation);
        double[] deliveryCoordinates = geoDataProvider.getCoordinatesFromAddress(p_order.getDestination());

        // Obtener la duración de la ruta desde el almacén hasta el destino
        double duration = geoDataProvider.getDuration(warehouseCoordinates, deliveryCoordinates); // Duration en minutos

        // Convertir la duración a segundos
        long durationInSeconds = (long) (duration * 60); 
        long totalSeconds = durationInSeconds + assemblyTime.toSecondOfDay();
        LocalTime estimatedTime = LocalTime.ofSecondOfDay(totalSeconds);

        return estimatedTime;
    }


    // Método que planifica las rutas óptimas para los pedidos del día
    public List<Route> planDailyRoutes(List<Order> orders) throws Exception {
        List<Route> plannedRoutes = new ArrayList<>();
        LocalTime availableTime = LocalTime.of(8, 0);  // Hora de inicio de la jornada laboral (8:00 AM)
    
        // Optimizar las rutas de los pedidos
        ArrayList<Order> orderedDeliveries = optimizeRoutes(new ArrayList<>(orders));
    
        // Empezar desde el almacén para la primera ruta
        String currentOrigin = warehouseLocation;
    
        // Planificar las entregas para hoy
        for (Order order : orderedDeliveries) {
            // Calcular la estimación de tiempo para el pedido
            LocalTime estimatedTime = estimateDeliveryTimeForOrder(order);
            float distance = (float) geoDataProvider.getDistance(
                    geoDataProvider.getCoordinatesFromAddress(currentOrigin), 
                    geoDataProvider.getCoordinatesFromAddress(order.getDestination()));
    
            // Crear la ruta planificada (usando la clase Route)
            Route route = new Route(plannedRoutes.size() + 1, currentOrigin, order.getDestination(), distance, estimatedTime);
    
            // Verificar si el tiempo estimado de entrega excede el horario laboral
            if (estimatedTime.isAfter(WORKDAY_END_TIME)) {
                availableTime = LocalTime.of(8, 0);  // Reiniciar al inicio del siguiente día
                availableTime = availableTime.plusMinutes(estimatedTime.toSecondOfDay() / 60); // Sumar el tiempo estimado de entrega
            } else {
                if (estimatedTime.isBefore(availableTime)) {
                    availableTime = availableTime.plusMinutes(estimatedTime.toSecondOfDay() / 60);
                } else {
                    availableTime = estimatedTime;
                }
            }
    
            // Agregar la ruta planificada a la lista
            plannedRoutes.add(route);
    
            // El origen de la próxima ruta será el destino de esta ruta
            currentOrigin = order.getDestination();
        }
    
        // Validar que las entregas estén dentro del límite de 3 días hábiles
        validateDeliveryDate(orderedDeliveries);
    
        // Retornar las rutas planificadas
        return plannedRoutes;
    }
    
    // Método que optimiza las rutas de entrega de los pedidos
    private ArrayList<Order> optimizeRoutes(ArrayList<Order> orders) throws Exception {
        // Crear una lista nueva para los pedidos ordenados por distancia
        ArrayList<Order> orderedDeliveries = new ArrayList<>(orders);
        
        // Coordenadas del almacén
        double[] warehouseCoordinates = geoDataProvider.getCoordinatesFromAddress(warehouseLocation); // Coordenadas del almacén

        // Ordenar los pedidos utilizando un comparador basado en la distancia
        orderedDeliveries.sort(Comparator.comparingDouble(order -> 
            {
                try {
                    return geoDataProvider.getDistance(warehouseCoordinates, geoDataProvider.getCoordinatesFromAddress(order.getDestination()));
                } catch (Exception e) {
                    e.printStackTrace();
                    return Double.MAX_VALUE; // Retornar un valor muy grande en caso de error
                }
            }));

        return orderedDeliveries;
    }


    // Método para validar que la fecha de entrega de los pedidos no exceda el límite de 3 días hábiles    
    private void validateDeliveryDate(List<Order> orders) {
        LocalDate currentDate = LocalDate.now();
        LocalDate maxDeliveryDate = currentDate.plusDays(MAX_WORK_DAYS);
    
        // Iterar a través de la lista de pedidos
        for (Order order : orders) {
            // Convertir Date a LocalDate
            LocalDate deliveryDate = convertToLocalDate(order.getDeliveryDate());
    
            // Verificar si la fecha de entrega excede el límite
            if (deliveryDate.isAfter(maxDeliveryDate)) {
                throw new RuntimeException("La fecha de entrega del pedido con ID " + order.getOrderID() + " excede el límite de " + MAX_WORK_DAYS + " días hábiles.");
            }
        }
    }
    
    // Método para convertir un objeto Date a LocalDate
    private LocalDate convertToLocalDate(Date deliveryDate) {
        return deliveryDate.toInstant()
                           .atZone(ZoneId.systemDefault())
                           .toLocalDate();
    }
    
    @Override
    public String toString() {
        return "RoutePlanner{" +
                "warehouseLocation='" + warehouseLocation + '\'' +
                ", startTime=" + startTime +
                '}';
    }
}
