package Inventory;

import java.util.ArrayList;
import java.util.Date;

public class PackingList {

	private int folio;
	private ArrayList<Furniture> products;
	private Date arrivalDate;

	public PackingList(int folio, ArrayList<Furniture> products, Date arrivalDate) {
		this.folio = folio;
		this.products = products;
		this.arrivalDate = arrivalDate;
	}

	public ArrayList<Furniture> getProducts() {
		return products;
	}

	public int getFolio() {
		return folio;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

}
