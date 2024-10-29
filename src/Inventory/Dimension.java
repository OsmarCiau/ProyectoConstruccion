package Inventory;

import Validations.ValidationUtils;

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

    public void setHeight(float p_height) {
        ValidationUtils.validatePositiveNumber(p_height, "Height");
        this.height = p_height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float p_width){
        ValidationUtils.validatePositiveNumber(p_width, "Width");
        this.width = p_width;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float p_length) {
        ValidationUtils.validatePositiveNumber(p_length, "Length");
        this.length = p_length;
    }

    public void swapLengthAndWidth(){
        float currentWidth = width;
        width = length;
        length = currentWidth;
    }
}

