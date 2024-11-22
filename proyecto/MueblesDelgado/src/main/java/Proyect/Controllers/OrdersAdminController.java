package Proyect.Controllers;

import Proyect.StoreKeeper.Order;
import Proyect.StoreKeeper.OrdersAdmin;
import Proyect.StoreKeeper.Platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrdersAdminController {
    private final OrdersAdmin ordersAdminService;

    @Autowired
    public OrdersAdminController(OrdersAdmin ordersAdminService) {
        this.ordersAdminService = ordersAdminService;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        // Primero, creamos la orden
        Order createdOrder = ordersAdminService.findOrCreateOrder(order.getOrderID(), order.getDestination(), order.getDeliveryDate());

        // Devolvemos la orden creada con el status 201 (CREATED)
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }


    @PostMapping("/orders")
    public ResponseEntity<Void> setOrders(@RequestBody List<Order> orders) {
        ordersAdminService.setOrders(orders);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getOrders() {
        List<Order> orders = ordersAdminService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{p_orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable int p_orderId) {
        Order order = ordersAdminService.findByOrderId(p_orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping("/{p_orderId}")
    public ResponseEntity<Order> updateOrder(@PathVariable int p_orderId, @RequestBody Order p_order) {
        p_order.setOrderID(p_orderId);
        Order updatedOrder = ordersAdminService.updateOrder(p_order);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

    @DeleteMapping("/{p_orderId}")
    public ResponseEntity<Void> removeOrder(@PathVariable int p_orderId) {
        ordersAdminService.removeOrder(p_orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

