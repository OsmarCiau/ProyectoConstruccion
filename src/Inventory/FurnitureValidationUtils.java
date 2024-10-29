package Inventory;

public class FurnitureValidationUtils {
    public boolean validateNonNegative(int p_value, String p_fieldName) {
        boolean isValid = false;

        if (p_value > 0) {
            isValid = true;
        } else {
            throw new IllegalArgumentException(p_fieldName + " cannot be negative.");
        }

        return isValid;
    }

    public boolean validateQuantity(int p_quantity) {
        boolean isValid = false;

        if (p_quantity >= 0) {
            isValid = true;
        } else {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }

        return isValid;
    }

    public boolean validateNonNull(String p_type, String p_fieldName) {
        boolean isValid = false;

        if (p_type == null || p_type.isEmpty()) {
            throw new IllegalArgumentException(p_fieldName + " cannot be null.");
        } else {
            isValid = true;
        }

        return isValid;
    }

    public boolean validateDimension(Dimension p_dimension) {
        boolean isValid = false;

        if (p_dimension == null) {
            throw new IllegalArgumentException("Error: Dimension cannot be null.");
        } else {
            isValid = true;
        }

        return isValid;
    }
}
