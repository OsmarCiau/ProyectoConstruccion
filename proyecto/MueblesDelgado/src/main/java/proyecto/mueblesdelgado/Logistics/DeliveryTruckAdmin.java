package proyecto.mueblesdelgado.Logistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DeliveryTruckAdmin {

    private ArrayList<DeliveryTruck> availableTrucks = new ArrayList<>();
    private ArrayList<TruckDriver> availableDrivers = new ArrayList<>();
    private Map<DeliveryTruck, TruckDriver> deliveryTruckDriverMap = new HashMap<>();

    public void registerDeliveryTruck(int trackingNumber, float capacity, float mileage) {
        DeliveryTruck deliveryTruck = new DeliveryTruck(trackingNumber, capacity, mileage);
        availableTrucks.add(deliveryTruck);
    }

    public void registerTruckDriver(String name, int licenseNumber) {
        TruckDriver truckDriver = new TruckDriver(name, licenseNumber);
        availableDrivers.add(truckDriver);
    }

    // Obtiene todos los camiones disponibles
    public ArrayList<DeliveryTruck> getAvailableTrucks() {
        return availableTrucks;
    }


    public ArrayList<TruckDriver> getAvailableDrivers() {
        return availableDrivers;
    }

    public void assignDriverToTruck(int trackingNumber, String driverName) {
        DeliveryTruck selectedTruck = findDeliveryTruck(trackingNumber);
        TruckDriver selectedDriver = findTruckDriver(driverName);

        if (selectedTruck != null && selectedDriver != null) {
            deliveryTruckDriverMap.put(selectedTruck, selectedDriver);
        } else {
            throw new IllegalArgumentException("Error: Truck or driver not found.");
        }
    }

    private DeliveryTruck findDeliveryTruck(int trackingNumber) {
        for (DeliveryTruck truck : availableTrucks) {
            if (truck.getTrackingNumber() == trackingNumber) {
                return truck; // Retorna el camión encontrado
            }
        }
        return null; // Retorna null si no se encuentra
    }

    private TruckDriver findTruckDriver(String name) {
        for (TruckDriver driver : availableDrivers) {
            if (driver.getName().equalsIgnoreCase(name)) {
                return driver; // Retorna el conductor encontrado
            }
        }
        return null;
    }

    // Obtiene el mapa de camiones y conductores asignados
    public Map<DeliveryTruck, TruckDriver> getDeliveryTruckDriverMap() {
        return deliveryTruckDriverMap;
    }
}
