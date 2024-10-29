package Proyect.Repositories;

import Proyect.Logistics.TruckAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TruckAssignmentRepository extends JpaRepository<TruckAssignment, Integer> {
    // Aquí puedes agregar métodos adicionales según sea necesario
}
