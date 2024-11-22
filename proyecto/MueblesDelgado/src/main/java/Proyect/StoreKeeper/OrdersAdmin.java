package Proyect.StoreKeeper;

import Proyect.Inventory.Furniture;
import Proyect.Repositories.FurnitureRepository;
import Proyect.Repositories.OrderRepository;
import Proyect.Validations.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrdersAdmin {

    private final FurnitureRepository furnitureRepository;
    private final OrderRepository orderRepository;
    private final StoreKeeper storeKeeper;

    @Autowired
    public OrdersAdmin(FurnitureRepository furnitureRepository, OrderRepository orderRepository, StoreKeeper storeKeeper) {
        this.furnitureRepository = furnitureRepository;
        this.orderRepository = orderRepository;
        this.storeKeeper = storeKeeper;
    }

    // Paso 1: Verificar si la orden existe o crear una nueva
    public Order findOrCreateOrder(int orderId, String destination, LocalDate deliveryDate) {
        // Intentamos encontrar la orden por el `order_id`
        Order existingOrder = orderRepository.findById(orderId).orElse(null);

        if (existingOrder == null) {
            // Si no existe, creamos una nueva orden
            existingOrder = new Order(orderId, destination, deliveryDate, List.of());
            orderRepository.save(existingOrder);  // Guardamos la nueva orden
        }

        return existingOrder;
    }

    // Paso 2: Crear mueble y asociarlo a una orden
    public void createFurnitureWithOrder(Furniture furniture, int orderId) {
        // Verificamos si la orden existe
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: ID " + orderId));
        
        // Asociamos el mueble con la orden
        furniture.setOrder(order);  // Establecer el `order` en el mueble

        // Guardamos el mueble
        furnitureRepository.save(furniture);
    }

    // Paso 3: Asociar muebles con la orden
    public void associateFurnitureWithOrder(int orderId) {
        // Buscar todos los muebles que tienen este `order_id`
        List<Furniture> furnitureList = furnitureRepository.findByOrder_orderID(orderId);

        // Asociar los muebles con la orden
        for (Furniture furniture : furnitureList) {
            // Si el mueble no tiene una orden asociada
            if (furniture.getOrder() == null) {
                // Establecemos la orden en el mueble
                furniture.setOrder(new Order(orderId, null, null, null));  // Asociar al `order`
                furnitureRepository.save(furniture);  // Guardamos el mueble
            }
        }
    }

    // Crear la orden y asociar los muebles a esa orden
    public Order createOrderWithFurniture(int orderId, String destination, LocalDate deliveryDate) {
        // Verificamos si la orden existe o la creamos
        Order order = findOrCreateOrder(orderId, destination, deliveryDate);

        // Asociamos los muebles a la orden
        associateFurnitureWithOrder(orderId);

        return order;  // Retorna la orden con los muebles asociados
    }

    // Actualizar los muebles con el Order asociado
    private void updateFurnitureOrder(Order order) {
        // Obtén los muebles de la orden
        List<Furniture> furnitureList = order.getOrderContent();

        for (Furniture furniture : furnitureList) {
            if (furniture.getOrder() == null) {  // Solo actualiza si el mueble no tiene un Order asociado
                furniture.setOrder(order);  // Asociamos el mueble con la orden
                furnitureRepository.save(furniture);  // Guardamos el mueble con la relación al Order
            }
        }
    }

    // Obtener la orden por ID
    public Order findByOrderId(int p_orderId) {
        return orderRepository.findById(p_orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: ID " + p_orderId));
    }

    // Obtener todas las órdenes
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Eliminar una orden
    public void removeOrder(int p_orderId) {
        if (orderRepository.existsById(p_orderId)) {
            orderRepository.deleteById(p_orderId);
        } else {
            throw new IllegalArgumentException("Order not found: ID " + p_orderId);
        }
    }

    // Actualizar una orden existente
    public Order updateOrder(Order p_order) {
        ValidationUtils.validateNonNull(p_order, "Order");
        orderRepository.findById(p_order.getOrderID())
                .orElseThrow(() -> new IllegalArgumentException("Order not found: ID " + p_order.getOrderID()));
        return orderRepository.save(p_order);
    }

    // Guardar múltiples órdenes
    public void setOrders(List<Order> p_orders) {
        orderRepository.saveAll(p_orders);
    }
}
