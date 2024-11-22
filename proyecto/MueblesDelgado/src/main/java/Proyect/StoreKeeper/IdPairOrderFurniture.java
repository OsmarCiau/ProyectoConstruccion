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


}
