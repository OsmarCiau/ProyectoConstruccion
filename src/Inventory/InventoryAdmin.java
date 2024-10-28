package Inventory;

import java.util.ArrayList;

public class InventoryAdmin {
    private ArrayList<Furniture> a_furnitureDatabase = new ArrayList<>();

    public void addFurniture(PackingList p_packingList) {
        ArrayList<Furniture> productsToAdd = p_packingList.getProducts();
        isPackingListNotEmpty(productsToAdd);
        a_furnitureDatabase.addAll(productsToAdd);
        System.out.println("Added " + productsToAdd.size() + " items to the inventory.");
    }

    public void removeFurniture(PackingList p_packingList) {
        ArrayList<Furniture> productsToRemove = p_packingList.getProducts();
        isPackingListNotEmpty(productsToRemove);
        a_furnitureDatabase.removeAll(productsToRemove);
        System.out.println("Removed " + productsToRemove.size() + " items from the inventory.");
    }

    public void updateFurniture(PackingList p_packingList) {
        ArrayList<Furniture> productsToUpdate = p_packingList.getProducts();
        isPackingListNotEmpty(productsToUpdate);

        for (Furniture furniture : productsToUpdate) {
            updateFurnitureItem(furniture);
        }
        System.out.println("Updated inventory with " + productsToUpdate.size() + " items.");
    }

    private void updateFurnitureItem(Furniture p_furniture) {
        int index = a_furnitureDatabase.indexOf(p_furniture);
        validateFurnitureIndex(index, p_furniture.getFurnitureId());
        a_furnitureDatabase.set(index, p_furniture);
        System.out.println("Updated furniture with ID: " + p_furniture.getFurnitureId());
    }

    private void validateFurnitureIndex(int p_index, int p_furnitureId) {
        if (p_index < 0) {
            throw new IllegalArgumentException("Furniture with ID " + p_furnitureId + " does not exist in the inventory.");
        }
    }

    private void isPackingListNotEmpty(ArrayList<Furniture> p_products) {
        if (p_products == null || p_products.isEmpty()) {
            throw new IllegalArgumentException("Error: PackingList is empty or null.");
        }
    }

}
