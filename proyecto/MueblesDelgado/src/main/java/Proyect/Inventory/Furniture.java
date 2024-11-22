package Proyect.Inventory;

import Proyect.Validations.ValidationUtils;
import jakarta.persistence.*;
import Proyect.StoreKeeper.Order;

@Entity
public class Furniture {

    @Id
    private int furnitureId;

    private String type;
    private String brand;
    private String color;

    @Embedded
    private Dimension dimension = new Dimension(1f, 1f, 1f);

    private int quantity;
    private int buildTime;

    // Relación ManyToOne con Order
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id") // Relación con Order
    private Order order;

    // Relación ManyToOne con PackingList (opcional)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "packingListID") // Nombre de la columna de clave foránea
    private PackingList packingList;

    // Constructor vacío para JPA
    public Furniture() {}

    // Constructor que toma un orderId para asociar el Order
    public Furniture(int p_furnitureId, String p_type, String p_brand, String p_color, Dimension p_dimension, 
                     int p_quantity, int p_buildTime, Order order) {
        setFurnitureId(p_furnitureId);
        setType(p_type);
        setBrand(p_brand);
        setColor(p_color);
        setDimension(p_dimension);
        setQuantity(p_quantity);
        setBuildTime(p_buildTime);
        setOrder(order);  // Asociamos el Order
    }

    // Getters y Setters
    public int getFurnitureId() {
        return furnitureId;
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public PackingList getPackingList() {
        return packingList;
    }

    public void setPackingList(PackingList packingList) {
        this.packingList = packingList;
    }

    @Override
    public boolean equals(Object p_object) {
        if (!(p_object instanceof Furniture furniture)) {
            return false;
        }
        return furnitureId == furniture.furnitureId;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(furnitureId);
    }

    @Override
    public String toString() {
        return "Furniture{" +
                "furnitureId=" + furnitureId +
                ", type='" + type + '\'' +
                ", brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                ", dimension=" + dimension +
                ", quantity=" + quantity +
                ", buildTime=" + buildTime +
                '}';
    }
}
