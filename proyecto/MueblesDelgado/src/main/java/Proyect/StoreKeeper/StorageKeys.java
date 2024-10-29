package Proyect.StoreKeeper;

import Proyect.Validations.ValidationUtils;

public class StorageKeys {
    private int cellNumber = 0;
    private int rackNumber = 0;

    public StorageKeys(){}

    public StorageKeys(int p_cellNumber, int p_rackNumber){
        this.cellNumber = p_cellNumber;
        this.rackNumber = p_rackNumber;
    }

    public int getRackNumber() {
        return rackNumber;
    }

    public void setRackNumber(int p_rackNumber) {
        ValidationUtils.validatePositiveNumber(p_rackNumber,"Rack Number");
        this.rackNumber = p_rackNumber;
    }

    public int getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(int p_cellNumber) {
        ValidationUtils.validatePositiveNumber(p_cellNumber, "Cell Number");
        this.cellNumber = p_cellNumber;
    }

}