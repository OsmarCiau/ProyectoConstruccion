package Proyect.Logistics;

import Proyect.StoreKeeper.Order;

import java.util.ArrayList;

public class LogisticsAdminValidationUtils {

    public boolean validateOrders(ArrayList<Order> p_orders){
        boolean isValid = false;
        if(p_orders.isEmpty()){
            throw new IllegalArgumentException("NO CURRENT ORDERS");
        }else{
            isValid = true;
        }
        return isValid;
    }

    public boolean validateTrucks(ArrayList<DeliveryTruck> p_trucksAvailable){
        boolean isValid = false;
        if(p_trucksAvailable.isEmpty()){
            throw new IllegalArgumentException("NO CURRENT TRUCKS");
        }else{
            isValid = true;
        }
        return isValid;
    }

}
