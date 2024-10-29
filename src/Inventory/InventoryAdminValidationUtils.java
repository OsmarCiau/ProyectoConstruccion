package Inventory;

import java.util.ArrayList;

public class InventoryAdminValidationUtils {
    public void validateFurnitureIndex(int p_index) {
        if (p_index < 0) {
            throw new IllegalArgumentException("Furniture index cannot be negative.");
        }
    }

    public void isPackingListEmpty(ArrayList<Furniture> p_products) {
        if (p_products == null || p_products.isEmpty()) {
            throw new IllegalArgumentException("Error: PackingList is empty or null.");
        }
    }
}
