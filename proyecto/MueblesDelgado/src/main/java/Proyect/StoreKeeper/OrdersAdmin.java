package Proyect.StoreKeeper;

import Proyect.Repositories.OrderRepository;
import Proyect.Validations.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersAdmin {

    private final OrderRepository orderRepository;
    private final StoreKeeper storeKeeper;

    @Autowired
    public OrdersAdmin(OrderRepository orderRepository, StoreKeeper storeKeeper) {
        this.orderRepository = orderRepository;
        this.storeKeeper = storeKeeper;
    }

    public Order findByOrderId(int p_orderId) {
        return orderRepository.findById(p_orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: ID " + p_orderId));
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order addOrder(Order p_order) {
        ValidationUtils.validateNonNull(p_order, "Order");

        // Guardamos la orden en la base de datos primero
        Order savedOrder = orderRepository.save(p_order);

        // Llamamos al StoreKeeper para asignar el espacio a los muebles de la orden recién creada
        //storeKeeper.storeOrderInWarehouse(savedOrder);

        return savedOrder;
    }

    public void setOrders(List<Order> p_orders) {
        orderRepository.saveAll(p_orders);
    }

    public void removeOrder(int p_orderId) {
        if (orderRepository.existsById(p_orderId)) {
            orderRepository.deleteById(p_orderId);
        } else {
            throw new IllegalArgumentException("Order not found: ID " + p_orderId);
        }
    }

    public Order updateOrder(Order p_order) {
        ValidationUtils.validateNonNull(p_order, "Order");
        orderRepository.findById(p_order.getOrderID())
                .orElseThrow(() -> new IllegalArgumentException("Order not found: ID " + p_order.getOrderID()));
        return orderRepository.save(p_order);
    }
}
