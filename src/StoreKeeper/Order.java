package StoreKeeper;

import java.util.ArrayList;
import java.util.Date;

import Inventory.Furniture;

import java.time.Duration;

public class Order {
    private int orderID;
    private String destination;
    private ArrayList<Platform> platformUsed;
    private Date deliveryDate;
    private Duration assemblyTime;
    private ArrayList<Furniture> orderContent;

    public Order(int orderID, String destination, Date deliveryDate, ArrayList<Furniture> orderContent) {
        this.orderID = orderID;
        this.destination = destination;
        this.deliveryDate = deliveryDate;
        this.orderContent = orderContent;
        setAssemblyTime();
    }

    public void findPlatforms() {
        //! Duda con esto
        // return platformUsed;
    }

    public Duration getTotalAssemblyTime() {
        return assemblyTime;
    }

    private void setAssemblyTime() {
        Duration assemblyTime = Duration.ofMinutes(0);
        for (Furniture furniture : this.orderContent) {
            int buildTimeMinutes = furniture.getBuildTime();
            Duration buildTimeDuration = Duration.ofMinutes(buildTimeMinutes);
            assemblyTime = assemblyTime.plus(buildTimeDuration);
        }
        this.assemblyTime = assemblyTime;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public ArrayList<Platform> getPlatformUsed() {
        return platformUsed;
    }

    public void setPlatformUsed(ArrayList<Platform> platformUsed) {
        this.platformUsed = platformUsed;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public ArrayList<Furniture> getOrderContent() {
        return orderContent;
    }

    public void setOrderContent(ArrayList<Furniture> orderContent) {
        this.orderContent = orderContent;
    }
}