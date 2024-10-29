package Proyect.Logistics;

public class TruckDriverValidationUtils {
    public void validateName(String p_name){
        if(p_name == null){
            throw new IllegalArgumentException("NAME REQUIRED");
        }
    }

    public void validateLicenseNumber(int p_licenseNumber){
        int minimumValue = 0;

        if(p_licenseNumber <= minimumValue){
            throw new IllegalArgumentException("LICENSE NUMBER MUST BE GREATER THAN 0");
        }
    } 
    
}
