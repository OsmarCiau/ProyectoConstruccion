package Inventory;

public class DimensionValidationUtils {
    public void validateHeight(float p_height) {
        if (p_height <= 0) {
            throw new IllegalArgumentException("Height cannot be negative.");
        }
    }

    public void validateWidth(float p_width) {
        if (p_width <= 0) {
            throw new IllegalArgumentException("Width cannot be negative.");
        }
    }

    public void validateLength(float p_length) {
        if (p_length <= 0) {
            throw new IllegalArgumentException("Length cannot be negative.");
        }
    }
}
