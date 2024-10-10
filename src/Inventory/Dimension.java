package Inventory;
public class Dimension {
    private float length;
    private float height;
    private float width;

    public Dimension(float length, float height, float width){
        this.length = length;
        this.height = height;
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        if (height < 0) {
            throw new IllegalArgumentException("Height cannot be negative");
        }
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width){
        if (width < 0) {
            throw new IllegalArgumentException("Width cannot be negative");
        }
        this.width = width;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        if (length < 0) {
            throw new IllegalArgumentException("Length cannot be negative");
        }
        this.length = length;
    }
}

