package StoreKeeper;

import java.util.Scanner;
import java.util.SortedMap;

public class Cell extends Container{
    private Dimension dimension;
    private int cellNumber;
    private float ocupiedSpace; //  espacio restante a lo largo

    public Cell(int cellNumber){
        setAvailable(true);
        dimension = new Dimension();
        this.cellNumber = cellNumber;
        ocupiedSpace = 0;
    }
    public void getInfo(){
        System.out.println("Cell number: " + cellNumber + "\n" +
                            "Dimension: " + dimension.getLenght() + " x " + dimension.getWidth() + " x " + dimension.getHeight() + "\n" +
                            "Occupied space (lenght): " + ocupiedSpace + "\n" +
                            "Available: " + isAvailable()
        );
    }

    public void setInfo(){
        //System.out.println("\nSetting info of the cell\n");
        setDimension();
    }
    @Override
    public boolean isAvailable() {
        if( (dimension.getLenght() - ocupiedSpace) >0 ){
            return true;
        }
        else{
            return false;
        }

    }

    public float getAvailableSpace(){
        if(isAvailable()){
            return dimension.getLenght() - ocupiedSpace;
        }
        else{
            return 0;
        }
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Setting dimension (cm): ");
        System.out.print("\tLenght: ");
        float data = scan.nextFloat();
        dimension.setLenght(data);
        System.out.print("\tWidth: ");
        data = scan.nextFloat();
        dimension.setWidth(data);
        System.out.print("\tHeight: ");
        data = scan.nextFloat();
        dimension.setHeight(data);

        scan.close(); //  Cerrar el scanner
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
