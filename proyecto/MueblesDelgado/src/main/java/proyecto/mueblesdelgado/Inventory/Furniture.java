package proyecto.mueblesdelgado.Inventory;

public class Furniture{

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
            throw new IllegalArgumentException("Error: furnitureId must be a positive number.");
        }
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type != null && !type.isEmpty()) {
            this.type = type;
        } else {
            throw new IllegalArgumentException("Error: Type cannot be null or empty.");
        }
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        if (brand != null && !brand.isEmpty()) {
            this.brand = brand;
        } else {
            throw new IllegalArgumentException("Error: brand cannot be empty.");
        }
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        if (color != null && !color.isEmpty()) {
            this.color = color;
        } else {
            throw new IllegalArgumentException("Error: color cannot be empty.");
        }
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        if (dimension == null) {
            throw new IllegalArgumentException("Error: Dimension cannot be null.");
        }
        this.dimension = dimension;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity > 0) {
            this.quantity = quantity;
        } else {
            throw new IllegalArgumentException("Error: quantity must be a positive number.");
        }
    }

    public int getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(int buildTime) {
        if (buildTime > 0) {
            this.buildTime = buildTime;
        } else {
            throw new IllegalArgumentException("Error: buildTime must be a positive number.");
        }
    }

}
