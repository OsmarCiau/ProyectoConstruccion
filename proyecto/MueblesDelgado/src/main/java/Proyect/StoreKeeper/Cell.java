package Proyect.StoreKeeper;

import Proyect.Container.Container;
import jakarta.persistence.*;
import Proyect.Inventory.Dimension;
import Proyect.Validations.ValidationUtils;

@Entity
public class Cell extends Container {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cellNumber; // ID único para la celda

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storage_key_id", referencedColumnName = "storageId", nullable = true)
    private StorageKeys storageKey; // Relación con StorageKey (la celda está asociada a un StorageKey)

    @Embedded
    private Dimension dimension; // Embedding la dimensión

    private float occupiedSpace = 0;

    // Constructor vacío para JPA
    public Cell() {
        this.dimension = new Dimension(1, 1, 1);
    }

    // Constructor con parámetros
    public Cell(int p_cellNumber, float p_occupiedSpace) {
        setAvailable(true);
        setNumber(p_cellNumber);
        this.dimension = new Dimension(5, 5, 5); // Asumimos que la dimensión es 5x5x5
        setOccupiedSpace(p_occupiedSpace);
    }

    // Getter y Setter para id
    public int getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(int p_cellNumber) {
        this.cellNumber = p_cellNumber;
    }

    // Getter y Setter para StorageKey
    public StorageKeys getStorageKey() {
        return storageKey;
    }

    public void setStorageKey(StorageKeys p_storageKey) {
        this.storageKey = p_storageKey;
    }

    // Getter y Setter para dimension
    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension p_dimension) {
        this.dimension = p_dimension;
    }

    // Getter y Setter para occupiedSpace
    public float getOccupiedSpace() {
        return this.occupiedSpace;
    }

    public void setOccupiedSpace(float p_occupiedSpace) {
        ValidationUtils.validateNonNegativeNumber(p_occupiedSpace, "Occupied space in Cell");
        this.occupiedSpace = p_occupiedSpace;
    }

    // Método isAvailable para verificar si la celda está disponible
    @Override
    public boolean isAvailable() {
        return (this.dimension.getLength() - this.occupiedSpace) > 0;
    }

    // Información de la celda
    public void getInfo() {
        System.out.println("Cell number: " + number + "\n" + // celNumber
                "Dimension: " + this.dimension.getLength() + " x " + this.dimension.getWidth() + " x " + this.dimension.getHeight() + "\n" +
                "Occupied space (length): " + this.occupiedSpace + "\n" +
                "Available: " + isAvailable());
    }

    // Obtener espacio disponible en la celda
    public float getAvailableSpace() {
        return isAvailable() ? this.dimension.getLength() - this.occupiedSpace : 0;
    }

    // Métodos para añadir y quitar espacio ocupado
    public void addSpaceOccupiedInCell(float p_platformLength) {
        this.occupiedSpace += p_platformLength;
    }

    public void removeSpaceOccupiedInCell(float p_platformLength) {
        this.occupiedSpace -= p_platformLength;
    }

    // Getters para las dimensiones
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
