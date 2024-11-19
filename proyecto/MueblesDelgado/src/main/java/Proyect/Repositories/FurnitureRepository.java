package Proyect.Repositories;

import Proyect.Inventory.Furniture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FurnitureRepository extends JpaRepository<Furniture, Integer> {
    Furniture findByFurnitureId(int furnitureId);
}
