package Inventory;

import java.util.ArrayList;
import java.util.Date;

public class PackingListValidationUtils {
    public static void validateFolio(int p_folio) {
        if (p_folio <= 0) {
            throw new IllegalArgumentException("Error: folio must be a positive number.");
        }
    }

    public static void validateFurnitureIndex(int p_index, int p_furnitureId) {
        if (p_index < 0) {
            throw new IllegalArgumentException("Furniture with ID " + p_furnitureId + " does not exist in the inventory.");
        }
    }

    public static void validateArrivalDate(Date p_arrivalDate) {
        if (p_arrivalDate == null) {
            throw new IllegalArgumentException("Error: arrivalDate cannot be null.");
        }
    }

    public static void isPackingListNotEmpty(ArrayList<Furniture> p_products) {
        if (p_products == null || p_products.isEmpty()) {
            throw new IllegalArgumentException("Error: PackingList is empty or null.");
        }
    }
}
