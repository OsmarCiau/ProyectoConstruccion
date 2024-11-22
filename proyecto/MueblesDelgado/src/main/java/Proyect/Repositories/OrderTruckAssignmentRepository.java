package Proyect.Repositories;

import Proyect.Logistics.OrderTruckAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderTruckAssignmentRepository extends JpaRepository<OrderTruckAssignment, Integer> {
}
