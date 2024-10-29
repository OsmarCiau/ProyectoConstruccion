package Container;

import Validations.ValidationUtils;

public abstract class Container {
    private boolean available = false;
    protected int number = 0;

    public void setAvailable(boolean p_available) {
        this.available = p_available;
    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int p_number) {
        ValidationUtils.validatePositiveNumber(p_number, "Number");
        this.number = p_number;
    }

    public abstract boolean isAvailable();

}
