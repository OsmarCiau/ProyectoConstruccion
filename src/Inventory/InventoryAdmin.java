package Inventory;

import java.util.ArrayList;

public class InventoryAdmin {
    private ArrayList<Furniture> a_furnitureDatabase = new ArrayList<>();

    private static final InventoryAdminValidationUtils inventoryAdminValidator = new InventoryAdminValidationUtils();

    public void addFurniture(PackingList p_packingList) {
        ArrayList<Furniture> productsToAdd = p_packingList.getProducts();
        boolean isPackingListValid = inventoryAdminValidator.isPackingListEmpty(productsToAdd);

        if (isPackingListValid) {
            a_furnitureDatabase.addAll(productsToAdd);
            System.out.println("Added " + productsToAdd.size() + " items to the inventory.");
        }
    }

    public void removeFurniture(PackingList p_packingList) {
        ArrayList<Furniture> productsToRemove = p_packingList.getProducts();
        boolean isPackingListValid = inventoryAdminValidator.isPackingListEmpty(productsToRemove);

        if (isPackingListValid) {
            a_furnitureDatabase.removeAll(productsToRemove);
            System.out.println("Removed " + productsToRemove.size() + " items from the inventory.");
        }
    }

    public void updateFurniture(PackingList p_packingList) {
        ArrayList<Furniture> productsToUpdate = p_packingList.getProducts();
        boolean isPackingListValid = inventoryAdminValidator.isPackingListEmpty(productsToUpdate);

        if (isPackingListValid) {
            for (Furniture furniture : productsToUpdate) {
                updateFurnitureItem(furniture);
            }
            System.out.println("Updated inventory with " + productsToUpdate.size() + " items.");
        }
    }

    private void updateFurnitureItem(Furniture p_furniture) {
        int index = a_furnitureDatabase.indexOf(p_furniture);
        boolean isFurnitureIndexValid = inventoryAdminValidator.validateFurnitureIndex(index, p_furniture.getFurnitureId());

        if (isFurnitureIndexValid) {
            a_furnitureDatabase.set(index, p_furniture);
            System.out.println("Updated furniture with ID: " + p_furniture.getFurnitureId());
        }
    }
}
