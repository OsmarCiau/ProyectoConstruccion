package Proyect.Inventory;

import Proyect.Validations.ValidationUtils;

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

	public void setProducts(ArrayList<Furniture> p_products) {
		ValidationUtils.validatesArrayList(p_products, "Products");
		this.products = p_products;
	}

	public int getFolio() {
		return folio;
	}

	public void setFolio(int p_folio) {
		ValidationUtils.validatePositiveNumber(p_folio, "Folio");
		this.folio = p_folio;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date p_arrivalDate) {
		ValidationUtils.validateNonNull(p_arrivalDate, "Date");
		this.arrivalDate = p_arrivalDate;
	}
}