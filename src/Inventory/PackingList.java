package Inventory;

import java.util.ArrayList;
import java.util.Date;

public class PackingList {
	private int folio = 0;
	private ArrayList<Furniture> products = new ArrayList<>();
	private Date arrivalDate = new Date();

	public PackingList(int p_folio, ArrayList<Furniture> p_products, Date p_arrivalDate) {
		setFolio(p_folio);
		setProducts(p_products);
		setArrivalDate(p_arrivalDate);
	}

	public ArrayList<Furniture> getProducts() {
		return products;
	}

	private static final PackingListValidationUtils packingListValidator = new PackingListValidationUtils();

	public void setProducts(ArrayList<Furniture> p_products) {
		packingListValidator.isPackingListEmpty(p_products);
		this.products = p_products;
	}

	public int getFolio() {
		return folio;
	}

	public void setFolio(int p_folio) {
		packingListValidator.validateFolio(p_folio);
		this.folio = p_folio;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date p_arrivalDate) {
		packingListValidator.validateArrivalDate(p_arrivalDate);
		this.arrivalDate = p_arrivalDate;
	}
}
