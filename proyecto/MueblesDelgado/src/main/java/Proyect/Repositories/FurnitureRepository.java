package Proyect.Repositories;

import Proyect.Inventory.Furniture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FurnitureRepository extends JpaRepository<Furniture, Integer> {
    Furniture findByFurnitureId(int furnitureId);

    // Aquí usamos 'order.orderID' para referirnos al campo 'orderID' de la entidad 'Order'
    List<Furniture> findByOrder_orderID(int orderID);
}
