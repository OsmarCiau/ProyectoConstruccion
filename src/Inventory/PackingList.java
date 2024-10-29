package Inventory;

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

	private static final PackingListValidationUtils packingListValidator = new PackingListValidationUtils();

	public void setProducts(ArrayList<Furniture> p_products) {
		boolean isPackingListValid = packingListValidator.isPackingListEmpty(p_products);

		if (isPackingListValid) {
			a_products = p_products;
		}
	}

	public int getFolio() {
		return a_folio;
	}

	public void setFolio(int p_folio) {
		boolean isFolioValid = packingListValidator.validateFolio(p_folio);

		if (isFolioValid) {
			a_folio = p_folio;
		}
	}

	public Date getArrivalDate() {
		return a_arrivalDate;
	}

	public void setArrivalDate(Date p_arrivalDate) {
		boolean isArrivalDateValid = packingListValidator.validateArrivalDate(p_arrivalDate);

		if (isArrivalDateValid) {
			a_arrivalDate = p_arrivalDate;
		}
	}
}
