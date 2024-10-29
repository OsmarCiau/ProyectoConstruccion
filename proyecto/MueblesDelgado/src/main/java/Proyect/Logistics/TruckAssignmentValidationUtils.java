package Proyect.Logistics;

public class TruckAssignmentValidationUtils {

    public boolean validateDeliveryTruck(DeliveryTruck p_deliveryTruck) {
        if (p_deliveryTruck == null) {
            throw new IllegalArgumentException("DELIVERY TRUCK REQUIRED");
        }

        return true; // Si no lanza excepción, el camión es válido
    }

    public boolean validateTruckDriver(TruckDriver p_truckDriver) {
        if (p_truckDriver == null) {
            throw new IllegalArgumentException("TRUCK DRIVER REQUIRED");
        }
        return true; // Si no lanza excepción, el conductor es válido
    }

}
