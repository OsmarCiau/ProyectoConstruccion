package Proyect.Inventory;

public class DimensionValidationUtils {
    public static void validateHeight(float p_height) {
        if (p_height < 0) {
            throw new IllegalArgumentException("Height cannot be negative.");
        }
    }

    public static void validateWidth(float p_width) {
        if (p_width < 0) {
            throw new IllegalArgumentException("Width cannot be negative.");
        }
    }

    public static void validateLength(float p_length) {
        if (p_length < 0) {
            throw new IllegalArgumentException("Length cannot be negative.");
        }
    }
}
