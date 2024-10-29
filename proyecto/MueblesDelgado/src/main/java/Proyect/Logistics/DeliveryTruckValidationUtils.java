package Proyect.Logistics;

public class DeliveryTruckValidationUtils {

    public void validateTrackingNumber(int p_trackingNumber){
        int minimumValue = 0;

        if(p_trackingNumber <= minimumValue){
            throw new IllegalArgumentException("TRACKING NUMBER MUST BE GREATER THAN 0");
        }

    }

    public void validateCapacity(double p_capacity){
        double minimumCapacity = 0.0f; //definir capacidad minima

        if(p_capacity <= minimumCapacity){
            throw new IllegalArgumentException("CAPACITY MUST BE GREATER THAN 0.0");
        }
    }


    public void validateMileage(double p_mileage){
        double minimumMileage = 0.0f; //definir mileage minima

        if(p_mileage <= minimumMileage){
            throw new IllegalArgumentException("MILEAGE MUST BE GREATER THAN 0.0");
        }
    }

}
