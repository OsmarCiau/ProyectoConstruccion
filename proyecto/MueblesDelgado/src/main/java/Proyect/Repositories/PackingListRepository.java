package Proyect.Repositories;

import Proyect.Inventory.PackingList;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PackingListRepository extends JpaRepository<PackingList, Integer> {
    PackingList findByFolio(int packingListFolio);
}
