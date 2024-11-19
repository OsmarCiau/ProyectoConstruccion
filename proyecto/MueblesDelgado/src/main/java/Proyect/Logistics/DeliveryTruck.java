package Proyect.Logistics;

import Proyect.Validations.ValidationUtils;
import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class DeliveryTruck {

    @Id
    private String trackingNumber = null;
    private double capacity = 0.0f; //cambiar a double?
    private double mileage = 0.0f;  //cambiar a double?

    public DeliveryTruck(){}
    public DeliveryTruck(String p_trackingNumber, double p_capacity, double p_mileage){
        setTrackingNumber(p_trackingNumber);
        setCapacity(p_capacity);
        setMileage(p_mileage);
    }

    public void setTrackingNumber(String p_trackingNumber) {
        ValidationUtils.validateNonNull(p_trackingNumber, "Tracking Number");
        this.trackingNumber = p_trackingNumber;
    }

    public void setCapacity(double p_capacity){
        ValidationUtils.validateGreaterThanZero(p_capacity, "DeliveryTruck Capacity");
        this.capacity = p_capacity;
    }

    public void setMileage(double p_mileage){
       ValidationUtils.validateGreaterThanZero(p_mileage, "DeliveryTruck Mileage");
       this.mileage = p_mileage;
    }

    public String getTrackingNumber() {
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
