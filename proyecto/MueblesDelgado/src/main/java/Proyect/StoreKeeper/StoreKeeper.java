package Proyect.StoreKeeper;

import Proyect.Repositories.StorageKeysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StoreKeeper {

    private final StorageKeysRepository storageKeyRepository; // Repositorio para interactuar con las claves

    public StoreKeeper() {
        this.storageKeyRepository = null;
    }

    @Autowired
    public StoreKeeper(StorageKeysRepository p_storageKeyRepository) {
        this.storageKeyRepository = p_storageKeyRepository;
    }

    // Obtener todos los espacios disponibles (sin plataforma asignada)
    public List<StorageKeys> getAvailableSpace() {
        return storageKeyRepository.findByPlatformIsNull();
    }

    // Colocar una plataforma en un espacio adecuado
    public void placePlatformInCell(Platform platform) {
        // Obtener espacios disponibles
        List<StorageKeys> availableKeys = getAvailableSpace();
        StorageKeys fittingKey = null;

        // Buscar una clave adecuada para la plataforma
        for (StorageKeys key : availableKeys) {
            if (platformFitsInCell(platform, key.getCell())) {
                fittingKey = key;
                break;
            }
        }

        // Verificar si se encontró un espacio
        if (fittingKey != null) {
            fittingKey.setPlatform(platform); // Asignar la plataforma a la clave
            platform.setLocationInRack(fittingKey); // Actualizar ubicación en la plataforma
            storageKeyRepository.save(fittingKey); // Guardar el cambio en la base de datos
            System.out.println("Plataforma " + platform.getPlatformId() + " colocada en Rack " 
                    + fittingKey.getRack().getRackNumber() + " Celda " + fittingKey.getCell().getNumber());
        } else {
            throw new IllegalStateException("No hay espacio disponible para la plataforma.");
        }
    }

    // Retirar una plataforma de su celda
    public void retirePlatformFromCell(Platform platform) {
        StorageKeys platformLocation = platform.getLocationInRack();
        if (platformLocation != null) {
            platformLocation.setPlatform(null); // Liberar la clave de almacenamiento
            storageKeyRepository.save(platformLocation); // Guardar el cambio en la base de datos
            platform.setLocationInRack(null); // Limpiar la referencia en la plataforma
            System.out.println("La plataforma " + platform.getPlatformId() + " se retiró de: Rack: "
                    + platformLocation.getRack().getRackNumber() + ", Celda: " + platformLocation.getCell().getNumber());
        } else {
            throw new IllegalStateException("La plataforma no está asociada a ninguna ubicación.");
        }
    }

    // Buscar una plataforma por ID
    public StorageKeys findPlatform(int platformId) {
        StorageKeys foundKey = storageKeyRepository.findByPlatform_platformId(platformId).orElse(null);
        if (foundKey == null) {
            throw new IllegalStateException("Plataforma con ID " + platformId + " no encontrada.");
        }
        return foundKey;
    }

    // Verificar si una plataforma cabe en una celda
    private boolean platformFitsInCell(Platform platform, Cell cell) {
        // Verificar dimensiones de la plataforma contra la celda
        if (platform.getLength() <= cell.getLength()) {
            return true;
        }
        if (platform.getWidth() <= cell.getLength() && platform.getLength() <= cell.getWidth()) {
            platform.getDimension().swapLengthAndWidth(); // Ajustar dimensiones si es necesario
            return true;
        }
        return false;
    }
}
