// LogisticsAdminController.java
package Proyect.Controllers;

import Proyect.StoreKeeper.Order;
import Proyect.Logistics.DeliveryTruck;
import Proyect.Logistics.OrderTruckAssignment;
import Proyect.Logistics.LogisticsAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logistics")
public class LogisticsAdminController {

    @Autowired
    private LogisticsAdmin logisticsAdminService;

    // Endpoint para guardar o actualizar pedidos
    /*@PostMapping("/orders")
    public ResponseEntity<Void> setOrders(@RequestBody List<Order> orders) {
        logisticsAdminService.setOrders(orders);
        return new ResponseEntity<>(HttpStatus.CREATED);
    } */

    // Endpoint para obtener todos los pedidos
    /*@GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrders() {
        List<Order> orders = logisticsAdminService.getOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    } */

    // Endpoint para guardar o actualizar camiones disponibles
    @PostMapping("/trucks")
    public ResponseEntity<Void> setTrucksAvailable(@RequestBody List<DeliveryTruck> trucksAvailable) {
        logisticsAdminService.setTrucksAvailable(trucksAvailable);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Endpoint para obtener todos los camiones disponibles
    @GetMapping("/trucks")
    public ResponseEntity<List<DeliveryTruck>> getTrucksAvailable() {
        List<DeliveryTruck> trucks = logisticsAdminService.getTrucksAvailable();
        return new ResponseEntity<>(trucks, HttpStatus.OK);
    }

    // Endpoint para obtener la ruta de entrega planificada
    /*@GetMapping("/delivery-route")
    public ResponseEntity<List<String>> getDeliveryRoute() {
        List<String> deliveryRoute = logisticsAdminService.getDeliveryRoute();
        return new ResponseEntity<>(deliveryRoute, HttpStatus.OK);
    } */

    // Endpoint para asignar un pedido a un camión
    /*@PostMapping("/assign")
    public ResponseEntity<Void> assignOrderToTruck(@RequestParam int orderId, @RequestParam int trackingNumber) {
        logisticsAdminService.assignOrderToTruck(orderId, trackingNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Endpoint para obtener todas las asignaciones de pedidos a camiones
    @GetMapping("/assignments")
    public ResponseEntity<List<OrderTruckAssignment>> getOrderTruckAssignments() {
        List<OrderTruckAssignment> assignments = logisticsAdminService.getOrderTruckAssignments();
        return new ResponseEntity<>(assignments, HttpStatus.OK);
    } */
}
