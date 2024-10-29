package Proyect.Logistics;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class TruckAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int assignmentId = 0; // Nombre más descriptivo

    @ManyToOne
    @JoinColumn(name = "tracking_number") // Columna que referencia al camión
    private DeliveryTruck deliveryTruck = new DeliveryTruck();

    @ManyToOne
    @JoinColumn(name = "driver_id") // Columna que referencia al conductor
    private TruckDriver truckDriver = new TruckDriver();

    private static final TruckAssignmentValidationUtils truckAssignmentValidator = new TruckAssignmentValidationUtils();

    // Constructor por defecto
    public TruckAssignment() {}

    // Constructor con parámetros
    public TruckAssignment(DeliveryTruck p_deliveryTruck, TruckDriver p_truckDriver) {
        setDeliveryTruck(p_deliveryTruck);
        setTruckDriver(p_truckDriver);
    }

    // Setters con validación
    public void setDeliveryTruck(DeliveryTruck p_deliveryTruck) {
        truckAssignmentValidator.validateDeliveryTruck(p_deliveryTruck);
        this.deliveryTruck = p_deliveryTruck;
    }

    public void setTruckDriver(TruckDriver p_truckDriver) {
        truckAssignmentValidator.validateTruckDriver(p_truckDriver);
        this.truckDriver = p_truckDriver;
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public DeliveryTruck getDeliveryTruck() {
        return deliveryTruck;
    }

    public TruckDriver getTruckDriver() {
        return truckDriver;
    }

    @Override
    public String toString() {
        return "TruckAssignment{" +
                "assignmentId=" + assignmentId +
                ", deliveryTruck=" + deliveryTruck +
                ", truckDriver=" + truckDriver +
                '}';
    }
}
