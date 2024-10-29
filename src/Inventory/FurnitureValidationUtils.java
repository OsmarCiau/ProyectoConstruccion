package Inventory;

public class FurnitureValidationUtils {
    public void validateNonNegative(int p_value, String p_fieldName) {
        if (p_value <= 0) {
            throw new IllegalArgumentException(p_fieldName + " cannot be negative.");
        }
    }

    public void validateQuantity(int p_quantity) {
        if (p_quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
    }

    public void validateNonNull(String p_type, String p_fieldName) {
        if (p_type == null || p_type.isEmpty()) {
            throw new IllegalArgumentException(p_fieldName + " cannot be null.");
        }
    }

    public void validateDimension(Dimension p_dimension) {
        if (p_dimension == null) {
            throw new IllegalArgumentException("Error: Dimension cannot be null.");
        }
    }
}
