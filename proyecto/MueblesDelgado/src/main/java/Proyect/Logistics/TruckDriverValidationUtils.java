package Proyect.Logistics;

public class TruckDriverValidationUtils {
    public boolean validateName(String p_name){
        boolean isValid = false;
        if(p_name != null){
            isValid = true;
        }else{
            throw new IllegalArgumentException("NAME REQUIRED");
        }
        return isValid;
    }

    public boolean validateLicenseNumber(int p_licenseNumber){
        int minimumValue = 0;
        boolean isValid = false;

        if(p_licenseNumber > minimumValue){
            isValid = true;
        }else{
            throw new IllegalArgumentException("LICENSE NUMBER MUST BE GREATER THAN 0");
        }
        return isValid;
    }
}
