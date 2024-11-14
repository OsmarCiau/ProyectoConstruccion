package Proyect.Repositories;

import Proyect.Logistics.TruckAssignment;
import Proyect.Logistics.DeliveryTruck;
import Proyect.Logistics.TruckDriver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TruckAssignmentRepository extends JpaRepository<TruckAssignment, Integer> {
    boolean existsByTruckAndDriver(DeliveryTruck deliveryTruck, TruckDriver truckDriver);
}
