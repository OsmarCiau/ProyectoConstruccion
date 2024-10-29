package Proyect.Inventory;

import Proyect.Validations.ValidationUtils;

import java.util.ArrayList;

public class InventoryAdmin {
    private ArrayList<Furniture> furnitureDatabase = new ArrayList<>();

    public void addFurniture(PackingList p_packingList) {
        ArrayList<Furniture> productsToAdd = p_packingList.getProducts();
        ValidationUtils.validatesArrayList(productsToAdd, "Products");
        furnitureDatabase.addAll(productsToAdd);
        System.out.println("Added " + productsToAdd.size() + " items to the inventory.");
    }

    public void removeFurniture(PackingList p_packingList) {
        ArrayList<Furniture> productsToRemove = p_packingList.getProducts();
        ValidationUtils.validatesArrayList(productsToRemove, "Products");
        furnitureDatabase.removeAll(productsToRemove);
        System.out.println("Removed " + productsToRemove.size() + " items from the inventory.");
    }

    public void updateFurniture(PackingList p_packingList) {
        ArrayList<Furniture> productsToUpdate = p_packingList.getProducts();
        ValidationUtils.validatesArrayList(productsToUpdate, "Products");
        for (Furniture furniture : productsToUpdate) {
            updateFurnitureItem(furniture);
        }
        System.out.println("Updated inventory with " + productsToUpdate.size() + " items.");
    }

    private void updateFurnitureItem(Furniture p_furniture) {
        int index = furnitureDatabase.indexOf(p_furniture);
        ValidationUtils.validateNonNegativeNumber(index, "Furniture");
        furnitureDatabase.set(index, p_furniture);
        System.out.println("Updated furniture with ID: " + p_furniture.getFurnitureId());
    }
}