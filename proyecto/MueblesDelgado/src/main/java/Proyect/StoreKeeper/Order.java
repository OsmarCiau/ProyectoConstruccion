package Proyect.StoreKeeper;

import Proyect.Inventory.Furniture;
import Proyect.Logistics.Route;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.Duration;
import java.util.ArrayList;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderID;

    private String destination;

    @Temporal(TemporalType.DATE)
    private LocalDate deliveryDate;

    @Transient
    private ArrayList<Furniture> orderContent = new ArrayList<>();

    private Duration totalAssemblyTime = Duration.ZERO;

    @ManyToOne
    @JoinColumn(name = "route_id", nullable = true) // Ruta asociada
    private Route route = null;

    public Order() {}

    public Order(String p_destination, LocalDate p_deliveryDate) {
        setDestination(p_destination);
        setDeliveryDate(p_deliveryDate);
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Duration getTotalAssemblyTime() {
        return totalAssemblyTime;
    }

    public void setTotalAssemblyTime(Duration totalAssemblyTime) {
        this.totalAssemblyTime = totalAssemblyTime;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", destination='" + destination + '\'' +
                ", deliveryDate=" + deliveryDate +
                ", totalAssemblyTime=" + totalAssemblyTime +
                ", route=" + (route != null ? route.getRouteId() : "No route assigned") +
                '}';
    }
}
