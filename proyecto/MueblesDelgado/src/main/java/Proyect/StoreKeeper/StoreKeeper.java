package Proyect.StoreKeeper;

import Proyect.Container.ContainerList;
import Proyect.Inventory.Dimension;
import Proyect.Inventory.Furniture;
import Proyect.Validations.ValidationUtils;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StoreKeeper {

    private int employeeId = 0;
    private ContainerList<Rack> racks = new ContainerList<Rack>();
    private ArrayList<Platform> platforms = new ArrayList<>();
    // private FurnitureRegistry entry;

    public StoreKeeper(int p_employeeId, ContainerList<Rack> p_racksList){
        this.employeeId = p_employeeId;
        this.racks = p_racksList;
    }

    public ArrayList<StorageKeys> getAvailableSpace() {

        ArrayList<StorageKeys> availableSpaceKeys = new ArrayList<>();

        ContainerList<Rack> availableRacks = getAvailableRacks();

        for(Rack rack: availableRacks){
            ContainerList<Cell> availableCells = getAvailableCells(rack);
            for(Cell cell: availableCells){
                availableSpaceKeys.add(new StorageKeys(cell.getNumber(), rack.getNumber()));
            }
        }

        return availableSpaceKeys;
    }


    private ContainerList<Rack> getAvailableRacks() {

        ContainerList<Rack> availableRacks = new ContainerList<>();
        for(Rack rack: this.racks){
            if(rack.isAvailable()){
                availableRacks.add(rack);
            }
        }
        return availableRacks;
    }

    private ContainerList<Cell> getAvailableCells(Rack p_rack) {
        ContainerList<Cell>  availableCells = new ContainerList<>();

        for(Cell cell: p_rack.getRackCells()){
            if(cell.isAvailable()){
                availableCells.add(cell);
            }
        }
        return availableCells;
    }

    public void placePlatformInCell(Platform p_platform) {
        StorageKeys locationKey = getSpotForPlatform(p_platform);
        //añadir valiacion por si no hay lugar
        Cell cell = getCell(locationKey);
        cell.addSpaceOccupiedInCell( p_platform.getLength() );

        p_platform.setLocationInRack(locationKey);
        this.platforms.add(p_platform);

        //Asignando el espacio ocupado por la plataforma en la celda
        /*
        Rack rack = getRackByNumber(locationKey.getRackNumber());
        Cell celda = getCell(locationKey);

        ContainerList<Cell> cellList = rack.getRackCells();
        Cell cell = cellList.getByNumber(locationKey.getCellNumber());

        cell.addSpaceOccupiedInCell(p_platform.getDimension().getLength()); // refactorizar

         */

    }

    public void retirePlatformFromCell(Platform p_platform) {
        StorageKeys platformLocation = p_platform.getLocationInRack();
        Cell cell = getCell(platformLocation);
        cell.removeSpaceOccupiedInCell(p_platform.getDimension().getLength());
        this.platforms.remove(p_platform);

        System.out.println("La tarima " + p_platform.getPlatformID() + " se retiró de: Rack: " + platformLocation.getRackNumber() + ", Celda: " + cell.getNumber());
        System.out.println("Espacio restante en la celda " + cell.getNumber() + ": " + cell.getAvailableSpace());
    }

    public StorageKeys findPlatform(int p_platformId) {
        for(Platform platform: this.platforms){
            if(platform.getPlatformID() == p_platformId){
                return platform.getLocationInRack();
            }
        }
        return null; //si no encontró plataforma con el id
    }

    public StorageKeys getSpotForPlatform(Platform p_platform){

        // Retorna el primer espacio adecuado encontrado
        ContainerList<Rack> availableRacks = getAvailableRacks();

        for(Rack rack: availableRacks){

            ContainerList<Cell> availableCells = getAvailableCells(rack);
            Cell cell = getFirstFittingCellForPlatform(p_platform, availableCells);

            if(cell != null){
                return new StorageKeys(cell.getNumber(), rack.getNumber());
            }
        }
        return new StorageKeys();
    }

    private Cell getFirstFittingCellForPlatform(Platform p_platform, ContainerList<Cell> p_cells){
        for(Cell cell: p_cells){
            if(platformFitsInCell(p_platform, cell)){
                return cell;
            }
        }
        return null;
    }

    private boolean platformFitsInCell(Platform p_platform, Cell p_cell){

        if ( p_platform.getLength() <= p_cell.getLength() ){
            return true;
        }
        else if( (p_platform.getWidth() <= p_cell.getLength())  && (p_platform.getLength() <= p_cell.getWidth()) ){
            //cambiar ancho por largo de plataforma
            p_platform.getDimension().swapLengthAndWidth();
            return true;
        }

        return false;
    }

    private Rack getRackByNumber(int p_rackNumber){
        for(Rack rack: this.racks){
            if(rack.getNumber() == p_rackNumber){
                return rack;
            }
        }
        return null;
    }

    public ContainerList<Rack> getRacks() {
        return this.racks;
    }

    public void setRacks(ContainerList<Rack> p_racks) {
        ValidationUtils.validateContainerList(p_racks, "Rack List");
        this.racks = p_racks;
    }

    private Cell getCell(StorageKeys p_location){
        Rack rack = this.racks.getByNumber(p_location.getRackNumber());
        ContainerList<Cell> cellList = rack.getRackCells();
        return cellList.getByNumber(p_location.getCellNumber());
    }

}