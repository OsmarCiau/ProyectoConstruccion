package Proyect.StoreKeeper;

public class CellValidationUtils {
    public static void validateOrderID(int orderID) {
        if (orderID <= 0) {
            throw new IllegalArgumentException("Order ID must be positive.");
        }
    }
}
