package proyecto.mueblesdelgado.Container;
public abstract class Container {
    private boolean available;

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public abstract boolean isAvailable();

}
