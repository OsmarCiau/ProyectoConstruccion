package StoreKeeper;

import java.util.ArrayList;
import java.util.Date;
import Inventory.Furniture;
import java.time.Duration;

public class Order {
    private int a_orderID = 0;
    private String a_destination = "";
    private ArrayList<Platform> a_platformUsed = new ArrayList<>();
    private Date a_deliveryDate = new Date();
    private ArrayList<Furniture> a_orderContent = new ArrayList<>();
    private Duration a_totalAssemblyTime = Duration.ZERO;

    public Order(int p_orderID, String  p_destination, Date  p_deliveryDate, ArrayList<Furniture>  p_orderContent) { //ArrayList<Platform> p_platformUsed
        setOrderID(p_orderID);
        setDestination(p_destination);
        setDeliveryDate(p_deliveryDate);
        setOrderContent(p_orderContent);
        calculateAssemblyTime();
    }

    public void findPlatforms() {
        for (Platform platform : a_platformUsed) {
            System.out.println("Platform ID: " + platform.getPlatformID());
            System.out.println("Location in Rack: " + platform.getLocationInRack());
            System.out.println("Dimension: " + platform.getDimension());
        }
    }

    private void calculateAssemblyTime() {
        Duration totalAssemblyTime = Duration.ZERO;
        for (Furniture furniture : a_orderContent) {
            totalAssemblyTime = totalAssemblyTime.plus(calculateFurnitureBuildTime(furniture));
        }
        setTotalAssemblyTime(totalAssemblyTime);
    }

    private Duration calculateFurnitureBuildTime(Furniture  p_furniture) {
        int buildTimeMinutes =  p_furniture.getBuildTime() *  p_furniture.getQuantity();
        return Duration.ofMinutes(buildTimeMinutes);
    }

    public Duration getTotalAssemblyTime() {
        return a_totalAssemblyTime;
    }

    public void setTotalAssemblyTime(Duration p_assemblyTime){
        OrderValidationUtils.validateNonNull(p_assemblyTime, "Assembly Time");
        this.a_totalAssemblyTime = p_assemblyTime;
    }

    public int getOrderID() {
        return a_orderID;
    }

    public void setOrderID(int p_orderID) {
        OrderValidationUtils.validateOrderID(p_orderID);
        this.a_orderID =  p_orderID;
    }

    public String getDestination() {
        return a_destination;
    }

    public void setDestination(String  p_destination) {
        OrderValidationUtils.validateDestination(p_destination);
        this.a_destination =  p_destination;
    }

    public ArrayList<Platform> getPlatformUsed() {
        return a_platformUsed;
    }

    public void setPlatformUsed(ArrayList<Platform>  p_platformUsed) {
        OrderValidationUtils.validateNonNull(p_platformUsed, "Platform Used");
        this.a_platformUsed =  p_platformUsed;
    }

    public Date getDeliveryDate() {
        return a_deliveryDate;
    }

    public void setDeliveryDate(Date  p_deliveryDate) {
        OrderValidationUtils.validateNonNull(p_deliveryDate, "Delivery Date");
        this.a_deliveryDate =  p_deliveryDate;
    }

    public ArrayList<Furniture> getOrderContent() {
        return a_orderContent;
    }

    public void setOrderContent(ArrayList<Furniture>  p_orderContent) {
        OrderValidationUtils.validateNonNull(p_orderContent, "Order Content");
        this.a_orderContent =  p_orderContent;
    }
}