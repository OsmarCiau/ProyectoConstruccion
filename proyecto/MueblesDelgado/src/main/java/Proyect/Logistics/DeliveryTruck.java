package Proyect.Logistics;

public class DeliveryTruck {
    private int a_trackingNumber = 0;
    private float a_capacity = 0.0f; //cambiar a double?
    private float a_mileage = 0.0f;  //cambiar a double?

    public DeliveryTruck(int p_trackingNumber, float capacity, float mileage){
        setTrackingNumber(p_trackingNumber);
        setCapacity(capacity);
        setMileage(mileage);
    }

    private void setTrackingNumber(int p_trackingNumber) {
        boolean trackingNumberIsValid = validateTrackingNumber(p_trackingNumber);

        if(trackingNumberIsValid){
            this.a_trackingNumber = p_trackingNumber;
        }
    }
    private boolean validateTrackingNumber(int p_trackingNumber){
        int minimumValue = 0;
        boolean isValid = false;

        if(p_trackingNumber > minimumValue){
            isValid = true;
        }else{
            throw new IllegalArgumentException("TRACKING NUMBER MUST BE GREATER THAN 0");
        }

        return isValid;

    }

    private void setCapacity(float p_capacity){
        boolean capacityIsValid = validateCapacity(p_capacity);

        if(capacityIsValid){
            this.a_capacity = p_capacity;
        }
    }

    private boolean validateCapacity(float p_capacity){
        float minimumCapacity = 0.0f; //definir capacidad minima
        boolean isValid = false;

        if(p_capacity > minimumCapacity){
            isValid = true;
        }else{
            throw new IllegalArgumentException("CAPACITY MUST BE GREATER THAN 0.0");
        }

        return isValid;
    }

    private void setMileage(float p_mileage){
        boolean mileageIsValid = validateMileage(p_mileage);
        if(mileageIsValid){
            this.a_mileage = p_mileage;
        }
    }

    private boolean validateMileage(float p_mileage){
        float minimumMileage = 0.0f; //definir mileage minima
        boolean isValid = false;

        if(p_mileage > minimumMileage){
            isValid = true;
        }else{
            throw new IllegalArgumentException("MILEAGE MUST BE GREATER THAN 0.0");
        }


        return isValid;
    }


    public int getTrackingNumber() {
        return a_trackingNumber;
    }

    public float getCapacity() {
        return a_capacity;
    }

    public float getMileage() {
        return a_mileage;
    }


    @Override
    public String toString() {
        return
                "\n"+"TrackingNumber:" + getTrackingNumber() +
                        ", Capacity:" + getCapacity() +
                        ", Mileage:" + getMileage() ;
    }
}