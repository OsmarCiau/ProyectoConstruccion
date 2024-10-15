package proyecto.mueblesdelgado.Inventory;

import java.util.ArrayList;

public class InventoryAdmin {

    // Base de datos simulada para almacenar muebles
    private ArrayList<Furniture> furnitureDatabase = new ArrayList<>();

    public void addFurniture(PackingList packingList) {
        ArrayList<Furniture> productsToAdd = packingList.getProducts();
        if (productsToAdd != null && !productsToAdd.isEmpty()) {
            furnitureDatabase.addAll(productsToAdd);
            System.out.println("Added " + productsToAdd.size() + " items to the inventory.");
        } else {
            System.out.println("PackingList is empty or null.");
        }
    }

    public void removeFurniture(PackingList packingList) {
        ArrayList<Furniture> productsToRemove = packingList.getProducts();
        if (productsToRemove != null && !productsToRemove.isEmpty()) {
            furnitureDatabase.removeAll(productsToRemove);
            System.out.println("Removed " + productsToRemove.size() + " items from the inventory.");
        } else {
            System.out.println("PackingList is empty or null.");
        }
    }

    public void updateFurniture(PackingList packingList) {
        ArrayList<Furniture> productsToUpdate = packingList.getProducts();
        if (productsToUpdate != null && !productsToUpdate.isEmpty()) {

            for (Furniture furniture : productsToUpdate) {
                updateFurnitureItem(furniture);
            }
            System.out.println("Updated inventory with " + productsToUpdate.size() + " items.");
        } else {
            System.out.println("PackingList is empty or null.");
        }
    }

    // Método para actualizar un mueble específico
    /*AGREGAR AL DIAGRAMA DE CLASES */
    private void updateFurnitureItem(Furniture furniture) {
        // Implementar la lógica para encontrar el mueble en la base de datos
        // Verificar si el ID del mueble existe en la base de datos.

        // Ejemplo simplificado:
        int index = furnitureDatabase.indexOf(furniture); // Asume que `equals` está sobreescrito en Furniture
        if (index >= 0) {
            furnitureDatabase.set(index, furniture); // Actualiza el mueble
            System.out.println("Updated furniture with ID: " + furniture.getFurnitureId());
        } else {
            System.out.println("Furniture with ID " + furniture.getFurnitureId() + " does not exist in the inventory.");
        }
    }

}
