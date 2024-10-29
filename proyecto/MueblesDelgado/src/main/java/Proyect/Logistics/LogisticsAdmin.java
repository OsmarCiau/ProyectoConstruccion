package Proyect.Logistics;


import Proyect.StoreKeeper.Order;

import java.util.ArrayList;
import java.util.HashMap;

public class LogisticsAdmin {
    private ArrayList<Order> orders = new ArrayList<>();
    private ArrayList<DeliveryTruck> trucksAvailable = new ArrayList<>(); //nuevo atributo
    private RoutePlanner routePlanner;

    LogisticsAdminValidationUtils logisticsAdminValidator = new LogisticsAdminValidationUtils();

    public LogisticsAdmin(ArrayList<Order> p_orders, RoutePlanner p_routePlanner, ArrayList<DeliveryTruck> p_trucksAvailable){
        setOrders(p_orders);
        setRoutePlanner(p_routePlanner);
        setTrucksAvailable(p_trucksAvailable);
    }

    private void setOrders(ArrayList<Order> p_orders) {
        boolean ordersIsValid = logisticsAdminValidator.validateOrders(p_orders);
        if(ordersIsValid){
            this.orders = p_orders;
        }
    }


    public void setRoutePlanner(RoutePlanner p_routePlanner) {
        this.routePlanner = p_routePlanner;
    }

    private void setTrucksAvailable(ArrayList<DeliveryTruck> p_trucksAvailable){
        boolean truckAvailableIsValid = logisticsAdminValidator.validateTrucks(p_trucksAvailable);
        if(truckAvailableIsValid){
            this.trucksAvailable = p_trucksAvailable;
        }
    }


    public ArrayList<Order> getOrders() {
        return orders;
    }

    public RoutePlanner getRoutePlanner() {
        return routePlanner;
    }

    public ArrayList<DeliveryTruck> getTrucksAvailable(){
        return trucksAvailable;
    }

    public ArrayList<String> getDeliveryRoute(){
        ArrayList<String> deliveryRoute = routePlanner.planOptimalRoutes(getOrders(), getRoutePlanner().getWarehouseLocation() );
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