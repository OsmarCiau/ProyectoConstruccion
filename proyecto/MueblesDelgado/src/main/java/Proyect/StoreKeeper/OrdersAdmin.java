package Proyect.StoreKeeper;

import Proyect.Inventory.InventoryAdmin;
import Proyect.Repositories.IdPairOrderFurnitureRepository;
import Proyect.Repositories.OrderRepository;
import Proyect.Validations.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersAdmin {

    private final OrderRepository orderRepository;

    private IdPairOrderFurnitureRepository idPairOrderFurnitureRepository;

    private InventoryAdmin inventoryAdmin; // Dependencia para gestionar inventario

    @Autowired
    public OrdersAdmin(
        OrderRepository p_orderRepository, 
        IdPairOrderFurnitureRepository p_idPairOrderFurnitureRepository, 
        InventoryAdmin p_inventoryAdmin) {
            
        this.orderRepository = p_orderRepository;
        this.idPairOrderFurnitureRepository = p_idPairOrderFurnitureRepository;
        this.inventoryAdmin = p_inventoryAdmin;
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
        return orderRepository.save(p_order);
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
