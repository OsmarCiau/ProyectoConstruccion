package StoreKeeper;

import Container.Container;
import Inventory.Dimension;

import java.util.Scanner;

public class Cell extends Container {
    private Dimension dimension;
    private int cellNumber;
    private float ocupiedSpace; //  espacio restante a lo largo

    public Cell(int cellNumber){
        setAvailable(true);
        dimension = new Dimension(0,0,0);
        this.cellNumber = cellNumber;
        ocupiedSpace = 0;
    }
    public void getInfo(){
        System.out.println("Cell number: " + cellNumber + "\n" +
                            "Dimension: " + dimension.getLength() + " x " + dimension.getWidth() + " x " + dimension.getHeight() + "\n" +
                            "Occupied space (lenght): " + ocupiedSpace + "\n" +
                            "Available: " + isAvailable()
        );
    }

    public void setInfo(){
        //System.out.println("\nSetting info of the cell\n");
        //setDimension();
    }
    @Override
    public boolean isAvailable() {
        if( (dimension.getLength() - ocupiedSpace) >0 ){
            return true;
        }
        else{
            return false;
        }

    }

    public float getAvailableSpace(){
        if(isAvailable()){
            return dimension.getLength() - ocupiedSpace;
        }
        else{
            return 0;
        }
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        if (dimension == null) {
            throw new IllegalArgumentException("Error: Dimension cannot be null.");
        }
        this.dimension = dimension;
    }

    public int getCellNumber() {
        return cellNumber;
    }

    /*
    public void setCellNumber(int cellNumber) {
        this.cellNumber = cellNumber;
    }
     */


    public float getOcupiedSpace() {
        return ocupiedSpace;
    }

    public void setOcupiedSpace(float ocupiedSpace) {
        this.ocupiedSpace = ocupiedSpace;
    }
}
