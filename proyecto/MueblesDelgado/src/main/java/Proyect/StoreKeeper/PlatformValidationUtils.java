package Proyect.StoreKeeper;

public class PlatformValidationUtils {

    public static void validateNonNull(Object p_value, String p_fieldName) {
        if (p_value == null) {
            throw new IllegalArgumentException(p_fieldName + " cannot be null.");
        }
    }

    public static void validateLocationInRack(StorageKeys p_locationInRack){
        if (p_locationInRack.getRackNumber() == 0 || p_locationInRack.getCellNumber() == 0) {
            throw new IllegalArgumentException("Location in rack cannot be null or empty");
        }
    }

    public static void validatePlatformID(int p_platformID){
        if (p_platformID <= 0) {
            throw new IllegalArgumentException("Platform ID must be positive");
        }
    }

    public static void validateUnassignedOrder(Order p_order) {
        if (p_order != null) {
            throw new IllegalStateException("Platform is already assigned to an order.");
        }
    }

}