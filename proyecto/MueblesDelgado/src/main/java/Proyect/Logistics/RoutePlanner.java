package Proyect.Logistics;

import Proyect.StoreKeeper.Order;

import java.time.LocalTime;
import java.util.ArrayList;

public class RoutePlanner {
    private String a_warehouseLocation = null;
    private LocalTime a_startTime = LocalTime.of(0, 0);

    public RoutePlanner(String p_wareHouseLocation, LocalTime p_startTime){
        setWarehouseLocation(p_wareHouseLocation);


    }

    private void setWarehouseLocation(String p_warehouseLocation) {
        boolean warehouseLocationIsValid = validateWarehouseLocation(p_warehouseLocation);
        if(warehouseLocationIsValid){
            this.a_warehouseLocation = p_warehouseLocation;
        }
    }

    private boolean validateWarehouseLocation(String p_warehouseLocation){
        boolean isValid = false;
        if(p_warehouseLocation != null){
            isValid = true;
        }else{
            throw new IllegalArgumentException("WAREHOUSE LOCATION REQUIRED");
        }
        return isValid;

    }
    private void setStartTime(LocalTime p_startTime) {
        boolean startTimeIsValid = validateStartTime(p_startTime);
        if(startTimeIsValid){
            this.a_startTime = p_startTime;
        }

    }

    private boolean validateStartTime(LocalTime p_startTime){
        boolean isValid = false;
        LocalTime minimumTime = LocalTime.of(8,0);

        if(p_startTime.isAfter(minimumTime)){
            isValid = true;
        }else{
            throw new IllegalArgumentException("START TIME MUST BE AFTER 8:00 AM");
        }

        return isValid;
    }

    public String getWarehouseLocation() {
        return a_warehouseLocation;
    }

    public LocalTime getStartTime() {
        return a_startTime;
    }

    public LocalTime estimateDeliveryTimeForOrder(Order p_order){
        //TO-DO
        LocalTime time = LocalTime.of(0,0);
        return time;
    }

    public ArrayList<String> planOptimalRoutes(ArrayList<Order> p_order, String p_wareHouseLocation){
        //TO-DO
        ArrayList<String> optimalRoute = new ArrayList<>();
        return optimalRoute;
    }

    public LocalTime routeDuration(){
        //TO-DO
        LocalTime time = LocalTime.of(0,0);
        return time;
    }

    @Override
    public String toString() {
        return "RoutePlanner" +
                "Warehouse Location" + getWarehouseLocation() + '\'' +
                ", Start Time" + getStartTime() ;
    }
}