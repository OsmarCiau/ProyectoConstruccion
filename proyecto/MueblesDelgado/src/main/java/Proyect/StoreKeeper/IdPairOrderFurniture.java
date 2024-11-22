package Proyect.StoreKeeper;
import Proyect.Inventory.Furniture;
import Proyect.Inventory.PackingList;
import jakarta.persistence.*;

@Entity
public class IdPairOrderFurniture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderFurnitureId; //ID único por pareja de id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderID", referencedColumnName = "orderID")
    private Order order; //relación con order

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "furnitureID", referencedColumnName = "furnitureId")
    private Furniture furniture; //relación con furniture

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "packingListID", referencedColumnName = "folio")
    private PackingList packingList; //relación con PackingList

    //Constructor
    public IdPairOrderFurniture() {
    }

    public IdPairOrderFurniture(Order p_order, Furniture p_furniture, PackingList p_packingList) {
        this.order = p_order;
        this.furniture = p_furniture;
        this.packingList = p_packingList;
    }

    //Getters y Setters
    public Long getOrderFurnitureId() {
        return orderFurnitureId;
    }

    public void setOrderFurnitureId(Long p_orderFurnitureId) {
        this.orderFurnitureId = p_orderFurnitureId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order p_order) {
        this.order = p_order;
    }

    public Furniture getFurniture() {
        return furniture;
    }

    public void setFurniture(Furniture p_furniture) {
        this.furniture = p_furniture;
    }

    public PackingList getPackingList() {
        return packingList;
    }

    public void setPackingList(PackingList p_packingList) {
        this.packingList = p_packingList;
    }

}