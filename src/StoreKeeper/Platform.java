package StoreKeeper;

import java.util.Map;

import Inventory.Dimension;


public class Platform {
    private Order order;
    private int platformID;
    private Map<Integer, Integer> locationInRack;
    private Dimension dimension;

    public Platform(Order order, int platformID, Map<Integer, Integer> locationInRack, Dimension dimension) {
        setOrder(order);
        setPlatformID(platformID);
        setLocationInRack(locationInRack);
        setDimension(dimension);
    }

    public Map<Integer, Integer> getLocation() {
        return locationInRack;
    }

    public void setOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        this.order = order;
    }

    public void setPlatformID(int platformID) {
        if (platformID <= 0) {
            throw new IllegalArgumentException("Platform ID must be positive");
        }
        this.platformID = platformID;
    }

    public void setLocationInRack(Map<Integer, Integer> locationInRack) {
        if (locationInRack == null || locationInRack.isEmpty()) {
            throw new IllegalArgumentException("Location in rack cannot be null or empty");
        }
        this.locationInRack = locationInRack;
    }

    public void setDimension(Dimension dimension) {
        if (dimension == null) {
            throw new IllegalArgumentException("Dimension cannot be null");
        }
        this.dimension = dimension;
    }

    public int getPlatformID() {
        return platformID;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public Order getOrder() {
        return order;
    }
}
