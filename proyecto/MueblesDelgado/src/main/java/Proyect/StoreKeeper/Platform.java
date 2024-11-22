package Proyect.StoreKeeper;

import Proyect.Inventory.Dimension;
import Proyect.Validations.ValidationUtils;

import javax.persistence.*;

@Entity
public class Platform {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long platformId; // Identificador único de la plataforma

    @ManyToOne(fetch = FetchType.LAZY)  // Relación muchos a uno con Order
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @OneToOne(fetch = FetchType.LAZY)  // Relación uno a uno con StorageKey
    @JoinColumn(name = "storage_key_id", nullable = false)
    private StorageKeys locationInRack;  // Ubicación de la plataforma en el rack (StorageKey)

    @Embedded
    private Dimension dimension;  // Uso de @Embedded para la clase Dimension

    public Platform() {
    }

    public Platform(Long p_platformId, Order p_order, Dimension p_dimension, StorageKeys p_locationInRack) {
        this.platformId = p_platformId;
        this.order = p_order;
        this.dimension = p_dimension;
        this.locationInRack = p_locationInRack;
    }

    // Métodos getters y setters
    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long p_platformId) {
        ValidationUtils.validateGreaterThanZero(p_platformId, "Platform ID");
        this.platformId = p_platformId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order p_order) {
        this.order = p_order;
    }

    public StorageKeys getLocationInRack() {
        return locationInRack;
    }

    public void setLocationInRack(StorageKeys p_locationInRack) {
        ValidationUtils.validateLocationInRack(p_locationInRack);
        this.locationInRack = p_locationInRack;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension p_dimension) {
        ValidationUtils.validateNonNull(p_dimension, "Dimension");
        this.dimension = p_dimension;
    }

    public float getLength() {
        return dimension.getLength();
    }

    public float getWidth() {
        return dimension.getWidth();
    }

    public float getHeight() {
        return dimension.getHeight();
    }
}
