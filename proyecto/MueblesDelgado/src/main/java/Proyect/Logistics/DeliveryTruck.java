package Proyect.Logistics;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class DeliveryTruck {

    @Id
    private int trackingNumber = 0;
    private double capacity = 0.0f; //cambiar a double?
    private double mileage = 0.0f;  //cambiar a double?

    private static final DeliveryTruckValidationUtils deliveryTruckValidator = new DeliveryTruckValidationUtils();

    public DeliveryTruck(){}
    public DeliveryTruck(int p_trackingNumber, double capacity, double mileage){
        setTrackingNumber(p_trackingNumber);
        setCapacity(capacity);
        setMileage(mileage);
    }

    public void setTrackingNumber(int p_trackingNumber) {
        boolean trackingNumberIsValid = deliveryTruckValidator.validateTrackingNumber(p_trackingNumber);

        if(trackingNumberIsValid){
            this.trackingNumber = p_trackingNumber;
        }
    }

    public void setCapacity(double p_capacity){
        boolean capacityIsValid = deliveryTruckValidator.validateCapacity(p_capacity);

        if(capacityIsValid){
            this.capacity = p_capacity;
        }
    }

    public void setMileage(double p_mileage){
        boolean mileageIsValid = deliveryTruckValidator.validateMileage(p_mileage);
        if(mileageIsValid){
            this.mileage = p_mileage;
        }
    }

    public int getTrackingNumber() {
        return trackingNumber;
    }

    public double getCapacity() {
        return capacity;
    }

    public double getMileage() {
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