package proyecto.mueblesdelgado.Inventory;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class PackingList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Autogenerar el ID
	private int folio;

	@OneToMany(mappedBy = "packingList", cascade = CascadeType.ALL, orphanRemoval = true) // Relación bidireccional
	private List<Furniture> products;

	@Temporal(TemporalType.DATE)
	private Date arrivalDate;

	public PackingList() {}

	public PackingList(List<Furniture> products, Date arrivalDate) {
		this.products = products;
		this.arrivalDate = arrivalDate;
	}

	// Getters y Setters
	public int getFolio() {
		return folio;
	}

	public void setFolio(int folio) {
		this.folio = folio;
	}

	public List<Furniture> getProducts() {
		return products;
	}

	public void setProducts(List<Furniture> products) {
		this.products = products;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
}
