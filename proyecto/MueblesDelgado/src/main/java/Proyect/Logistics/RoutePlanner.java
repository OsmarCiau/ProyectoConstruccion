package Proyect.Logistics;

import Proyect.StoreKeeper.Order;

import java.time.LocalTime;
import java.util.ArrayList;

public class RoutePlanner {
    private String warehouseLocation = null;
    private LocalTime startTime = LocalTime.of(0, 0);

    RoutePlannerValidationUtils routePlannerValidator = new RoutePlannerValidationUtils();


    public RoutePlanner(String p_wareHouseLocation, LocalTime p_startTime){
        setWarehouseLocation(p_wareHouseLocation);
        setStartTime(p_startTime);
    }

    private void setWarehouseLocation(String p_warehouseLocation) {
        boolean warehouseLocationIsValid = routePlannerValidator.validateWarehouseLocation(p_warehouseLocation);
        if(warehouseLocationIsValid){
            this.warehouseLocation = p_warehouseLocation;
        }
    }


    private void setStartTime(LocalTime p_startTime) {
        boolean startTimeIsValid = routePlannerValidator.validateStartTime(p_startTime);
        if(startTimeIsValid){
            this.startTime = p_startTime;
        }

    }

    public String getWarehouseLocation() {
        return warehouseLocation;
    }

    public LocalTime getStartTime() {
        return startTime;
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
