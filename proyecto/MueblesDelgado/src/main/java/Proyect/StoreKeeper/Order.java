package Proyect.StoreKeeper;

import Proyect.Inventory.Furniture;
import Proyect.Logistics.Route;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true) // Relación bidireccional con Platform
    private List<Platform> platforms; // Lista de plataformas asociadas al pedido

    public Order() {}

    public Order(String p_destination, LocalDate p_deliveryDate) {
        setDestination(p_destination);
        setDeliveryDate(p_deliveryDate);
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int p_orderID) {
        this.orderID = p_orderID;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String p_destination) {
        this.destination = p_destination;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate p_deliveryDate) {
        this.deliveryDate = p_deliveryDate;
    }

    public Duration getTotalAssemblyTime() {
        return totalAssemblyTime;
    }

    public void setTotalAssemblyTime(Duration p_totalAssemblyTime) {
        this.totalAssemblyTime = p_totalAssemblyTime;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route p_route) {
        this.route = p_route;
    }

    public List<Platform> getPlatforms() {
        return platforms;
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
