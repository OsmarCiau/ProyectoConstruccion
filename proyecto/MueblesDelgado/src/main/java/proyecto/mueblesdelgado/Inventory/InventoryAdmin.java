package proyecto.mueblesdelgado.Inventory;

import org.springframework.web.bind.annotation.*;
import proyecto.mueblesdelgado.Repositories.InventoryAdminRepository;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryAdmin {

    private final InventoryAdminRepository inventoryAdminRepository;

    public InventoryAdmin(InventoryAdminRepository inventoryAdminRepository) {
        this.inventoryAdminRepository = inventoryAdminRepository;
    }

    @PostMapping("/add")
    public void addFurniture(@RequestBody PackingList packingList) {
        List<Furniture> productsToAdd = packingList.getProducts();
        if (productsToAdd != null && !productsToAdd.isEmpty()) {
            inventoryAdminRepository.saveAll(productsToAdd);
            System.out.println("Added " + productsToAdd.size() + " items to the inventory.");
        } else {
            System.out.println("PackingList is empty or null.");
        }
    }

    @PostMapping("/remove")
    public void removeFurniture(@RequestBody PackingList packingList) {
        List<Furniture> productsToRemove = packingList.getProducts();
        if (productsToRemove != null && !productsToRemove.isEmpty()) {
            inventoryAdminRepository.deleteAll(productsToRemove);
            System.out.println("Removed " + productsToRemove.size() + " items from the inventory.");
        } else {
            System.out.println("PackingList is empty or null.");
        }
    }

    @PutMapping("/update")
    public void updateFurniture(@RequestBody PackingList packingList) {
        List<Furniture> productsToUpdate = packingList.getProducts();
        if (productsToUpdate != null && !productsToUpdate.isEmpty()) {
            for (Furniture furniture : productsToUpdate) {
                updateFurnitureItem(furniture);
            }
            System.out.println("Updated inventory with " + productsToUpdate.size() + " items.");
        } else {
            System.out.println("PackingList is empty or null.");
        }
    }

    private void updateFurnitureItem(Furniture furniture) {
        if (inventoryAdminRepository.existsById(furniture.getFurnitureId())) {
            inventoryAdminRepository.save(furniture); // Guarda el mueble (crea o actualiza)
            System.out.println("Updated furniture with ID: " + furniture.getFurnitureId());
        } else {
            System.out.println("Furniture with ID " + furniture.getFurnitureId() + " does not exist in the inventory.");
        }
    }
}
