package Proyect.Logistics;

import java.util.ArrayList;
import java.util.HashMap;

//refactorizar los gets para no regresar la lista
public class DeliveryTruckAdmin {

    private ArrayList<DeliveryTruck> availableTrucks = new ArrayList<>();
    public void registerDeliveryTruck(int p_trackingNumber, float p_capacity, float p_mileage){
        DeliveryTruck deliveryTruck = new DeliveryTruck(p_trackingNumber, p_capacity, p_mileage);
        availableTrucks.add(deliveryTruck);
    }

    private ArrayList<TruckDriver> availableDrivers = new ArrayList<>();
    public void registerTruckDriver(String p_name, int p_licenseNumber){
        TruckDriver truckDriver = new TruckDriver(p_name, p_licenseNumber);
        availableDrivers.add(truckDriver);
    }

    public ArrayList<DeliveryTruck> getAvailableTrucks(){
        return availableTrucks;
    }

    public ArrayList<TruckDriver> getAvailableDrivers(){
        return availableDrivers;
    }

    private HashMap<DeliveryTruck, TruckDriver> deliveryTruckTruckDriverMap = new HashMap<>();
    public void assignDriverToTruck(int p_trackingNumber, String p_name){
        DeliveryTruck selectedTruck = findDeliveryTruck(p_trackingNumber);
        TruckDriver selectedDriver = findTruckDriver(p_name);

        if(selectedTruck != null && selectedDriver != null){
            deliveryTruckTruckDriverMap.put(selectedTruck,selectedDriver);
        }

    }

    private DeliveryTruck findDeliveryTruck(int p_trackingNumber){
        DeliveryTruck foundTruck = null;
        for(DeliveryTruck truck : availableTrucks){
            if(truck.getTrackingNumber() == p_trackingNumber){
                foundTruck = truck;
            }
        }

        return foundTruck;
    }

    private TruckDriver findTruckDriver(String p_name){
        TruckDriver foundDriver = null;
        for(TruckDriver driver : availableDrivers){
            if(driver.getName().equals(p_name)){
                foundDriver = driver;
            }
        }
        return foundDriver;
    }

    public HashMap<DeliveryTruck, TruckDriver> getDeliveryTruckTruckDriverMap() {
        return deliveryTruckTruckDriverMap;
    }
}