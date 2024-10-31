// LogisticsAdminService.java
package Proyect.Logistics;

import Proyect.StoreKeeper.Order;
import Proyect.Logistics.DeliveryTruck;
import Proyect.Logistics.OrderTruckAssignment;
import Proyect.Repositories.DeliveryTruckRepository;
import Proyect.Repositories.OrderTruckAssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogisticsAdmin {

    //@Autowired
    //private OrderRepository orderRepository;

    @Autowired
    private DeliveryTruckRepository deliveryTruckRepository;

    @Autowired
    private OrderTruckAssignmentRepository orderTruckAssignmentRepository;


    /* Guardar una lista de pedidos
    //public void setOrders(List<Order> orders) {
        orderRepository.saveAll(orders);
    } */

    // Guardar una lista de camiones
    public void setTrucksAvailable(List<DeliveryTruck> trucksAvailable) {
        deliveryTruckRepository.saveAll(trucksAvailable);
    }

    // Obtener todos los pedidos
    /*public List<Order> getOrders() {
        return orderRepository.findAll();
    } */

    // Obtener todos los camiones disponibles
    public List<DeliveryTruck> getTrucksAvailable() {
        return deliveryTruckRepository.findAll();
    }

    // Obtener la ruta de entrega planificada
    /*public List<String> getDeliveryRoute() {
        return routePlanner.planOptimalRoutes(getOrders(), routePlanner.getWarehouseLocation());
    } */

    // Asignar un pedido a un camión y almacenar en la base de datos
    /*public void assignOrderToTruck(int orderId, int trackingNumber) {
        Order selectedOrder = orderRepository.findByOrderId(orderId);
        DeliveryTruck selectedTruck = deliveryTruckRepository.findByTrackingNumber(trackingNumber);

        if (selectedOrder != null && selectedTruck != null) {
            OrderTruckAssignment assignment = new OrderTruckAssignment(selectedOrder, selectedTruck);
            orderTruckAssignmentRepository.save(assignment);
        }
    } */

    // Obtener todas las asignaciones de pedidos a camiones
    public List<OrderTruckAssignment> getOrderTruckAssignments() {
        return orderTruckAssignmentRepository.findAll();
    }
}
