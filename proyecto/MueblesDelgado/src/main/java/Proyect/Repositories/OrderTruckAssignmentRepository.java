package Proyect.Repositories;

import Proyect.Logistics.RouteTruckAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderTruckAssignmentRepository extends JpaRepository<RouteTruckAssignment, Integer> {
}
