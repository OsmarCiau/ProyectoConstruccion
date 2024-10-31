package Proyect.Logistics;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class OrderTruckAssignment {

    @Id
    private int assignmentId; // Ensure you have a unique ID for the entity


    //falta relacion ManyToOne
    private int orderId; // Reference to the order
    private int truckId; // Reference to the truck

    // Getters and setters
    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getTruckId() {
        return truckId;
    }

    public void setTruckId(int truckId) {
        this.truckId = truckId;
    }
}
