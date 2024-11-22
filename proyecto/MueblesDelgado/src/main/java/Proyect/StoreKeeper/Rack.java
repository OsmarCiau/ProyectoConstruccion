package Proyect.StoreKeeper;

import jakarta.persistence.*;
import java.util.List;

import Proyect.Container.Container;

@Entity
public class Rack extends Container{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rackNumber; // Número del rack

    @OneToMany(mappedBy = "rack", fetch = FetchType.LAZY)
    private List<StorageKeys> storageKeys; // Relación con StorageKey (almacena celdas y pedidos asociados)

    // Constructor
    public Rack() {}

    public Rack(int p_rackNumber) {
        this.rackNumber = p_rackNumber;
    }

    // Getter y Setter para rackNumber
    public int getRackNumber() {
        return rackNumber;
    }

    public void setRackNumber(int p_rackNumber) {
        this.rackNumber = p_rackNumber;
    }

    // Getter y Setter para StorageKey
    public List<StorageKeys> getStorageKeys() {
        return storageKeys;
    }

    public void setStorageKeys(List<StorageKeys> p_storageKeys) {
        this.storageKeys = p_storageKeys;
    }

    @Override
    public boolean isAvailable() {
        //Si alguna celda está disponible, el rack también lo está
        for (StorageKeys storageKey : storageKeys) {
            if (storageKey.getCell().isAvailable()) {
                return true;
            }
        }
        return false;
    }

    // Información del rack
    public void getInfo() {
        System.out.println("Rack number: " + rackNumber);
        System.out.println("Rack available: " + isAvailable());
        System.out.println("Cells in rack: " + storageKeys.size()); // Número de celdas en el rack
        // se obtiene con el tamaño de la lista de StorageKeys, ya que cada StorageKey tiene una celda asociada en el rack
    }
}
