package StoreKeeper;

import Inventory.Dimension;


public class Platform {
    private Order order = null;
    private int platformID = 0;
    private StorageKeys locationInRack = null;
    private Dimension dimension = null;

    public Platform(){

    }

    public Platform(Order p_order, int p_platformID,  Dimension p_dimension) {
        assignOrder(p_order);
        setPlatformID(p_platformID);
        //setLocationInRack(locationInRack);
        setDimension(p_dimension);
    }

    public void setOrder(Order p_order) {
        PlatformValidationUtils.validateNonNull(p_order, "Order");
        PlatformValidationUtils.validateUnassignedOrder(this.order);
        this.order = p_order;
    }

    private void assignOrder(Order p_order) {
        setOrder(p_order);
        p_order.addPlatformUsed(this); //* relación bidireccional
    }

    public Order getOrder() {
        return this.order;
    }

    public void setPlatformID(int p_platformID) {
        PlatformValidationUtils.validatePlatformID(p_platformID);
        this.platformID = p_platformID;
    }

    public int getPlatformID() {
        return this.platformID;
    }

    public void setLocationInRack(StorageKeys p_locationInRack) {
        PlatformValidationUtils.validateLocationInRack(p_locationInRack);
        this.locationInRack = p_locationInRack;
    }

    public StorageKeys getLocationInRack() {
        return this.locationInRack;
    }

    public void setDimension(Dimension p_dimension) {
        PlatformValidationUtils.validateNonNull(p_dimension, "Dimension");
        this.dimension = p_dimension;
    }

    public Dimension getDimension() {
        return this.dimension;
    }
}
