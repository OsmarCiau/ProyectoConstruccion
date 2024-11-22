package Proyect.Inventory;

import Proyect.Validations.ValidationUtils;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;

@Entity
public class PackingList {

	@Id
	private int folio = 0;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private ArrayList<Furniture> products = new ArrayList<>();

	@Temporal(TemporalType.DATE)
	private Date arrivalDate = new Date();

	private int orderID = 0;

	public PackingList(int p_folio, int p_orderID, ArrayList<Furniture> p_products, Date p_arrivalDate) {
		setFolio(p_folio);
		setOrderID(p_orderID);
		setProducts(p_products);
		setArrivalDate(p_arrivalDate);
	}

	public PackingList() {}

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
		ValidationUtils.validateGreaterThanZero(p_folio, "Folio");
		this.folio = p_folio;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int p_orderID) {
		ValidationUtils.validateGreaterThanZero(p_orderID, "Order ID");
		this.orderID = p_orderID;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date p_arrivalDate) {
		ValidationUtils.validateNonNull(p_arrivalDate, "Date");
		this.arrivalDate = p_arrivalDate;
	}
}