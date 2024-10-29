package Proyect.Logistics;

import Proyect.StoreKeeper.Order;

import java.util.ArrayList;

public class LogisticsAdminValidationUtils {

    public void validateOrders(ArrayList<Order> p_orders){
        if(p_orders.isEmpty()){
            throw new IllegalArgumentException("NO CURRENT ORDERS");
        }
    }

    public void validateTrucks(ArrayList<DeliveryTruck> p_trucksAvailable){
        if(p_trucksAvailable.isEmpty()){
            throw new IllegalArgumentException("NO CURRENT TRUCKS");
        }
    }


}
