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

    private static final FurnitureValidationUtils furnitureValidator = new FurnitureValidationUtils();

    public void setFurnitureId(int p_furnitureId) {
        boolean isFurnitureIdValid = furnitureValidator.validateNonNegative(p_furnitureId, "Furniture ID");

        if (isFurnitureIdValid) {
            a_furnitureId = p_furnitureId;
        }
    }

    public String getType() {
        return a_type;
    }

    public void setType(String p_type) {
        boolean isTypeValid = furnitureValidator.validateNonNull(p_type, "Type");

        if (isTypeValid) {
            a_type = p_type;
        }
    }

    public String getBrand() {
        return a_brand;
    }

    public void setBrand(String p_brand) {
        boolean isBrandValid = furnitureValidator.validateNonNull(p_brand, "Brand");

        if (isBrandValid) {
            a_brand = p_brand;
        }
    }

    public String getColor() {
        return a_color;
    }

    public void setColor(String p_color) {
        boolean isColorValid = furnitureValidator.validateNonNull(p_color, "Color");

        if (isColorValid) {
            a_color = p_color;
        }
    }

    public Dimension getDimension() {
        return a_dimension;
    }

    public void setDimension(Dimension p_dimension) {
        boolean isDimensionValid = furnitureValidator.validateDimension(p_dimension);

        if (isDimensionValid) {
            a_dimension = p_dimension;
        }
    }

    public int getQuantity() {
        return a_quantity;
    }

    public void setQuantity(int p_quantity) {
        boolean isQuantityValid = furnitureValidator.validateQuantity(p_quantity);

        if (isQuantityValid) {
            a_quantity = p_quantity;
        }
    }

    public int getBuildTime() {
        return a_buildTime;
    }

    public void setBuildTime(int p_buildTime) {
        boolean isBuildTimeValid = furnitureValidator.validateNonNegative(p_buildTime, "Build Time");

        if (isBuildTimeValid) {
            a_buildTime = p_buildTime;
        }
    }

    public boolean equals(Object p_object) {
        if (!(p_object instanceof Furniture furniture))
            return false;
        return a_furnitureId == furniture.a_furnitureId;
    }
    
}
