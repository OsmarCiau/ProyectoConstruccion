package Proyect.Logistics;


import jakarta.persistence.*;

@Entity
public class OrderTruckAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "truck_id", referencedColumnName = "trackingNumber")
    private DeliveryTruck deliveryTruck;

    @ManyToOne
    @JoinColumn(name = "route_id", referencedColumnName = "routeId")
    private Route route;

    // Constructor vacío (requerido por JPA)
    public OrderTruckAssignment() {}

    // Constructor
    public OrderTruckAssignment(DeliveryTruck deliveryTruck, Route route) {
        this.deliveryTruck = deliveryTruck;
        this.route = route;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DeliveryTruck getDeliveryTruck() {
        return deliveryTruck;
    }

    public void setDeliveryTruck(DeliveryTruck deliveryTruck) {
        this.deliveryTruck = deliveryTruck;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }
}
