package Proyect.StoreKeeper;

import jakarta.persistence.*;

@Entity
public class StorageKeys {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storageId; // ID único para StorageKey

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rack_id", referencedColumnName = "rackNumber", nullable = false)
    private Rack rack; // Relación con Rack

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cell_id", referencedColumnName = "cellNumber", nullable = false)
    private Cell cell; // Relación con Cell

    @ManyToOne(fetch = FetchType.LAZY)  // Relación muchos a uno con Platform
    @JoinColumn(name = "platform_id", nullable = false)
    private Platform platform;  // Plataforma en la que se encuentra este StorageKey (asocia la ubicación)


    // Constructor vacío
    public StorageKeys() {}

    // Constructor con parámetros
    public StorageKeys(Rack p_rack, Cell p_cell, Platform p_platform) {
        this.rack = p_rack;
        this.cell = p_cell;
        this.platform = p_platform;
    }

    // Getters y Setters
    public Long getStorageId() {
        return storageId;
    }

    public void setStorageId(Long p_storageId) {
        this.storageId = p_storageId;
    }

    public Rack getRack() {
        return rack;
    }

    public void setRack(Rack p_rack) {
        this.rack = p_rack;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell p_cell) {
        this.cell = p_cell;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform p_platform) {
        this.platform = p_platform;
    }
}
