package StoreKeeper;

import Container.Container;
import java.util.ArrayList;


public class Rack extends Container {
    private int rackNumber = 0;
    private int cellCounter = 0;
    private ArrayList<Cell> rackCells = new ArrayList<>();

    public Rack(int rackNumber){
        setRackNumber(rackNumber);
        setCellCounter(6); // cambiar la cantidad de celdas cuando se defina en equipo
        setAvailable(true);

         /*
        En este punto se estám creando las celdas y se insertan en el arrayList
                PENDIENTE --> ¿Cómo y donde debe de estar implementado esto?
         */
        System.out.println("Creating cells...");
        for(int actualCellNumber=1;actualCellNumber<=cellCounter;actualCellNumber++){
            Cell cell = new Cell(actualCellNumber, 0);
            rackCells.add(cell);
        }
        System.out.println("Process finished (creating cells)");
    }

    public void getInfo(){
        System.out.println("Rack number: " + rackNumber);
        System.out.println("Cell counter: " + cellCounter);
        System.out.println("Rack available: " + isAvailable());
    }

    @Override
    public boolean isAvailable() {
        int cellsNotAvailable = 0;
        //determinando cuantas celdas no estan disponibles
        for(Cell cell:rackCells){
            if(!cell.isAvailable()){
                cellsNotAvailable++;
            }
        }

        if(cellsNotAvailable == cellCounter){
            return false;
        } else{ return true;}

    }

    public int getRackNumber() {
        return rackNumber;
    }

    public void setRackNumber(int rackNumber) {
        this.rackNumber = rackNumber;
    }

    public int getCellCounter() {
        return cellCounter;
    }

    public void setCellCounter(int cellCounter) {
        this.cellCounter = cellCounter;
    }

    public ArrayList<Cell> getRackCells() {
        return rackCells;
    }

    public void setRackCells(ArrayList<Cell> rackCells) {
        this.rackCells = rackCells;
    }

    public static void main(String[] args) {
        Rack rack1 = new Rack(1);
        rack1.getInfo();
    }

}


