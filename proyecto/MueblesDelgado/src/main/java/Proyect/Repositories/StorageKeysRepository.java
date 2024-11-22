package Proyect.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import Proyect.StoreKeeper.StorageKeys;

public interface StorageKeysRepository extends JpaRepository<StorageKeys, Integer> {
    List<StorageKeys> findByPlatformIsNull(); // Claves disponibles (sin plataformas)
    Optional<StorageKeys> findByPlatformId(int platformId); // Buscar clave por ID de plataforma
}
