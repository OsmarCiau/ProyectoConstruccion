package Proyect.Logistics;

public class DeliveryTruckValidationUtils {

    public boolean validateTrackingNumber(int p_trackingNumber){
        int minimumValue = 0;
        boolean isValid = false;

        if(p_trackingNumber > minimumValue){
            isValid = true;
        }else{
            throw new IllegalArgumentException("TRACKING NUMBER MUST BE GREATER THAN 0");
        }

        return isValid;

    }

    public boolean validateCapacity(double p_capacity){
        double minimumCapacity = 0.0f; //definir capacidad minima
        boolean isValid = false;

        if(p_capacity > minimumCapacity){
            isValid = true;
        }else{
            throw new IllegalArgumentException("CAPACITY MUST BE GREATER THAN 0.0");
        }

        return isValid;
    }

    public boolean validateMileage(double p_mileage){
        double minimumMileage = 0.0f; //definir mileage minima
        boolean isValid = false;

        if(p_mileage > minimumMileage){
            isValid = true;
        }else{
            throw new IllegalArgumentException("MILEAGE MUST BE GREATER THAN 0.0");
        }


        return isValid;
    }

}
