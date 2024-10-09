package StoreKeeper;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.SortedMap;

public class Rack extends Container{
    private int rackNumber;
    private int cellCounter;
    private ArrayList<Cell> rackCells;

    public Rack(int rackNumber){
        this.rackNumber = rackNumber;
        setAvailable(true);
        rackCells = new ArrayList<>();
    }

    public void setInfo(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Number of cells in the rack: ");
        cellCounter = scan.nextInt();
        /*
        En este punto se estám creando las celdas y se insertan en el arrayList
                PENDIENTE --> ¿Hacemos un metodo que lo haga?
         */
        System.out.println("Creating cells...");
        for(int actualCellNumber=1;actualCellNumber<=cellCounter;actualCellNumber++){
            Cell cell = new Cell(actualCellNumber);
            System.out.println("Cell " + actualCellNumber);
            cell.setInfo();
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
        for(Cell cell:rackCells){

            if(!cell.isAvailable()){
                cellsNotAvailable++;
            }
        }

        if(cellsNotAvailable == cellCounter){
            return false;
        }
        else{
            return true;
        }

    }

    public int getRackNumber() {
        return rackNumber;
    }

    public int getCellCounter() {
        return cellCounter;
    }

    public ArrayList<Cell> getRackCells() {
        return rackCells;
    }
}
