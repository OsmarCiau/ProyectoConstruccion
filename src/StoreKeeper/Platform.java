package StoreKeeper;

import java.util.Map;


public class Platform {
    private Order order;
    private int platformID;
    private Map<Integer, Integer> locationInRack;
    private Dimension dimension;

    public Map<Integer, Integer> getLocation() {
        return locationInRack;
    }
    
}
