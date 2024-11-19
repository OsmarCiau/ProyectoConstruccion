package Proyect.Logistics;

import Proyect.Validations.ValidationUtils;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TruckDriver {

    @Id
    private int licenseNumber = 0;
    private String name = null;
    public TruckDriver() {}

    public TruckDriver(String p_name, int p_licenseNumber){
        setName(p_name);
        setLicenseNumber(p_licenseNumber);
    }

    public void setName(String p_name) {
        ValidationUtils.validateNonNull(p_name, "Name");
        this.name = p_name;
    }

    public void setLicenseNumber(int p_licenseNumber){
        ValidationUtils.validateGreaterThanZero(p_licenseNumber, "License Number");
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
