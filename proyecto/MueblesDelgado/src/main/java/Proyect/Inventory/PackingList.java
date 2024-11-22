package Proyect.Inventory;

import Proyect.Validations.ValidationUtils;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class PackingList {

    @Id
    private int folio;

    @OneToMany(mappedBy = "packingList", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Furniture> products = new ArrayList<>();

    @Temporal(TemporalType.DATE)
    private Date arrivalDate;

    // Constructor completo
    public PackingList(int p_folio, List<Furniture> p_products, Date p_arrivalDate) {
        setFolio(p_folio);
        setProducts(p_products);
        setArrivalDate(p_arrivalDate);
    }

    // Constructor vacío para JPA
    public PackingList() {
        this.products = new ArrayList<>();
    }

    // Métodos getters y setters
    public int getFolio() {
        return folio;
    }

    public void setFolio(int p_folio) {
        ValidationUtils.validateGreaterThanZero(p_folio, "Folio");
        this.folio = p_folio;
    }

    public List<Furniture> getProducts() {
        return products;
    }

    public void setProducts(List<Furniture> p_products) {
        ValidationUtils.validatesList(p_products, "Products");
        // Establecer la relación bidireccional
        for (Furniture product : p_products) {
            product.setPackingList(this);
        }
        this.products = p_products;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date p_arrivalDate) {
        ValidationUtils.validateNonNull(p_arrivalDate, "Arrival Date");
        this.arrivalDate = p_arrivalDate;
    }

    // Método para añadir un producto individualmente
    public void addProduct(Furniture product) {
        ValidationUtils.validateNonNull(product, "Product");
        // Configurar la relación bidireccional
        product.setPackingList(this);
        products.add(product);
    }

    // Método para eliminar un producto individualmente
    public void removeProduct(Furniture product) {
        ValidationUtils.validateNonNull(product, "Product");
        products.remove(product);
        product.setPackingList(null); // Romper la relación bidireccional
    }

    @Override
    public String toString() {
        return "PackingList{" +
                "folio=" + folio +
                ", arrivalDate=" + arrivalDate +
                ", products=" + products +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        PackingList that = (PackingList) obj;

        return folio == that.folio;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(folio);
    }
}
