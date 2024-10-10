package Inventory;

import java.util.ArrayList;
import java.util.Date;

public class PackingList {

    private int folio;
	private ArrayList<Furniture> products;
	private Date arrivalDate;

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
