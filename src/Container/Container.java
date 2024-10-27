package Container;
public abstract class Container {
    private boolean available = true;
    protected int number = 0;

    public void setAvailable(boolean available) {
        this.available = available;
    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public abstract boolean isAvailable();

}
