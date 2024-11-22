package Proyect.Logistics;

import Proyect.Repositories.DeliveryTruckRepository;
import Proyect.Repositories.OrderTruckAssignmentRepository;
import Proyect.Repositories.OrderRepository;
import Proyect.StoreKeeper.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class LogisticsAdminService {

    @Autowired
    private DeliveryTruckRepository deliveryTruckRepository;

    @Autowired
    private OrderTruckAssignmentRepository orderTruckAssignmentRepository;

    @Autowired
    private OrderRepository orderRepository;
    
    private final RoutePlanner routePlanner;

    @Autowired
    public LogisticsAdminService(RoutePlanner routePlanner) {
        this.routePlanner = routePlanner;
    }

    // Guardar una lista de camiones disponibles
    public void setTrucksAvailable(List<DeliveryTruck> trucksAvailable) {
        deliveryTruckRepository.saveAll(trucksAvailable);
    }

    // Obtener todos los camiones disponibles
    public List<DeliveryTruck> getAvailableTrucks() {
        return deliveryTruckRepository.findAll();
    }

    // Obtener todas las asignaciones de pedidos a camiones
    public List<RouteTruckAssignment> getOrderTruckAssignments() {
        return orderTruckAssignmentRepository.findAll();
    }

    // Método solo para planificar rutas, no asigna nada
    public List<Route> planRoutesForOrders(List<Order> orders) throws Exception {
        List<DeliveryTruck> trucksAvailable = getAvailableTrucks();

        // Verificar que haya camiones disponibles
        if (trucksAvailable.isEmpty()) {
            throw new IllegalArgumentException("No trucks available for assignment");
        }

        // Planificar las rutas usando RoutePlanner y devolverlas
        return routePlanner.planOptimalRoutes(orders, trucksAvailable.size());
    }

    // Método para asignar rutas a los camiones y asignar la ruta a las órdenes
    @Transactional
    public void assignRoutesToTrucks(List<Route> plannedRoutes, List<Order> orders) {
        List<DeliveryTruck> trucksAvailable = getAvailableTrucks();
        int truckIndex = 0;

        for (Route route : plannedRoutes) {
            if (truckIndex >= trucksAvailable.size()) {
                truckIndex = 0;
            }

            DeliveryTruck selectedTruck = trucksAvailable.get(truckIndex);

            // Crear la asignación entre el camión y la ruta
            createAssignment(selectedTruck, route);

            // Aquí asignamos la ruta a las órdenes correspondientes
            assignRouteToOrders(orders, route);

            truckIndex++;
        }
    }

    // Método para asignar la ruta a las órdenes
    private void assignRouteToOrders(List<Order> orders, Route route) {
        for (Order order : orders) {
            if (order.getDeliveryDate().isBefore(LocalDate.now())) {
                // Si la fecha de entrega del pedido es antes de hoy, asigna la ruta
                order.setRoute(route);
                // Guardamos la orden actualizada en el repositorio de órdenes
                orderRepository.save(order);
            }
        }
    }

    // Método para crear y guardar la asignación entre camión y ruta
    private void createAssignment(DeliveryTruck truck, Route route) {
        RouteTruckAssignment assignment = new RouteTruckAssignment(truck, route);
        orderTruckAssignmentRepository.save(assignment);
    }

    // Método Orquestador para planificar rutas
    public List<Route> planRoutes(List<Order> orders) throws Exception {
        return planRoutesForOrders(orders);  // Solo planifica rutas
    }

    // Método Orquestador para asignar rutas a camiones y a órdenes
    @Transactional // Aquí también se agrega la transacción
    public void assignRoutes(List<Route> plannedRoutes, List<Order> orders) {
        assignRoutesToTrucks(plannedRoutes, orders);  // Asigna rutas a camiones y a órdenes
    }
}
