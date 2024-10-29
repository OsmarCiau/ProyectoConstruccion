package Proyect.StoreKeeper;

public class StorageKeys {
    private int cellNumber = 0;
    private int rackNumber = 0;

    public StorageKeys(){

    }

    public StorageKeys(int cellNumber, int rackNumber){
        this.cellNumber = cellNumber;
        this.rackNumber = rackNumber;
    }

    public int getRackNumber() {
        return rackNumber;
    }

    public void setRackNumber(int rackNumber) {
        this.rackNumber = rackNumber;
    }

    public int getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(int cellNumber) {
        this.cellNumber = cellNumber;
    }

}
