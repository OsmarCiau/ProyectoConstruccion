package Inventory;

import java.util.ArrayList;
import java.util.Date;

public class PackingListValidationUtils {
    public boolean validateFolio(int p_folio) {
        boolean isValid = false;

        if (p_folio > 0) {
            isValid = true;
        } else {
            throw new IllegalArgumentException("Folio cannot be negative.");
        }

        return isValid;
    }

    public boolean validateArrivalDate(Date p_arrivalDate) {
        boolean isValid = false;

        if (p_arrivalDate == null) {
            throw new IllegalArgumentException("Error: arrivalDate cannot be null.");
        } else {
            isValid = true;
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
