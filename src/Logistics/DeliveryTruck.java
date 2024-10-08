package Logistics;

public class DeliveryTruck {
    private int trackingNumber = 0;
    private float capacity = 0.0f; //cambiar a double?
    private float mileage = 0.0f;  //cambiar a double?

    public DeliveryTruck(int trackingNumber, float capacity, float mileage){
        setTrackingNumber(trackingNumber);
        setCapacity(capacity);
        setMileage(mileage);
    }

    private void setTrackingNumber(int trackingNumber) {
        int minimumValue = 0;
        if(trackingNumber > minimumValue){
            this.trackingNumber = trackingNumber;
        }else{
            throw new IllegalArgumentException("TRACKING NUMBER MUST BE GREATER THAN 0");
        }
    }

    private void setCapacity(float capacity){
        float minimumCapacity = 0.0f; //definir capacidad minima
        if(capacity > minimumCapacity){
            this.capacity = capacity;
        }else{
            throw new IllegalArgumentException("CAPACITY MUST BE GREATER THAN 0.0");
        }
    }

    private void setMileage(float mileage){
        float minimumMileage = 0.0f; //definir mileage minima
        if(mileage > minimumMileage){
            this.mileage = mileage;
        }else{
            throw new IllegalArgumentException("MILEAGE MUST BE GREATER THAN 0.0");
        }
    }


    public int getTrackingNumber() {
        return trackingNumber;
    }

    public float getCapacity() {
        return capacity;
    }

    public float getMileage() {
        return mileage;
    }

    @Override
    public String toString() {
        return "DeliveryTruck{" +
                "trackingNumber:" + trackingNumber +
                ", capacity:" + capacity +
                ", mileage:" + mileage +
                '}';
    }
}
