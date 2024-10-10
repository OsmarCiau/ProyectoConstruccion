package Inventory;

public class Dimension {

    private float length;
    private float height;
    private float width;

    public Dimension(){
        length = 0;
        height = 0;
        width = 0;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        if (height > 0) {
            this.height = height;
        } else {
            System.out.println("Error: height must be a positive value.");
        }
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        if (width > 0) {
            this.width = width;
        } else {
            System.out.println("Error: width must be a positive value.");
        }
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        if (length > 0) {
            this.length = length;
        } else {
            System.out.println("Error: length must be a positive value.");
        }
    }
}

