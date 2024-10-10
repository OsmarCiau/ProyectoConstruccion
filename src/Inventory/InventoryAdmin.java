package Inventory;

import java.util.ArrayList;

public class InventoryAdmin {

    /* Se comentan las líneas que manejan la BD para evitar errores por el momento hasta concretar la BD */

    public void addFurniture(PackingList packingList) {
        ArrayList<Furniture> productsToAdd = packingList.getProducts();
        if (productsToAdd != null && !productsToAdd.isEmpty()) {
            // furnitureDatabase.addAll(productsToAdd);
            System.out.println("Added " + productsToAdd.size() + " items to the inventory.");
        } else {
            System.out.println("PackingList is empty or null.");
        }
    }

    public void removeFurtinure(PackingList packingList) {
        ArrayList<Furniture> productsToRemove = packingList.getProducts();
        if (productsToRemove != null && !productsToRemove.isEmpty()) {
            // furnitureDatabase.removeAll(productsToRemove);
            System.out.println("Removed " + productsToRemove.size() + " items from the inventory.");
        } else {
            System.out.println("PackingList is empty or null.");
        }
    }

    public void updateFurniture(PackingList packingList) {
        ArrayList<Furniture> productsToUpdate = packingList.getProducts();
        if (productsToUpdate != null && !productsToUpdate.isEmpty()) {
            removeFurtinure(packingList);
            addFurniture(packingList);  
            System.out.println("Updated inventory with " + productsToUpdate.size() + " items.");
        } else {
            System.out.println("PackingList is empty or null.");
        }
    }

}
