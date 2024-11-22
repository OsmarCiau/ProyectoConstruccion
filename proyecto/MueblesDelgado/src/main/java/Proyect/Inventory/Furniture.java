package Proyect.Inventory;

import Proyect.Validations.ValidationUtils;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Furniture{

    @Id
    private int furnitureId = 0;
    private String type = null;
    private String brand = null;
    private String color = null;

    @Embedded
    private Dimension dimension = new Dimension(1f, 1f, 1f);
    private int quantity = 0;
    private int buildTime = 0;
    private int orderID = 0;


    public Furniture(int p_furnitureId, int p_orderID, String p_type, String p_brand, String p_color, Dimension p_dimension, int p_quantity, int p_buildTime) {
        setFurnitureId(p_furnitureId);
        setOrderID(p_orderID);
        setType(p_type);
        setBrand(p_brand);
        setColor(p_color);
        setDimension(p_dimension);
        setQuantity(p_quantity);
        setBuildTime(p_buildTime);
    }

    public Furniture(){}

    public int getFurnitureId() {
        return furnitureId;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int p_orderID) {
        ValidationUtils.validateGreaterThanZero(p_orderID, "Order ID");
        this.orderID = p_orderID;
    }

    public void setFurnitureId(int p_furnitureId) {
        ValidationUtils.validateGreaterThanZero(p_furnitureId, "Furniture ID");
        this.furnitureId = p_furnitureId;
    }

    public String getType() {
        return type;
    }

    public void setType(String p_type) {
        ValidationUtils.validateNonNull(p_type, "Type");
        this.type = p_type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String p_brand) {
        ValidationUtils.validateNonNull(p_brand, "Brand");
        this.brand = p_brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String p_color) {
        ValidationUtils.validateNonNull(p_color, "Color");
        this.color = p_color;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension p_dimension) {
        ValidationUtils.validateNonNull(p_dimension, "Dimension");
        this.dimension = p_dimension;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int p_quantity) {
        ValidationUtils.validateNonNegativeNumber(p_quantity, "Quantity");
        this.quantity = p_quantity;
    }

    public int getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(int p_buildTime) {
        ValidationUtils.validateGreaterThanZero(p_buildTime, "Build Time");
        this.buildTime = p_buildTime;
    }

    @Override
    public boolean equals(Object p_object) {
        if (!(p_object instanceof Furniture furniture))
            return false;
        return furnitureId == furniture.furnitureId;
    }

}