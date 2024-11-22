package Proyect.Repositories;

import Proyect.StoreKeeper.IdPairOrderFurniture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdPairOrderFurnitureRepository extends JpaRepository<IdPairOrderFurniture,Integer > {

}
