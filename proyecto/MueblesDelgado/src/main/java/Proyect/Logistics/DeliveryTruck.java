package Proyect.Logistics;

import Proyect.Validations.ValidationUtils;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class DeliveryTruck {

    @Id
    private String trackingNumber = null;
    private static final double DEFAULT_CAPACITY = 6500.0f; // 6,500 kg
    private double mileage = 0.0f; // en km

    public DeliveryTruck() {}

    public DeliveryTruck(String p_trackingNumber, double p_mileage) {
        setTrackingNumber(p_trackingNumber);
        setMileage(p_mileage);
    }

    public void setTrackingNumber(String p_trackingNumber) {
        ValidationUtils.validateNonNull(p_trackingNumber, "Tracking Number");
        this.trackingNumber = p_trackingNumber;
    }

    public void setMileage(double p_mileage) {
        ValidationUtils.validateGreaterThanZero(p_mileage, "DeliveryTruck Mileage");
        this.mileage = p_mileage;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public double getCapacity() {
        return DEFAULT_CAPACITY;
    }

    public double getMileage() {
        return mileage;
    }

    @Override
    public String toString() {
        return "\n" +
                "TrackingNumber:" + getTrackingNumber() +
                ", Capacity:" + getCapacity() +
                ", Mileage:" + getMileage();
    }
}
