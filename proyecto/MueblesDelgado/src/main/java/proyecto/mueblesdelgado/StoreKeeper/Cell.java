package proyecto.mueblesdelgado.StoreKeeper;

import proyecto.mueblesdelgado.Container.Container;
import proyecto.mueblesdelgado.Inventory.Dimension;

import java.util.Scanner;

public class Cell extends Container {
    private Dimension dimension = new Dimension(0,0,0);
    private int cellNumber=0;
    private float ocupiedSpace=0; //  espacio restante a lo largo

    public Cell(int cellNumber, float ocupiedSpace){
        setAvailable(true);
        setCellNumber(cellNumber);
        setDimension(); // los valores son iguales para todas las celdas. Ya están definidos en el setter.
        setOcupiedSpace(ocupiedSpace);
    }
    public void getInfo(){
        System.out.println("Cell number: " + cellNumber + "\n" +
                "Dimension: " + dimension. getLength() + " x " + dimension.getWidth() + " x " + dimension.getHeight() + "\n" +
                "Occupied space (lenght): " + ocupiedSpace + "\n" +
                "Available: " + isAvailable()
        );
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

    public void setDimension() {
        this.dimension.setLength(5);
        this.dimension.setWidth(5);
        this.dimension.setHeight(5);
    }

    public int getCellNumber() {
        return cellNumber;
    }


    public void setCellNumber(int cellNumber) {
        this.cellNumber = cellNumber;
    }

    public float getOcupiedSpace() {
        return ocupiedSpace;
    }

    public void setOcupiedSpace(float ocupiedSpace) {
        this.ocupiedSpace = ocupiedSpace;
    }
}