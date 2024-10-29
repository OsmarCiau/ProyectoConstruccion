package Proyect.Logistics;


import Proyect.StoreKeeper.Order;

import java.util.ArrayList;
import java.util.HashMap;

public class LogisticsAdmin {
    private ArrayList<Order> a_orders = new ArrayList<>();
    private ArrayList<DeliveryTruck> a_trucksAvailable = new ArrayList<>(); //nuevo atributo
    private RoutePlanner a_routePlanner;


    public LogisticsAdmin(ArrayList<Order> p_orders, RoutePlanner p_routePlanner, ArrayList<DeliveryTruck> p_trucksAvailable){
        setOrders(p_orders);
        setRoutePlanner(p_routePlanner);
        setTrucksAvailable(p_trucksAvailable);
    }

    private void setOrders(ArrayList<Order> p_orders) {
        boolean ordersIsValid = validateOrders(p_orders);
        if(ordersIsValid){
            this.a_orders = p_orders;
        }
    }

    private boolean validateOrders(ArrayList<Order> p_orders){
        boolean isValid = false;
        if(p_orders.isEmpty()){
            throw new IllegalArgumentException("NO CURRENT ORDERS");
        }else{
            isValid = true;
        }
        return isValid;
    }

    public void setRoutePlanner(RoutePlanner p_routePlanner) {
        this.a_routePlanner = p_routePlanner;
    }

    private void setTrucksAvailable(ArrayList<DeliveryTruck> p_trucksAvailable){
        boolean truckAvailableIsValid = validateTrucks(p_trucksAvailable);
        if(truckAvailableIsValid){
            this.a_trucksAvailable = p_trucksAvailable;
        }
    }

    private boolean validateTrucks(ArrayList<DeliveryTruck> p_trucksAvailable){
        boolean isValid = false;
        if(p_trucksAvailable.isEmpty()){
            throw new IllegalArgumentException("NO CURRENT TRUCKS");
        }else{
            isValid = true;
        }
        return isValid;
    }

    public ArrayList<Order> getOrders() {
        return a_orders;
    }

    public RoutePlanner getRoutePlanner() {
        return a_routePlanner;
    }

    public ArrayList<DeliveryTruck> getTrucksAvailable(){
        return a_trucksAvailable;
    }

    public ArrayList<String> getDeliveryRoute(){
        ArrayList<String> deliveryRoute = a_routePlanner.planOptimalRoutes(getOrders(), getRoutePlanner().getWarehouseLocation() );
        return deliveryRoute;
    }

    private HashMap<Order, DeliveryTruck> orderDeliveryTruckHashMap;
    public void assignOrderToTruck(int p_orderId, int p_trackingNumber){
        Order orderSelected = findOrder(p_orderId);
        DeliveryTruck deliveryTruckSelected = findTruck(p_trackingNumber);

        if(orderSelected != null && deliveryTruckSelected != null){
            orderDeliveryTruckHashMap.put(orderSelected, deliveryTruckSelected);
        }

    }

    public HashMap<Order, DeliveryTruck> getOrderDeliveryTruckHashMap(){
        return orderDeliveryTruckHashMap;
    }

    private Order findOrder(int p_orderId){
        Order foundOrder = null;
        for (Order order : getOrders()){
            if(order.getOrderID() == p_orderId){
                foundOrder = order;
            }

        }
        return foundOrder;
    }

    private DeliveryTruck findTruck(int p_trackingNumber){
        DeliveryTruck foundTruck = null;
        for(DeliveryTruck truck : getTrucksAvailable()){
            if(truck.getTrackingNumber() == p_trackingNumber){
                foundTruck = truck;
            }
        }

        return foundTruck;
    }
}
