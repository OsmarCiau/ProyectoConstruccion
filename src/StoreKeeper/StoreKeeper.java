package StoreKeeper;

import Inventory.Dimension;
import Inventory.Furniture;
import Container.ContainerList;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class StoreKeeper {
    private int employeeId = 0;
    private ContainerList<Rack> racksList = new ContainerList<Rack>();
    private ArrayList<Platform> platformsList = new ArrayList<>();
    // private FurnitureRegistry entry;

    public StoreKeeper(int employeeId, ContainerList<Rack> racksList){
        this.employeeId = employeeId;
        this.racksList = racksList;
    }

    public Map<Rack, ArrayList<Cell>> findAvailableSpace() {
        Map<Rack, ArrayList<Cell>> avaibleSpace = new HashMap<>();

        ArrayList<Rack> availableRacksList = findAvailableRack();

        for(Rack rack: availableRacksList){
            ArrayList<Cell> availableCellsList =findAvailableCell(rack);
            avaibleSpace.put(rack, availableCellsList);
        }

        for(Rack rack:  avaibleSpace.keySet()){
            avaibleSpace.get(rack);
        }

        return avaibleSpace;
    }


    //cambiar find por get??
    private ArrayList<Rack> findAvailableRack() {
        ArrayList<Rack> availableRacksList = new ArrayList<>();

        for(Rack rack: racksList){
            if(rack.isAvailable()){
                availableRacksList.add(rack);
            }
        }
        //System.out.println("Se encontraron todos los racks disponibles");
        return availableRacksList;
    }

    private ArrayList<Cell> findAvailableCell(Rack rack) {
        ArrayList<Cell>  availableCellsList = new ArrayList<>();

        for(Cell cell: rack.getRackCells()){
            if(cell.isAvailable()){
                availableCellsList.add(cell);
            }
        }
        return availableCellsList;
    }

    public void placePlatformInCell(Platform platform) {
        StorageKeys spotKey = findSpotForPlatform(platform);
        //System.out.println("Place platform in cell: Spot: rack " + spotKey.getRackNumber() + ", celda "+ spotKey.getCellNumber());
        platform.setLocationInRack(spotKey);

        //Asignando el espacio ocupado por la plataforma en la celda

        Rack rack = getRackByNumber(spotKey.getRackNumber());

        ContainerList<Cell> cellList = rack.getRackCells();
        Cell cell = cellList.getByNumber(spotKey.getCellNumber());
        platform.setLocationInRack(spotKey);
        cell.addSpaceOccupiedInCell(platform.getDimension().getLength()); // refactorizar
        System.out.println("La tarima " + platform.getPlatformID() + " se colocó en: Rack: " + rack.getNumber() + ", Celda: "+cell.getNumber());
        System.out.println("Espacio restante en la celda " + cell.getNumber() + ": " + cell.getAvailableSpace());
    }

    public void retirePlatformInCell(Platform platform) {
        StorageKeys spotKey = platform.getLocation();

        Rack rack = racksList.getByNumber(spotKey.getRackNumber());
        ContainerList<Cell> cellList = rack.getRackCells();
        Cell cell = cellList.getByNumber(spotKey.getCellNumber());
        cell.removeSpaceOccupiedInCell(platform.getDimension().getLength());
        System.out.println("La tarima " + platform.getPlatformID() + " se retiró de: Rack: " + rack.getNumber() + ", Celda: "+cell.getNumber());
        System.out.println("Espacio restante en la celda " + cell.getNumber() + ": " + cell.getAvailableSpace());

    }

    public StorageKeys findPlatform(int platformId) {
        for(Platform platform: platformsList){
            if(platform.getPlatformID() == platformId){
                return platform.getLocation();
            }
        }
        return null; //si no encontró plataforma con el id
    }

    public StorageKeys findSpotForPlatform(Platform platform){

        // Retorna el primer espacio adecuado encontrado
        ArrayList<Rack> availableRacksList = findAvailableRack();


        for(Rack availableRack: availableRacksList){
            ArrayList<Cell> availableCellsList = findAvailableCell(availableRack);

            for(Cell availableCell: availableCellsList){
                if(doesPlatformFitsInCell(availableCell, platform)){
                    StorageKeys spotForPlatform = new StorageKeys(availableRack.getNumber(), availableCell.getNumber());
                    return spotForPlatform;
                }
            }
        }
        return null; //no se encontraron celdas con espacio suficiente para almacenar la plataforma
    }


    private boolean doesPlatformFitsInCell(Cell cell, Platform platform){
      //cellHasSpaceForPlatform
        if (platform.getDimension().getLength() <= cell.getDimension().getLength()){ //corregir llamadas en la refactorización
            return true;
        }
        else if((platform.getDimension().getWidth() <= cell.getDimension().getLength())  && (platform.getDimension().getLength() <= cell.getDimension().getWidth()) ){
            //cambiar ancho por largo de plaforma
             platform.getDimension().swapLenghtAndWidth();
             return true;
        }
      return false;
    }

    private Rack getRackByNumber(int rackNumber){
        for(Rack rack: racksList){
            if(rack.getNumber() == rackNumber){
                return rack;
            }
        }
        return null;
    }

}
