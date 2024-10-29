package Proyect.Logistics;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TruckDriver {

    @Id
    private int licenseNumber = 0;
    private String name = null;

    private static final TruckDriverValidationUtils truckDriverValidator = new TruckDriverValidationUtils();
    public TruckDriver() {}

    public TruckDriver(String p_name, int p_licenseNumber){
        setName(p_name);
        setLicenseNumber(p_licenseNumber);
    }

    public void setName(String p_name) {
        truckDriverValidator.validateName(p_name);
        this.name = p_name;
    }

    public void setLicenseNumber(int p_licenseNumber){
        truckDriverValidator.validateLicenseNumber(p_licenseNumber);
        this.licenseNumber = p_licenseNumber;
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
