package Inventory;

import java.util.ArrayList;
import java.util.Date;

public class PackingList {
	private int a_folio;
	private ArrayList<Furniture> a_products;
	private Date a_arrivalDate;

	public PackingList(int p_folio, ArrayList<Furniture> p_products, Date p_arrivalDate) {
		setFolio(p_folio);
		setProducts(p_products);
		setArrivalDate(p_arrivalDate);
	}

	public ArrayList<Furniture> getProducts() {
		return a_products;
	}

	public void setProducts(ArrayList<Furniture> p_products) {
		this.a_products = p_products;
	}

	public int getFolio() {
		return a_folio;
	}

	public void setFolio(int p_folio) {
		validateFolio(p_folio);
		this.a_folio = p_folio;
	}

	private void validateFolio(int p_folio) {
		if (p_folio <= 0) {
			throw new IllegalArgumentException("Error: folio must be a positive number.");
		}
	}

	public Date getArrivalDate() {
		return a_arrivalDate;
	}

	public void setArrivalDate(Date p_arrivalDate) {
		validateArrivalDate(p_arrivalDate);
		this.a_arrivalDate = p_arrivalDate;
	}

	private void validateArrivalDate(Date p_arrivalDate) {
		if (p_arrivalDate == null) {
			throw new IllegalArgumentException("Error: arrivalDate cannot be null.");
		}
	}
}
