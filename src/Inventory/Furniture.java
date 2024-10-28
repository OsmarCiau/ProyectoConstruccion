package Inventory;

public class Furniture{
    private int a_furnitureId;
    private String a_type;
    private String a_brand;
    private String a_color;
    private Dimension a_dimension;
    private int a_quantity;
    private int a_buildTime;

    public Furniture(int p_furnitureId, String p_type, String p_brand, String p_color, Dimension p_dimension, int p_quantity, int p_buildTime) {
        setFurnitureId(p_furnitureId);
        setType(p_type);
        setBrand(p_brand);
        setColor(p_color);
        setDimension(p_dimension);
        setQuantity(p_quantity);
        setBuildTime(p_buildTime);
    }

    public int getFurnitureId() {
        return a_furnitureId;
    }

    public void setFurnitureId(int p_furnitureId) {
        validateFurnitureId(p_furnitureId);
        this.a_furnitureId = p_furnitureId;
    }

    private void validateFurnitureId(int p_furnitureId) {
        if (p_furnitureId <= 0) {
            throw new IllegalArgumentException("Error: furnitureId must be a positive number.");
        }
    }

    public String getType() {
        return a_type;
    }

    public void setType(String p_type) {
        validateType(p_type);
        this.a_type = p_type;
    }

    private void validateType(String p_type) {
        if (p_type == null || p_type.isEmpty()) {
            throw new IllegalArgumentException("Error: Type cannot be null or empty.");
        }
    }

    public String getBrand() {
        return a_brand;
    }

    public void setBrand(String p_brand) {
        validateBrand(p_brand);
        this.a_brand = p_brand;
    }

    private void validateBrand(String p_brand) {
        if (p_brand == null || p_brand.isEmpty()) {
            throw new IllegalArgumentException("Error: brand cannot be empty.");
        }
    }

    public String getColor() {
        return a_color;
    }

    public void setColor(String p_color) {
        validateColor(p_color);
        this.a_color = p_color;
    }

    private void validateColor(String p_color) {
        if (p_color == null || p_color.isEmpty()) {
            throw new IllegalArgumentException("Error: color cannot be empty.");
        }
    }

    public Dimension getDimension() {
        return a_dimension;
    }

    public void setDimension(Dimension p_dimension) {
        validateDimension(p_dimension);
        this.a_dimension = p_dimension;
    }

    private void validateDimension(Dimension p_dimension) {
        if (p_dimension == null) {
            throw new IllegalArgumentException("Error: Dimension cannot be null.");
        }
    }

    public int getQuantity() {
        return a_quantity;
    }

    public void setQuantity(int p_quantity) {
        validateQuantity(p_quantity);
        this.a_quantity = p_quantity;
    }

    private void validateQuantity(int p_quantity) {
        if (p_quantity <= 0) {
            throw new IllegalArgumentException("Error: quantity must be a positive number.");
        }
    }

    public int getBuildTime() {
        return a_buildTime;
    }

    public void setBuildTime(int p_buildTime) {
        validateBuildTime(p_buildTime);
        this.a_buildTime = p_buildTime;
    }

    private void validateBuildTime(int p_buildTime) {
        if (p_buildTime <= 0) {
            throw new IllegalArgumentException("Error: buildTime must be a positive number.");
        }
    }

    public boolean equals(Object p_object) {
        if (!(p_object instanceof Furniture furniture))
            return false;
        return a_furnitureId == furniture.a_furnitureId;
    }
    
}
