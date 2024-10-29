package StoreKeeper;

import Container.Container;
import Inventory.Dimension;

public class Cell extends Container {
    private Dimension dimension = null;
    private float occupiedSpace =0;

    public Cell(int p_cellNumber, float p_occupiedSpace){
        setAvailable(true);
        setNumber(p_cellNumber);
        this.dimension =  new Dimension(1,1,1);
        setDimension(); // los valores son iguales para todas las celdas. Ya están definidos en el setter.
        setOccupiedSpace(p_occupiedSpace);
    }


    public Dimension getDimension() {
        return this.dimension;
    }

    public void setDimension() {
        this.dimension.setLength(5);
        this.dimension.setWidth(5);
        this.dimension.setHeight(5);
    }

    public float getOccupiedSpace() {
        return this.occupiedSpace;
    }

    public void setOccupiedSpace(float p_occupiedSpace) {
        this.occupiedSpace = p_occupiedSpace;
    }

    @Override
    public boolean isAvailable() {
        if( (this.dimension.getLength() - this.occupiedSpace) >0 ){
            return true;
        }
        else{
            return false;
        }

    }

    public void getInfo(){
        System.out.println("Cell number: " + number + "\n" + //celNumber
                "Dimension: " + this.dimension.getLength() + " x " + this.dimension.getWidth() + " x " + this.dimension.getHeight() + "\n" +
                "Occupied space (lenght): " + this.occupiedSpace + "\n" +
                "Available: " + isAvailable()
        );
    }

    public float getAvailableSpace(){
        if(isAvailable()){
            return this.dimension.getLength() - this.occupiedSpace;
        }
        else{
            return 0;
        }
    }

    public void addSpaceOccupiedInCell(float p_platformLength){
        this.occupiedSpace += p_platformLength;
    }

    public void removeSpaceOccupiedInCell(float p_platformLength){
        this.occupiedSpace -= p_platformLength;
    }


}