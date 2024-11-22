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

    // Crear la orden y asignar las plataformas
    public Order createOrderWithPlatforms(Order order) {
        // Guardamos la orden antes de asignar plataformas, porque las plataformas dependen de la orden
        orderRepository.save(order); // Guardamos la orden primero

        if (order.getPlatforms() != null && !order.getPlatforms().isEmpty()) {
            for (Platform platform : order.getPlatforms()) {
                try {
                    // Asignar cada plataforma al espacio de almacenamiento
                    storeKeeper.placePlatformInCell(platform);
                    platform.setOrder(order); // Asociamos la plataforma a la orden
                    System.out.println("Plataforma " + platform.getPlatformId() + " colocada en el espacio de almacenamiento.");
                } catch (IllegalStateException e) {
                    // Si no se puede asignar la plataforma, manejamos el error
                    System.out.println("Error al asignar la plataforma " + platform.getPlatformId() + ": " + e.getMessage());
                    throw new IllegalStateException("Error al asignar la plataforma a la orden.");
                }
            }
        } else {
            System.out.println("No se encontraron plataformas para la orden.");
        }

        return order; // Devolvemos la orden con las plataformas asignadas
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
