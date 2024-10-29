package Inventory;

import java.util.ArrayList;

public class InventoryAdminValidationUtils {
    public boolean validateFurnitureIndex(int p_index, int p_furnitureId) {
        boolean isValid = false;

        if (p_index >= 0) {
            isValid = true;
        } else {
            throw new IllegalArgumentException("Furniture index cannot be negative.");
        }

        return isValid;
    }

    public boolean isPackingListEmpty(ArrayList<Furniture> p_products) {
        boolean isValid = false;

        if (p_products == null || p_products.isEmpty()) {
            throw new IllegalArgumentException("Error: PackingList is empty or null.");
        } else {
            isValid = true;
        }

        return isValid;
    }
}
