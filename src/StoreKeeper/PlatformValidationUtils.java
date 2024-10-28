package StoreKeeper;

public class PlatformValidationUtils {

    public static void validateNonNull(Object value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " cannot be null.");
        }
    }

    public static void validateLocationInRack(StorageKeys locationInRack){
        if (locationInRack.getRackNumber() == 0 || locationInRack.getCellNumber() == 0) {
            throw new IllegalArgumentException("Location in rack cannot be null or empty");
        }
    }

    public static void validatePlatformID(int platformID){
        if (platformID <= 0) {
            throw new IllegalArgumentException("Platform ID must be positive");
        }
    }

    public static void validateUnassignedOrder(Order order) {
        if (order != null) {
            throw new IllegalStateException("Platform is already assigned to an order.");
        }
    }
    
}
