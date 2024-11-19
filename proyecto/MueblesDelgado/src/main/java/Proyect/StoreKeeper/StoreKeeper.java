package Proyect.StoreKeeper;

import Proyect.Container.ContainerList;
import Proyect.Validations.ValidationUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StoreKeeper {
    private int employeeId = 0;
    private ContainerList<Rack> racksList = new ContainerList<Rack>();
    private ArrayList<Platform> platformsList = new ArrayList<>();
    // private FurnitureRegistry entry;

    public StoreKeeper(int p_employeeId, ContainerList<Rack> p_racksList){
        this.employeeId = p_employeeId;
        this.racksList = p_racksList;
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

        for(Rack rack: this.racksList){
            if(rack.isAvailable()){
                availableRacksList.add(rack);
            }
        }
        //System.out.println("Se encontraron todos los racks disponibles");
        return availableRacksList;
    }

    private ArrayList<Cell> findAvailableCell(Rack p_rack) {
        ArrayList<Cell>  availableCellsList = new ArrayList<>();

        for(Cell cell: p_rack.getRackCells()){
            if(cell.isAvailable()){
                availableCellsList.add(cell);
            }
        }
        return availableCellsList;
    }

    public void placePlatformInCell(Platform p_platform) {
        StorageKeys spotKey = findSpotForPlatform(p_platform);
        //System.out.println("Place platform in cell: Spot: rack " + spotKey.getRackNumber() + ", celda "+ spotKey.getCellNumber());
        p_platform.setLocationInRack(spotKey);

        //Asignando el espacio ocupado por la plataforma en la celda

        Rack rack = getRackByNumber(spotKey.getRackNumber());

        ContainerList<Cell> cellList = rack.getRackCells();
        Cell cell = cellList.getByNumber(spotKey.getCellNumber());
        p_platform.setLocationInRack(spotKey);
        cell.addSpaceOccupiedInCell(p_platform.getDimension().getLength()); // refactorizar
        //System.out.println("La tarima " + p_platform.getPlatformID() + " se colocó en: Rack: " + rack.getNumber() + ", Celda: "+cell.getNumber());
        //System.out.println("Espacio restante en la celda " + cell.getNumber() + ": " + cell.getAvailableSpace());
        this.platformsList.add((p_platform));
    }

    public void retirePlatformInCell(Platform p_platform) {
        StorageKeys spotKey = p_platform.getLocationInRack();

        Rack rack = this.racksList.getByNumber(spotKey.getRackNumber());
        ContainerList<Cell> cellList = rack.getRackCells();
        Cell cell = cellList.getByNumber(spotKey.getCellNumber());
        cell.removeSpaceOccupiedInCell(p_platform.getDimension().getLength());
        System.out.println("La tarima " + p_platform.getPlatformID() + " se retiró de: Rack: " + rack.getNumber() + ", Celda: "+cell.getNumber());
        System.out.println("Espacio restante en la celda " + cell.getNumber() + ": " + cell.getAvailableSpace());

    }

    public StorageKeys findPlatform(int p_platformId) {
        for(Platform platform: this.platformsList){
            if(platform.getPlatformID() == p_platformId){
                return platform.getLocationInRack();
            }
        }
        return null; //si no encontró plataforma con el id
    }

    public StorageKeys findSpotForPlatform(Platform p_platform){

        // Retorna el primer espacio adecuado encontrado
        ArrayList<Rack> availableRacksList = findAvailableRack();


        for(Rack availableRack: availableRacksList){
            ArrayList<Cell> availableCellsList = findAvailableCell(availableRack);

            for(Cell availableCell: availableCellsList){
                if(doesPlatformFitsInCell(availableCell, p_platform)){
                    StorageKeys spotForPlatform = new StorageKeys(availableRack.getNumber(), availableCell.getNumber());
                    return spotForPlatform;
                }
            }
        }
        return null; //no se encontraron celdas con espacio suficiente para almacenar la plataforma
    }


    private boolean doesPlatformFitsInCell(Cell p_cell, Platform p_platform){

        if (p_platform.getDimension().getLength() <= p_cell.getDimension().getLength()){ //corregir llamadas en la refactorización
            return true;
        }
        else if((p_platform.getDimension().getWidth() <= p_cell.getDimension().getLength())  && (p_platform.getDimension().getLength() <= p_cell.getDimension().getWidth()) ){
            //cambiar ancho por largo de plaforma
            p_platform.getDimension().swapLengthAndWidth();
            return true;
        }
        return false;
    }

    private Rack getRackByNumber(int p_rackNumber){
        for(Rack rack: this.racksList){
            if(rack.getNumber() == p_rackNumber){
                return rack;
            }
        }
        return null;
    }

    public ContainerList<Rack> getRacksList() {
        return this.racksList;
    }

    public void setRacksList(ContainerList<Rack> p_racksList) {
        ValidationUtils.validateContainerList(p_racksList, "Rack List");
        this.racksList = p_racksList;
    }
}