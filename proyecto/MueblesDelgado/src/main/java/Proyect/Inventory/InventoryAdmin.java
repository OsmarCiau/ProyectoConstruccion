package Proyect.Inventory;

import Proyect.Repositories.FurnitureRepository;
import Proyect.Repositories.PackingListRepository;
import Proyect.Repositories.OrderRepository;  // Asegúrate de importar el repositorio de Order
import Proyect.Validations.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Proyect.StoreKeeper.Order;  // Asegúrate de importar la clase Order

import java.util.List;

@Service
public class InventoryAdmin {

    @Autowired
    private FurnitureRepository furnitureRepository;

    @Autowired
    private PackingListRepository packingListRepository;

    @Autowired
    private OrderRepository orderRepository; // Repositorio de Order

    // Método para agregar muebles al inventario
    public void addFurnitureToInventory(PackingList p_packingList) {
        packingListRepository.save(p_packingList);
        List<Furniture> productsToAdd = p_packingList.getProducts();
        ValidationUtils.validatesList(productsToAdd, "Products");

        // Aquí agregamos el proceso de búsqueda o creación del Order
        for (Furniture furniture : productsToAdd) {
            // Buscamos o creamos el Order a partir del objeto Order del Furniture
            Order order = findOrCreateOrder(furniture.getOrder());  // Usamos el objeto Order
            furniture.setOrder(order);  // Asociamos el Order al Furniture
        }

        furnitureRepository.saveAll(productsToAdd);
        System.out.println("Added " + productsToAdd.size() + " items to the inventory.");
    }

    // Método para recuperar todos los muebles del inventario
    public List<Furniture> retrieveAllFurnitureFromInventory() {
        return furnitureRepository.findAll();
    }

    // Método para obtener las listas de packing
    public List<PackingList> getPackingList() {
        return packingListRepository.findAll();
    }

    // Método para remover muebles del inventario
    public void removeFurnitureFromInventory(PackingList p_packingList) {
        List<Furniture> productsToRemove = p_packingList.getProducts();
        ValidationUtils.validatesList(productsToRemove, "Products");
        furnitureRepository.deleteAll(productsToRemove);
        System.out.println("Removed " + productsToRemove.size() + " items from the inventory.");
    }

    // Método para actualizar muebles en el inventario
    public void updateFurnitureInInventory(PackingList p_packingList) {
        List<Furniture> productsToUpdate = p_packingList.getProducts();

        for (Furniture furniture : productsToUpdate) {
            // Aquí también asociamos el Order correspondiente antes de actualizar el mueble
            Order order = findOrCreateOrder(furniture.getOrder());  // Usamos el objeto Order
            furniture.setOrder(order);  // Asociamos el Order al Furniture
            updateFurnitureItem(furniture);
        }

        packingListRepository.save(p_packingList);
        System.out.println("Updated inventory with " + productsToUpdate.size() + " items.");
    }

    // Método privado para actualizar un mueble específico
    private void updateFurnitureItem(Furniture p_furniture) {
        furnitureRepository.save(p_furniture);
        System.out.println("Updated furniture with ID: " + p_furniture.getFurnitureId());
    }

    // Método para buscar o crear un Order dado un objeto Order
    private Order findOrCreateOrder(Order p_order) {
        // Si el Order es null, creamos uno nuevo
        if (p_order == null) {
            p_order = new Order();  // Crear un nuevo Order si no se proporciona
            orderRepository.save(p_order);  // Guardar el nuevo Order en la base de datos
        } else {
            // Si ya tiene un ID, intentamos encontrarlo
            Order existingOrder = orderRepository.findById(p_order.getOrderID()).orElse(null);
            if (existingOrder == null) {
                // Si no existe, guardamos el Order proporcionado
                orderRepository.save(p_order);
                p_order = orderRepository.findById(p_order.getOrderID()).orElse(p_order);
            } else {
                p_order = existingOrder;  // Si existe, usamos el Order encontrado
            }
        }
        return p_order;  // Retornamos el Order asociado
    }
}
