package Proyect.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import Proyect.Inventory.Furniture;

public interface FurnitureRepository extends JpaRepository<Furniture, Integer> {
    // agregar métodos de consulta personalizados aquí si es necesario
}

