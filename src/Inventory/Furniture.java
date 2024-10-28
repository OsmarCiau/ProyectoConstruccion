package Inventory;

public class Furniture{
    private int a_furnitureId = 0;
    private String a_type = null;
    private String a_brand = null;
    private String a_color = null;
    private Dimension a_dimension = new Dimension(0.0f, 0.0f, 0.0f);
    private int a_quantity = 0;
    private int a_buildTime = 0;

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
        FurnitureValidationUtils.validateNonNegative(p_furnitureId, "Furniture ID");
        a_furnitureId = p_furnitureId;
    }

    public String getType() {
        return a_type;
    }

    public void setType(String p_type) {
        FurnitureValidationUtils.validateNonNull(p_type, "Type");
        a_type = p_type;
    }

    public String getBrand() {
        return a_brand;
    }

    public void setBrand(String p_brand) {
        FurnitureValidationUtils.validateNonNull(p_brand, "Brand");
        a_brand = p_brand;
    }

    public String getColor() {
        return a_color;
    }

    public void setColor(String p_color) {
        FurnitureValidationUtils.validateNonNull(p_color, "Color");
        a_color = p_color;
    }

    public Dimension getDimension() {
        return a_dimension;
    }

    public void setDimension(Dimension p_dimension) {
        FurnitureValidationUtils.validateDimension(p_dimension);
        a_dimension = p_dimension;
    }

    public int getQuantity() {
        return a_quantity;
    }

    public void setQuantity(int p_quantity) {
        FurnitureValidationUtils.validateQuantity(p_quantity);
        a_quantity = p_quantity;
    }

    public int getBuildTime() {
        return a_buildTime;
    }

    public void setBuildTime(int p_buildTime) {
        FurnitureValidationUtils.validateNonNegative(p_buildTime, "Build Time");
        a_buildTime = p_buildTime;
    }

    public boolean equals(Object p_object) {
        if (!(p_object instanceof Furniture furniture))
            return false;
        return a_furnitureId == furniture.a_furnitureId;
    }
    
}
