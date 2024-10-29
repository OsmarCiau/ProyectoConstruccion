package Proyect.Logistics;

public class DeliveryTruck {
    private int trackingNumber = 0;
    private float capacity = 0.0f; //cambiar a double?
    private float mileage = 0.0f;  //cambiar a double?

    DeliveryTruckValidationUtils deliveryTruckValidator = new DeliveryTruckValidationUtils();

    public DeliveryTruck(int p_trackingNumber, float capacity, float mileage){
        setTrackingNumber(p_trackingNumber);
        setCapacity(capacity);
        setMileage(mileage);
    }

    private void setTrackingNumber(int p_trackingNumber) {
        boolean trackingNumberIsValid = deliveryTruckValidator.validateTrackingNumber(p_trackingNumber);

        if(trackingNumberIsValid){
            this.trackingNumber = p_trackingNumber;
        }
    }
   

    private void setCapacity(float p_capacity){
        boolean capacityIsValid = deliveryTruckValidator.validateCapacity(p_capacity);

        if(capacityIsValid){
            this.capacity = p_capacity;
        }
    }


    private void setMileage(float p_mileage){
        boolean mileageIsValid = deliveryTruckValidator.validateMileage(p_mileage);
        if(mileageIsValid){
            this.mileage = p_mileage;
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
        return
                "\n"+"TrackingNumber:" + getTrackingNumber() +
                        ", Capacity:" + getCapacity() +
                        ", Mileage:" + getMileage() ;
    }
}
