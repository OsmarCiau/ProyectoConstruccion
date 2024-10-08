package StoreKeeper;

import java.util.ArrayList;
import java.util.Date;
import java.time.Duration;

public class Order {
    private int orderID;
    private String destination;
    private ArrayList<Platform> platformUsed;
    private Date deliveryDate;
    private Duration assemblyTime;
    private ArrayList<Furniture> orderContent;

    public findPlatforms() {
        // TODO - implement Order.findPlatforms
        throw new UnsupportedOperationException();
    }

    public Duration getTotalAssemblyTime(ArrayList<Furniture> orderContent) {
        // TODO - implement Order.getTotalAssemblyTime
        throw new UnsupportedOperationException();
    }
}
