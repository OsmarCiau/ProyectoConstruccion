package Proyect.Repositories;

import Proyect.Logistics.TruckDriver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TruckDriverRepository extends JpaRepository<TruckDriver, Long> {
    TruckDriver findByName(String name);
}
