package StoreKeeper;

import java.util.Map;


public class Platform {
    private Order order;
    private int platformID;
    private Map<Integer, Integer> locationInRack;
    private Dimension dimension;

    public Platform(Order order, int platformID, Map<Integer, Integer> locationInRack, Dimension dimension) {
        this.order = order;
        this.platformID = platformID;
        this.locationInRack = locationInRack;
        this.dimension = dimension;
    }

    public Map<Integer, Integer> getLocation() {
        return locationInRack;
    }
    
}
