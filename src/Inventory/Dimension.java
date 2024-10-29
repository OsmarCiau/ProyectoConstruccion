package Inventory;

public class Dimension {
    private float length = 0.0f;
    private float height = 0.0f;
    private float width = 0.0f;

    public Dimension(float p_length, float p_height, float p_width){
        setLength(p_length);
        setHeight(p_height);
        setWidth(p_width);
    }

    public float getHeight() {
        return height;
    }

    private static final DimensionValidationUtils dimensionValidator = new DimensionValidationUtils();

    public void setHeight(float p_height) {
        dimensionValidator.validateHeight(p_height);
        this.height = p_height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float p_width){
        dimensionValidator.validateWidth(p_width);
        this.width = p_width;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float p_length) {
        dimensionValidator.validateLength(p_length);
        this.length = p_length;
    }

    public void swapLengthAndWidth(){
        float currentWidth = width;
        width = length;
        length = currentWidth;
    }
}

