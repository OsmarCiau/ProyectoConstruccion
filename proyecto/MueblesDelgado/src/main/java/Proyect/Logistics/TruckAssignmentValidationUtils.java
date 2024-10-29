package Proyect.Logistics;

public class TruckAssignmentValidationUtils {
    public void validateDeliveryTruck(DeliveryTruck p_deliveryTruck) {
        if (p_deliveryTruck == null) {
            throw new IllegalArgumentException("DELIVERY TRUCK REQUIRED");
        }
    }

    public void validateTruckDriver(TruckDriver p_truckDriver) {
        if (p_truckDriver == null) {
            throw new IllegalArgumentException("TRUCK DRIVER REQUIRED");
        }
    }
 

}
