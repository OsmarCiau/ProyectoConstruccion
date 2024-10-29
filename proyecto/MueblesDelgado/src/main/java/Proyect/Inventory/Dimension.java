package Proyect.Inventory;

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

    public void setHeight(float p_height) {
        DimensionValidationUtils.validateHeight(p_height);
        a_height = p_height;
    }

    public float getWidth() {
        return a_width;
    }

    public void setWidth(float p_width){
        DimensionValidationUtils.validateWidth(p_width);
        a_width = p_width;
    }

    public float getLength() {
        return a_length;
    }

    public void setLength(float p_length) {
        DimensionValidationUtils.validateLength(p_length);
        a_length = p_length;
    }

    public void swapLengthAndWidth(){
        float temp = a_width;
        a_width = a_length;
        a_length = temp;
    }
}
