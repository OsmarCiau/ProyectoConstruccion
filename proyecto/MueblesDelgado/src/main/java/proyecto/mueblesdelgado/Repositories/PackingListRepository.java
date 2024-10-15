package proyecto.mueblesdelgado.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import proyecto.mueblesdelgado.Inventory.Furniture;
import proyecto.mueblesdelgado.Inventory.PackingList;

public interface PackingListRepository extends JpaRepository<PackingList, Integer> {
    // agregar métodos de consulta personalizados aquí si es necesario
}


