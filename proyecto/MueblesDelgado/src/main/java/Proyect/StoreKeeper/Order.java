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
    private List<Furniture> orderContent = new ArrayList<>(); // Asumiendo que es solo para propósito temporal

    private Duration totalAssemblyTime = Duration.ZERO;

    // Enum para el estado de la orden
    public enum Status {
        PENDING,      // Orden creada, pero aún no procesada
        STORED,       // Orden almacenada en el almacén
        IN_TRANSIT,   // Orden en tránsito, asignada a una ruta
        DELIVERED     // Orden entregada al cliente
    }

    @Enumerated(EnumType.STRING) // Usamos el tipo de enumeración como string
    private Status status = Status.PENDING; // El valor inicial de la orden es PENDING

    @ManyToOne
    @JoinColumn(name = "route_id", nullable = true) // Ruta asociada
    private Route route = null;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true) // Relación bidireccional con Platform
    private List<Platform> platforms; // Lista de plataformas asociadas al pedido

    // Constructor vacío
    public Order() {}

    // Constructor con parámetros
    public Order(String p_destination, LocalDate p_deliveryDate) {
        setDestination(p_destination);
        setDeliveryDate(p_deliveryDate);
    }

    // Getter y Setter para orderID
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int p_orderID) {
        this.orderID = p_orderID;
    }

    // Getter y Setter para destination
    public String getDestination() {
        return destination;
    }

    public void setDestination(String p_destination) {
        this.destination = p_destination;
    }

    // Getter y Setter para deliveryDate
    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate p_deliveryDate) {
        this.deliveryDate = p_deliveryDate;
    }

    // Getter y Setter para totalAssemblyTime
    public Duration getTotalAssemblyTime() {
        return totalAssemblyTime;
    }

    public void setTotalAssemblyTime(Duration p_totalAssemblyTime) {
        this.totalAssemblyTime = p_totalAssemblyTime;
    }

    // Getter y Setter para route
    public Route getRoute() {
        return route;
    }

    public void setRoute(Route p_route) {
        this.route = p_route;
        if (p_route != null) {
            // Si la ruta es asignada, marcar la orden como "In Transit"
            this.status = Status.IN_TRANSIT;
        }
    }

    // Getter y Setter para platforms
    public List<Platform> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<Platform> platforms) {
        this.platforms = platforms;
    }

    // Getter y Setter para status
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    // Método para marcar la orden como entregada
    public void setStatusDelivered(boolean p_isDelivered) {
        if (p_isDelivered) {
            this.status = Status.DELIVERED; // Si se marca como entregado, cambiamos el estado
        }
    }

    // Método para cambiar el estado de la orden
    public void updateStatus(Status newStatus) {
        this.status = newStatus;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", destination='" + destination + '\'' +
                ", deliveryDate=" + deliveryDate +
                ", totalAssemblyTime=" + totalAssemblyTime +
                ", status=" + status + // Mostrar el estado de la orden
                ", route=" + (route != null ? route.getRouteId() : "No route assigned") +
                '}';
    }
}
