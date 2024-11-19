package Proyect.Logistics;

import Proyect.Validations.ValidationUtils;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class OrderTruckAssignment {

    @Id
    private int assignmentId; // Ensure you have a unique ID for the entity


    //falta relacion ManyToOne
    private int orderId; // Reference to the order
    private int truckId; // Reference to the truck

    public OrderTruckAssignment(){}

    public OrderTruckAssignment(int p_assignmentId, int p_orderId, int p_truckId){
        setAssignmentId(p_assignmentId);
        setOrderId(p_orderId);
        setTruckId(p_truckId);
    }

    // Getters and setters
    public void setAssignmentId(int p_assignmentId) {
        ValidationUtils.validateGreaterThanZero(p_assignmentId, "Assignment Id");
        this.assignmentId = p_assignmentId;
    }

    public void setOrderId(int p_orderId) {
        ValidationUtils.validateGreaterThanZero(p_orderId, "Order Id");
        this.orderId = p_orderId;
    }

    public void setTruckId(int p_truckId) {
        ValidationUtils.validateGreaterThanZero(p_truckId, "Truck Id");
        this.truckId = p_truckId;
    }

    public int getAssignmentId() {
        return assignmentId;
    }
    public int getTruckId() {
        return truckId;
    }
    public int getOrderId() { return orderId; }
}
