package Logistics;

import java.util.ArrayList;
import java.util.HashMap;

public class DeliveryTruckAdmin {


    private ArrayList<DeliveryTruck> availableTrucks = new ArrayList<>();
    public void registerDeliveryTruck(int trackingNumber, float capacity,
                                      float mileage){
        DeliveryTruck deliveryTruck = new DeliveryTruck(trackingNumber, capacity, mileage);
        availableTrucks.add(deliveryTruck);
    }

    private ArrayList<TruckDriver> availableDrivers = new ArrayList<>();
    public void registerTruckDriver(String name, int licenseNumber){
        TruckDriver truckDriver = new TruckDriver(name, licenseNumber);
        availableDrivers.add(truckDriver);
    }

    public ArrayList<DeliveryTruck> getAvailableTrucks(){
        return availableTrucks;
    }

    public ArrayList<TruckDriver> getAvailableDrivers(){
        return availableDrivers;
    }

    private HashMap<DeliveryTruck, TruckDriver> deliveryTruckTruckDriverMap = new HashMap<>();
    public void assignDriverToTruck(int trackingNumber, String name){
        DeliveryTruck selectedTruck = findDeliveryTruck(trackingNumber);
        TruckDriver selectedDriver = findTruckDriver(name);

        if(selectedTruck != null && selectedDriver != null){
            deliveryTruckTruckDriverMap.put(selectedTruck,selectedDriver);
        }

    }

    private DeliveryTruck findDeliveryTruck(int trackingNumber){
        DeliveryTruck foundTruck = null;
        for(DeliveryTruck truck : availableTrucks){
            if(truck.getTrackingNumber() == trackingNumber){
                foundTruck = truck;
            }
        }

        return foundTruck;
    }

    private TruckDriver findTruckDriver(String name){
        TruckDriver foundDriver = null;
        for(TruckDriver driver : availableDrivers){
            if(driver.getName().equals(name)){
                foundDriver = driver;
            }
        }
        return foundDriver;
    }

    public HashMap<DeliveryTruck, TruckDriver> getDeliveryTruckTruckDriverMap() {
        return deliveryTruckTruckDriverMap;
    }
}
