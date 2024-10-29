package Inventory;

public class Dimension {
    private float a_length = 0.0f;
    private float a_height = 0.0f;
    private float a_width = 0.0f;

    public Dimension(float p_length, float p_height, float p_width){
        setLength(p_length);
        setHeight(p_height);
        setWidth(p_width);
    }

    public float getHeight() {
        return a_height;
    }

    private static final DimensionValidationUtils dimensionValidator = new DimensionValidationUtils();

    public void setHeight(float p_height) {
        boolean isHeightValid = dimensionValidator.validateHeight(p_height);

        if (isHeightValid) {
            a_height = p_height;
        }
    }

    public float getWidth() {
        return a_width;
    }

    public void setWidth(float p_width){
        boolean isWidthValid = dimensionValidator.validateWidth(p_width);

        if (isWidthValid) {
            a_width = p_width;
        }
    }

    public float getLength() {
        return a_length;
    }

    public void setLength(float p_length) {
        boolean isLengthValid = dimensionValidator.validateLength(p_length);

        if (isLengthValid) {
            a_length = p_length;
        }
    }

    public void swapLengthAndWidth(){
        float currentWidth = a_width;
        a_width = a_length;
        a_length = currentWidth;
    }
}

