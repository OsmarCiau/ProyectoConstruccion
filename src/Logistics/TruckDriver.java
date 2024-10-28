package Logistics;

public class TruckDriver {
    private String a_name = null;
    private int a_licenseNumber = 0;

    public TruckDriver(String p_name, int p_licenseNumber){
        setName(p_name);
        setLicenseNumber(p_licenseNumber);
    }

    private void setName(String p_name) {
        boolean nameIsValid = validateName(p_name);
        if(nameIsValid){
            this.a_name = p_name;
        }
    }

    private boolean validateName(String p_name){
        boolean isValid = false;
        if(p_name != null){
            isValid = true;
        }else{
            throw new IllegalArgumentException("NAME REQUIRED");
        }
        return isValid;
    }

    private void setLicenseNumber(int p_licenseNumber){
        boolean licenseNumberIsValid = validateLicenseNumber(p_licenseNumber);
        if(licenseNumberIsValid){
            this.a_licenseNumber = p_licenseNumber;
        }

    }

    private boolean validateLicenseNumber(int p_licenseNumber){
        int minimumValue = 0;
        boolean isValid = false;

        if(p_licenseNumber > minimumValue){
            isValid = true;
        }else{
            throw new IllegalArgumentException("LICENSE NUMBER MUST BE GREATER THAN 0");
        }
        return isValid;
    }

    public String getName() {
        return a_name;
    }

    public int getLicenseNumber() {
        return a_licenseNumber;
    }

    @Override
    public String toString() {
        return "\n" +
                "name: " + getName() +
                ", licenseNumber:" + getLicenseNumber() ;
    }
}
