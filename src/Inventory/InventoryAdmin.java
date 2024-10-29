package Inventory;

import java.util.ArrayList;

public class InventoryAdmin {
    private ArrayList<Furniture> furnitureDatabase = new ArrayList<>();

    private static final InventoryAdminValidationUtils inventoryAdminValidator = new InventoryAdminValidationUtils();

    public void addFurniture(PackingList p_packingList) {
        ArrayList<Furniture> productsToAdd = p_packingList.getProducts();
        inventoryAdminValidator.isPackingListEmpty(productsToAdd);
        furnitureDatabase.addAll(productsToAdd);
        System.out.println("Added " + productsToAdd.size() + " items to the inventory.");
    }

    public void removeFurniture(PackingList p_packingList) {
        ArrayList<Furniture> productsToRemove = p_packingList.getProducts();
        inventoryAdminValidator.isPackingListEmpty(productsToRemove);
        furnitureDatabase.removeAll(productsToRemove);
        System.out.println("Removed " + productsToRemove.size() + " items from the inventory.");
    }

    public void updateFurniture(PackingList p_packingList) {
        ArrayList<Furniture> productsToUpdate = p_packingList.getProducts();
        inventoryAdminValidator.isPackingListEmpty(productsToUpdate);
        for (Furniture furniture : productsToUpdate) {
            updateFurnitureItem(furniture);
        }
        System.out.println("Updated inventory with " + productsToUpdate.size() + " items.");
    }

    private void updateFurnitureItem(Furniture p_furniture) {
        int index = furnitureDatabase.indexOf(p_furniture);
        inventoryAdminValidator.validateFurnitureIndex(index);
        furnitureDatabase.set(index, p_furniture);
        System.out.println("Updated furniture with ID: " + p_furniture.getFurnitureId());
    }
}
