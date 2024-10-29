package Inventory;

public class Furniture{
    private int furnitureId = 0;
    private String type = null;
    private String brand = null;
    private String color = null;
    private Dimension dimension = new Dimension(1f, 1f, 1f);
    private int quantity = 0;
    private int buildTime = 0;

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
        return furnitureId;
    }

    private static final FurnitureValidationUtils furnitureValidator = new FurnitureValidationUtils();

    public void setFurnitureId(int p_furnitureId) {
        furnitureValidator.validateNonNegative(p_furnitureId, "Furniture ID");
        furnitureId = p_furnitureId;
    }

    public String getType() {
        return type;
    }

    public void setType(String p_type) {
        furnitureValidator.validateNonNull(p_type, "Type");
        this.type = p_type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String p_brand) {
        furnitureValidator.validateNonNull(p_brand, "Brand");
        this.brand = p_brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String p_color) {
        furnitureValidator.validateNonNull(p_color, "Color");
        this.color = p_color;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension p_dimension) {
        furnitureValidator.validateDimension(p_dimension);
        this.dimension = p_dimension;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int p_quantity) {
        furnitureValidator.validateQuantity(p_quantity);
        this.quantity = p_quantity;
    }

    public int getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(int p_buildTime) {
        furnitureValidator.validateNonNegative(p_buildTime, "Build Time");
        this.buildTime = p_buildTime;
    }

    public boolean equals(Object p_object) {
        if (!(p_object instanceof Furniture furniture))
            return false;
        return furnitureId == furniture.furnitureId;
    }
    
}
