package Inventory;

import java.util.ArrayList;
import java.util.Date;

public class PackingList {

	private int folio;
	private ArrayList<Furniture> products;
	private Date arrivalDate;

	public PackingList(int folio, ArrayList<Furniture> products, Date arrivalDate) {
		setFolio(folio);
		setProducts(products);
		setArrivalDate(arrivalDate);
	}
	

	public ArrayList<Furniture> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Furniture> products) {
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
