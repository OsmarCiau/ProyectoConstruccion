package StoreKeeper;

import java.util.ArrayList;
import java.util.Map;

public class StoreKeeper {
    private int employeeId = 0;
    private FurnitureRegistry entry;
    private boolean isCellAvailable;
    private ArrayList<Rack> racks;

    public Map<Rack, ArrayList<Cell>> findAvailableSpace() {
        return null;
    }

    private ArrayList<Rack> findAvailableRack() {
        return null;
    }

    private ArrayList<Cell> findAvailableCell() {
        return null;
    }

    public boolean placePlatformInCell(Platform platform) {
        return false;
    }

    public boolean retirePlatformInCell(Platform platform) {
        return false;
    }

    public Map<Integer, Integer> findPlatform(int platformId) {
        return null;
    }
}
