package Proyect.Logistics;

import Proyect.Validations.ValidationUtils;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class DeliveryTruck {

    @Id
    private int trackingNumber = 0;
    private double capacity = 0.0f; //cambiar a double?
    private double mileage = 0.0f;  //cambiar a double?

    public DeliveryTruck(){}
    public DeliveryTruck(int p_trackingNumber, double p_capacity, double p_mileage){
        setTrackingNumber(p_trackingNumber);
        setCapacity(p_capacity);
        setMileage(p_mileage);
    }

    public void setTrackingNumber(int p_trackingNumber) {
        ValidationUtils.validateGreaterThanZero(p_trackingNumber, "Tracking Number");
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
