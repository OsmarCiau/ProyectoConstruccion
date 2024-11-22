package Proyect.StoreKeeper;

import Proyect.Inventory.Furniture;
import Proyect.Logistics.Route;
import Proyect.StoreKeeper.Platform;
import Proyect.Validations.ValidationUtils;
import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderID = 0;

    private String destination = null;

    @Temporal(TemporalType.DATE)
    private LocalDate deliveryDate;

    private Duration totalAssemblyTime = Duration.ZERO;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Furniture> orderContent = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "route_id", nullable = true)  // Ruta asociada
    private Route route = null;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Platform> platforms;

    // Constructor vacío
    public Order() {}

    public Order(int p_orderID, String p_destination, LocalDate p_deliveryDate, List<Furniture> p_orderContent) {
        setOrderID(p_orderID);
        setDestination(p_destination);
        setDeliveryDate(p_deliveryDate);
        setOrderContent(p_orderContent);
        calculateAssemblyTime();
    }

    private void calculateAssemblyTime() {
        Duration totalAssemblyTime = Duration.ZERO;
        for (Furniture furniture : orderContent) {
            totalAssemblyTime = totalAssemblyTime.plus(calculateFurnitureBuildTime(furniture));
        }
        setTotalAssemblyTime(totalAssemblyTime);
    }

    private Duration calculateFurnitureBuildTime(Furniture p_furniture) {
        int buildTimeMinutes = p_furniture.getBuildTime() * p_furniture.getQuantity();
        return Duration.ofMinutes(buildTimeMinutes);
    }

    // Getter y Setter para orderID
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int p_orderID) {
        this.orderID = p_orderID;
    }

    public Duration getTotalAssemblyTime() {
        return totalAssemblyTime;
    }

    public void setTotalAssemblyTime(Duration p_assemblyTime) {
        ValidationUtils.validateNonNull(p_assemblyTime, "Assembly Time");
        this.totalAssemblyTime = p_assemblyTime;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String p_destination) {
        ValidationUtils.validateNonNull(p_destination, "Destination");
        this.destination = p_destination;
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

    public void setPlatforms(List<Platform> p_platforms) {
        this.platforms = p_platforms;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate p_deliveryDate) {
        ValidationUtils.validateNonNull(p_deliveryDate, "Delivery Date");
        this.deliveryDate = p_deliveryDate;
    }

    public List<Furniture> getOrderContent() {
        return orderContent;
    }

    public void setOrderContent(List<Furniture> p_orderContent) {
        ValidationUtils.validateNonNull(p_orderContent, "Order Content");
        this.orderContent = p_orderContent;
    }
}
