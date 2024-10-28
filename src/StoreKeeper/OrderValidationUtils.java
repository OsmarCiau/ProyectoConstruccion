package StoreKeeper;

public class OrderValidationUtils {
    public static void validateOrderID(int orderID) {
        if (orderID <= 0) {
            throw new IllegalArgumentException("Order ID must be positive.");
        }
    }

    public static void validateDestination(String destination) {
        if (destination == null || destination.isEmpty()) {
            throw new IllegalArgumentException("Destination cannot be null or empty.");
        }
    }

    public static void validateNonNull(Object value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " cannot be null.");
        }
    }
}
