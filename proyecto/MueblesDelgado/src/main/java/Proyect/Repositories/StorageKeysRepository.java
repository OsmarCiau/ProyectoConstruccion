package Proyect.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import Proyect.StoreKeeper.StorageKeys;


@Repository
public interface StorageKeysRepository extends JpaRepository<StorageKeys, Integer> {
    List<StorageKeys> findByPlatformIsNull(); // Claves disponibles (sin plataformas)
    // Cambiar a buscar por la propiedad 'platformId' de Platform
    Optional<StorageKeys> findByPlatform_platformId(int platformId);
    
}
