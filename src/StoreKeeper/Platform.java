package StoreKeeper;

import Inventory.Dimension;


public class Platform {
    private Order a_order ;
    private int a_platformID;
    private StorageKeys a_locationInRack;
    private Dimension a_dimension;

    public Platform(){

    }

    public Platform(Order order, int platformID,  Dimension dimension) { //storageKeys locationInRack,
        setOrder(order);
        setPlatformID(platformID);
        //setLocationInRack(locationInRack);
        setDimension(dimension);
    }

    public void setOrder(Order p_order) {
        PlatformValidationUtils.validateNonNull(p_order, "Order");
        this.a_order = p_order;
    }

    public Order getOrder() {
        return a_order;
    }

    public void setPlatformID(int p_platformID) {
        PlatformValidationUtils.validatePlatformID(p_platformID);
        this.a_platformID = p_platformID;
    }

    public int getPlatformID() {
        return a_platformID;
    }

    public void setLocationInRack(StorageKeys p_locationInRack) {
        PlatformValidationUtils.validateLocationInRack(p_locationInRack);
        this.a_locationInRack = p_locationInRack;
    }

    public StorageKeys getLocationInRack() {
        return a_locationInRack;
    }

    public void setDimension(Dimension p_dimension) {
        PlatformValidationUtils.validateNonNull(p_dimension, "Dimension");
        this.a_dimension = p_dimension;
    }

    public Dimension getDimension() {
        return a_dimension;
    }
}
