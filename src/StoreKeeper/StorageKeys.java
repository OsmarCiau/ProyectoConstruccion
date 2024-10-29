package StoreKeeper;

public class StorageKeys {
    private int cellNumber = 0;
    private int rackNumber = 0;

    public StorageKeys(){

    }

    public StorageKeys(int p_cellNumber, int p_rackNumber){
        this.cellNumber = p_cellNumber;
        this.rackNumber = p_rackNumber;
    }

    public int getRackNumber() {
        return this.rackNumber;
    }

    public void setRackNumber(int p_rackNumber) {
        this.rackNumber = p_rackNumber;
    }

    public int getCellNumber() {
        return this.cellNumber;
    }

    public void setCellNumber(int p_cellNumber) {
        this.cellNumber = p_cellNumber;
    }


}
