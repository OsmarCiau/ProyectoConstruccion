public class Dimension {

    /*
    Esta clase no está dentro del paquete.
    Se puso para poder programar sin errores
     */
    private float lenght;
    private float height;
    private float width;

    public Dimension(){
        lenght = 0;
        height = 0;
        width = 0;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getLenght() {
        return lenght;
    }

    public void setLenght(float lenght) {
        this.lenght = lenght;
    }
}

