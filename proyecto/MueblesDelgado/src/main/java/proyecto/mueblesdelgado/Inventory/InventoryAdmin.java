package proyecto.mueblesdelgado.Inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import proyecto.mueblesdelgado.Repositories.FurnitureRepository;
import proyecto.mueblesdelgado.Repositories.PackingListRepository;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryAdmin {

    private final FurnitureRepository furnitureRepository;
    private final PackingListRepository packingListRepository;

    @Autowired
    public InventoryAdmin(FurnitureRepository furnitureRepository, PackingListRepository packingListRepository) {
        this.furnitureRepository = furnitureRepository;
        this.packingListRepository = packingListRepository;
    }

    @PostMapping("/add")
    public String addPackingList(@RequestBody PackingList packingList) {
        if (packingList != null && packingList.getProducts() != null && !packingList.getProducts().isEmpty()) {
            // Establecer la relación bidireccional
            for (Furniture furniture : packingList.getProducts()) {
                furniture.setPackingList(packingList);
            }
            // Guardar el PackingList, lo que también guarda los Furniture debido a CascadeType.ALL
            packingListRepository.save(packingList);
            return "Added " + packingList.getProducts().size() + " items to the inventory from PackingList with folio: " + packingList.getFolio();
        } else {
            return "PackingList is empty or null.";
        }
    }

    @PostMapping("/remove")
    public void removeFurniture(@RequestBody PackingList packingList) {
        List<Furniture> productsToRemove = packingList.getProducts();
        if (productsToRemove != null && !productsToRemove.isEmpty()) {
            furnitureRepository.deleteAll(productsToRemove);
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
        if (furnitureRepository.existsById(furniture.getFurnitureId())) {
            furnitureRepository.save(furniture); // Guarda el mueble (crea o actualiza)
            System.out.println("Updated furniture with ID: " + furniture.getFurnitureId());
        } else {
            System.out.println("Furniture with ID " + furniture.getFurnitureId() + " does not exist in the inventory.");
        }
    }
}
