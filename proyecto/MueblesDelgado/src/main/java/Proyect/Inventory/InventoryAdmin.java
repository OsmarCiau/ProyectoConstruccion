package Proyect.Inventory;

import Proyect.Repositories.FurnitureRepository;
import Proyect.Repositories.PackingListRepository;
import Proyect.Validations.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryAdmin {

    @Autowired
    private FurnitureRepository furnitureRepository;

    @Autowired
    private PackingListRepository packingListRepository;

    public void addFurnitureItemsToInventory(PackingList p_packingList) {
        packingListRepository.save(p_packingList);
        ArrayList<Furniture> productsToAdd = p_packingList.getProducts();
        ValidationUtils.validatesArrayList(productsToAdd, "Products");
        furnitureRepository.saveAll(productsToAdd);
        System.out.println("Added " + productsToAdd.size() + " items to the inventory.");
    }

    public List<Furniture> retrieveAllFurnitureFromInventory() {
        return furnitureRepository.findAll();
    }

    public void removeFurnitureItemsFromInventory(PackingList p_packingList) {
        ArrayList<Furniture> productsToRemove = p_packingList.getProducts();
        ValidationUtils.validatesArrayList(productsToRemove, "Products");
        furnitureRepository.deleteAll(productsToRemove);
        System.out.println("Removed " + productsToRemove.size() + " items from the inventory.");

    }

    public void updateFurnitureItemsInInventory(PackingList p_packingList) {
        ArrayList<Furniture> productsToUpdate = p_packingList.getProducts();
        ValidationUtils.validatesArrayList(productsToUpdate, "Products");

        for (Furniture furniture : productsToUpdate) {
            updateFurnitureItemInInventory(furniture);
        }
        packingListRepository.save(p_packingList);
        System.out.println("Updated inventory with " + productsToUpdate.size() + " items.");

    }

    private void updateFurnitureItemInInventory(Furniture p_furniture) {
        furnitureRepository.save(p_furniture);
        System.out.println("Updated furniture with ID: " + p_furniture.getFurnitureId());
    }
}