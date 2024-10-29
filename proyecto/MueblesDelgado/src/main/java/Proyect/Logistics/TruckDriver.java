package Proyect.Logistics;

public class TruckDriver {
    private String name = null;
    private int licenseNumber = 0;
    
    TruckDriverValidationUtils truckDriverValidator = new TruckDriverValidationUtils();


    public TruckDriver(String p_name, int p_licenseNumber){
        setName(p_name);
        setLicenseNumber(p_licenseNumber);
    }

    private void setName(String p_name) {
        boolean nameIsValid = truckDriverValidator.validateName(p_name);
        if(nameIsValid){
            this.name = p_name;
        }
    }


    private void setLicenseNumber(int p_licenseNumber){
        boolean licenseNumberIsValid = truckDriverValidator.validateLicenseNumber(p_licenseNumber);
        if(licenseNumberIsValid){
            this.licenseNumber = p_licenseNumber;
        }

    }

    public String getName() {
        return name;
    }

    public int getLicenseNumber() {
        return licenseNumber;
    }

    @Override
    public String toString() {
        return "\n" +
                "name: " + getName() +
                ", licenseNumber:" + getLicenseNumber() ;
    }
}
