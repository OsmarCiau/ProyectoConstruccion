package proyecto.mueblesdelgado.Inventory;

import jakarta.persistence.Embeddable;

@Embeddable  // Indica que esta clase puede ser embebida en otra entidad
public class Dimension {

    // Atributos de la clase
    private float length;
    private float height;
    private float width;

    // Constructor vacío requerido por JPA
    public Dimension() {
    }

    // Constructor completo con validaciones
    public Dimension(float length, float height, float width) {
        setLength(length);
        setHeight(height);
        setWidth(width);
    }

    // Getters y Setters con validaciones
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

    public void setWidth(float width) {
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
