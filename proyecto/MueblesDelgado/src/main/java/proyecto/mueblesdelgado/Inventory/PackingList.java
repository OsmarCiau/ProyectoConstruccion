package proyecto.mueblesdelgado.Inventory;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity // Indica que esta clase es una entidad JPA
public class PackingList {

	@Id // Marca el campo 'folio' como la clave primaria
	private int folio;

	@OneToMany // Indica una relación uno a muchos con la entidad Furniture
	private List<Furniture> products;

	@Temporal(TemporalType.DATE) // Indica que este campo debe ser tratado como una fecha
	private Date arrivalDate;

	// Constructor vacío requerido por JPA
	public PackingList() {
	}

	// Constructor completo
	public PackingList(int folio, List<Furniture> products, Date arrivalDate) {
		setFolio(folio);
		setProducts(products);
		setArrivalDate(arrivalDate);
	}

	// Getters y Setters
	public List<Furniture> getProducts() {
		return products;
	}

	public void setProducts(List<Furniture> products) {
		this.products = products;
	}

	public int getFolio() {
		return folio;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
}
