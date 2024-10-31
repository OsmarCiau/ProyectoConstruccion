package Proyect.StoreKeeper;

import java.util.ArrayList;
import java.util.Date;
import Proyect.Inventory.Furniture;
import Proyect.Validations.ValidationUtils;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.Duration;

public class Order {
    private int orderID = 0;
    private String destination = null;
    private ArrayList<Platform> platformUsed = new ArrayList<>();
    private Date deliveryDate = new Date();
    private ArrayList<Furniture> orderContent = new ArrayList<>();
    private Duration totalAssemblyTime = Duration.ZERO;

    public Order() {
    }

    public Order(int p_orderID, String  p_destination, Date  p_deliveryDate, ArrayList<Furniture>  p_orderContent) { //ArrayList<Platform> p_platformUsed
        setOrderID(p_orderID);
        setDestination(p_destination);
        setDeliveryDate(p_deliveryDate);
        setOrderContent(p_orderContent);
        calculateAssemblyTime();
    }

    public void findPlatforms() {
        for (Platform platform : platformUsed) {
            ValidationUtils.validateNonNull(platform.getLocationInRack(), "Location In Rack");
            System.out.println("Platform ID: " + platform.getPlatformID());
            System.out.println("Location in Rack: " + platform.getLocationInRack());
            System.out.println("Dimension: " + platform.getDimension());
        }
    }

    private void calculateAssemblyTime() {
        Duration totalAssemblyTime = Duration.ZERO;
        for (Furniture furniture : orderContent) {
            totalAssemblyTime = totalAssemblyTime.plus(calculateFurnitureBuildTime(furniture));
        }
        setTotalAssemblyTime(totalAssemblyTime);
    }

    private Duration calculateFurnitureBuildTime(Furniture  p_furniture) {
        int buildTimeMinutes =  p_furniture.getBuildTime() *  p_furniture.getQuantity();
        return Duration.ofMinutes(buildTimeMinutes);
    }

    public void addPlatformUsed(Platform p_platform) {
        platformUsed.add(p_platform);
    }

    public Duration getTotalAssemblyTime() {
        return totalAssemblyTime;
    }

    public void setTotalAssemblyTime(Duration p_assemblyTime){
        ValidationUtils.validateNonNull(p_assemblyTime, "Assembly Time");
        this.totalAssemblyTime = p_assemblyTime;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int p_orderID) {
        ValidationUtils.validatePositiveNumber(p_orderID, "Order ID");
        //OrderValidationUtils.validateOrderID(p_orderID);
        this.orderID =  p_orderID;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String  p_destination) {
        ValidationUtils.validateString(p_destination, "Destination");
        //OrderValidationUtils.validateDestination(p_destination);
        this.destination =  p_destination;
    }

    public ArrayList<Platform> getPlatformUsed() {
        return platformUsed;
    }

    public void setPlatformUsed(ArrayList<Platform>  p_platformUsed) {
        ValidationUtils.validatesArrayList(p_platformUsed, "Platform Used");
        this.platformUsed =  p_platformUsed;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date  p_deliveryDate) {
        ValidationUtils.validateNonNull(p_deliveryDate, "Delivery Date");
        //OrderValidationUtils.validateNonNull(p_deliveryDate, "Delivery Date");
        this.deliveryDate =  p_deliveryDate;
    }

    public ArrayList<Furniture> getOrderContent() {
        return orderContent;
    }

    public void setOrderContent(ArrayList<Furniture>  p_orderContent) {
        ValidationUtils.validateNonNull(p_orderContent, "Order Content");
        this.orderContent =  p_orderContent;
    }
}