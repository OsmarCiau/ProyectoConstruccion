package Inventory;

public class Dimension {
    private float a_length;
    private float a_height;
    private float a_width;

    public Dimension(float p_length, float p_height, float p_width){
        this.a_length = p_length;
        this.a_height = p_height;
        this.a_width = p_width;
    }

    public float getHeight() {
        return a_height;
    }

    public void setHeight(float p_height) {
        validateHeight(p_height);
        this.a_height = p_height;
    }

    private void validateHeight(float p_height) {
        if (p_height < 0) {
            throw new IllegalArgumentException("Height cannot be negative");
        }
    }

    public float getWidth() {
        return a_width;
    }

    public void setWidth(float p_width){
        validateWidth(p_width);
        this.a_width = p_width;
    }

    private void validateWidth(float p_width) {
        if (p_width < 0) {
            throw new IllegalArgumentException("Width cannot be negative");
        }
    }

    public float getLength() {
        return a_length;
    }

    public void setLength(float p_length) {
        validateLength(p_length);
        this.a_length = p_length;
    }

    private void validateLength(float p_length) {
        if (p_length < 0) {
            throw new IllegalArgumentException("Length cannot be negative");
        }
    }

    public void swapLengthAndWidth(){
        float temp = a_width;
        a_width = a_length;
        a_length = temp;
    }
}

