package Inventory;

import java.util.ArrayList;
import java.util.Date;

public class PackingListValidationUtils {
    public void validateFolio(int p_folio) {
        if (p_folio <= 0) {
            throw new IllegalArgumentException("Folio cannot be negative.");
        }
    }

    public void validateArrivalDate(Date p_arrivalDate) {
        if (p_arrivalDate == null) {
            throw new IllegalArgumentException("Error: arrivalDate cannot be null.");
        }
    }

    public void isPackingListEmpty(ArrayList<Furniture> p_products) {
        if (p_products == null || p_products.isEmpty()) {
            throw new IllegalArgumentException("Error: PackingList is empty or null.");
        }
    }
}
