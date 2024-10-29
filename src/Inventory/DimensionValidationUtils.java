package Inventory;

public class DimensionValidationUtils {
    public boolean validateHeight(float p_height) {
        boolean isValid = false;

        if (p_height > 0) {
            isValid = true;
        } else {
            throw new IllegalArgumentException("Height cannot be negative.");
        }

        return isValid;
    }

    public boolean validateWidth(float p_width) {
        boolean isValid = false;

        if (p_width > 0) {
            isValid = true;
        } else {
            throw new IllegalArgumentException("Width cannot be negative.");
        }

        return isValid;
    }

    public boolean validateLength(float p_length) {
        boolean isValid = false;

        if (p_length > 0) {
            isValid = true;
        } else {
            throw new IllegalArgumentException("Length cannot be negative.");
        }

        return isValid;
    }
}
