package Proyect.Inventory;

import java.util.ArrayList;
import java.util.Date;

public class PackingList {
	private int a_folio = 0;
	private ArrayList<Furniture> a_products = new ArrayList<>();
	private Date a_arrivalDate = new Date();

	public PackingList(int p_folio, ArrayList<Furniture> p_products, Date p_arrivalDate) {
		setFolio(p_folio);
		setProducts(p_products);
		setArrivalDate(p_arrivalDate);
	}

	public ArrayList<Furniture> getProducts() {
		return a_products;
	}

	public void setProducts(ArrayList<Furniture> p_products) {
		PackingListValidationUtils.isPackingListNotEmpty(p_products);
		a_products = p_products;
	}

	public int getFolio() {
		return a_folio;
	}

	public void setFolio(int p_folio) {
		PackingListValidationUtils.validateFolio(p_folio);
		this.a_folio = p_folio;
	}

	public Date getArrivalDate() {
		return a_arrivalDate;
	}

	public void setArrivalDate(Date p_arrivalDate) {
		PackingListValidationUtils.validateArrivalDate(p_arrivalDate);
		this.a_arrivalDate = p_arrivalDate;
	}
}