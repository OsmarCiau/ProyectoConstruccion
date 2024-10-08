package Logistics;

public class TruckDriver {
    private String name = null;
    private int licenseNumber = 0;

    public TruckDriver(String name, int licenseNumber){
        setName(name);
        setLicenseNumber(licenseNumber);
    }

    private void setName(String name) {
        if(name != null){ //validar que no sea un espacio en blanco (hacer la evaluación de la condición en otra rutina) ¿?
            this.name = name;
        }else{
            throw new IllegalArgumentException("NAME REQUIRED");
        }

    }

    private void setLicenseNumber(int licenseNumber){
        int minimumValue = 0;
        if(licenseNumber > minimumValue){
            this.licenseNumber = licenseNumber;
        }else{
            throw new IllegalArgumentException("LICENSE NUMBER MUST BE GREATER THAN 0");
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
        return "TruckDriver" +
                "name:'" + name + '\'' +
                ", licenseNumber:" + licenseNumber +
                '}';
    }
}
