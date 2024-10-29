package StoreKeeper;

import Container.Container;
import Container.ContainerList;
import Validations.ValidationUtils;

public class Rack extends Container {
    private int cellCounter = 0;
    private ContainerList<Cell> rackCells = null;

    public Rack(int p_rackNumber){
        setAvailable(true);
        setNumber(p_rackNumber);
        setCellCounter(6); // cambiar la cantidad de celdas cuando se defina en equipo
        this.rackCells = new ContainerList<Cell>();
        setRackCells();
    }


    public int getCellCounter() {
        return this.cellCounter;
    }

    public void setCellCounter(int p_cellCounter) {
        ValidationUtils.validatePositiveNumber(p_cellCounter, "Cell Counter");
        this.cellCounter = p_cellCounter;
    }

    public ContainerList<Cell> getRackCells() {
        return this.rackCells;
    }

   private void setRackCells() {
       for(int actualCellNumber = 1; actualCellNumber<= this.cellCounter; actualCellNumber++){
           Cell cell = new Cell(actualCellNumber, 0);
           this.rackCells.add(cell);
       }
    }

    @Override
    public boolean isAvailable() {
        int cellsNotAvailable = 0;
        //determinando cuantas celdas no estan disponibles
        for(Cell cell: this.rackCells){
            if(!cell.isAvailable()){
                cellsNotAvailable++;
            }
        }

        if(cellsNotAvailable == this.cellCounter){
            return false;
        } else{ return true;}

    }

    public void getInfo(){
        System.out.println("Rack number: "  + number); //rackNumber
        System.out.println("Cell counter: " + this.cellCounter);
        System.out.println("Rack available: " + isAvailable());
    }

    public static void main(String[] args) {
        Rack rack1 = new Rack(1);
        rack1.getInfo();
    }

}


