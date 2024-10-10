package Inventory;

import java.util.Scanner;

public class Furniture {

    private int furnitureId;
    private String type;
    private String brand;
    private String color;
    private Dimension dimension;
    private int quantity;
    private int buildTime;

    public Furniture(int furnitureId, String type, String brand, String color, Dimension dimension, int quantity, int buildTime) {
        setFurnitureId(furnitureId);
        setType(type);
        setBrand(brand);
        setColor(color);
        setDimension(dimension);
        setQuantity(quantity);
        setBuildTime(buildTime);
    }

    public int getFurnitureId() {
        return furnitureId;
    }

    public void setFurnitureId(int furnitureId) {
        if (furnitureId > 0) {
            this.furnitureId = furnitureId;
        } else {
            System.out.println("Error: furnitureId must be a positive number.");
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type != null && !type.isEmpty()) {
            this.type = type;
        } else {
            System.out.println("Error: type cannot be empty.");
        }
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        if (brand != null && !brand.isEmpty()) {
            this.brand = brand;
        } else {
            System.out.println("Error: brand cannot be empty.");
        }
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        if (color != null && !color.isEmpty()) {
            this.color = color;
        } else {
            System.out.println("Error: color cannot be empty.");
        }
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Setting dimension (cm): ");
        System.out.print("\tLenght: ");
        float data = scan.nextFloat();
        dimension.setLength(data);

        System.out.print("\tWidth: ");
        data = scan.nextFloat();
        dimension.setWidth(data);

        System.out.print("\tHeight: ");
        data = scan.nextFloat();
        dimension.setHeight(data);

        scan.close(); 
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity > 0) {
            this.quantity = quantity;
        } else {
            System.out.println("Error: quantity must be a positive number.");
        }
    }

    public int getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(int buildTime) {
        if (buildTime > 0) {
            this.buildTime = buildTime;
        } else {
            System.out.println("Error: buildTime must be a positive number.");
        }
    }

}
