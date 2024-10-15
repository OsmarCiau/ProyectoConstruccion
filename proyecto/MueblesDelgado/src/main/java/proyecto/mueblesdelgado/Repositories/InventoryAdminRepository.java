package proyecto.mueblesdelgado.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import proyecto.mueblesdelgado.Inventory.Furniture;

public interface InventoryAdminRepository extends JpaRepository<Furniture, Integer> {
    // agregar métodos de consulta personalizados aquí si es necesario
}

