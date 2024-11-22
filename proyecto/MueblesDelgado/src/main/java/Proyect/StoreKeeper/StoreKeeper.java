package Proyect.StoreKeeper;

import Proyect.Repositories.StorageKeysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StoreKeeper {

    private final StorageKeysRepository storageKeyRepository;

    @Autowired
    public StoreKeeper(StorageKeysRepository p_storageKeyRepository) {
        this.storageKeyRepository = p_storageKeyRepository;
    }

    public List<StorageKeys> getAvailableSpace() {
        return storageKeyRepository.findByPlatformIsNull();
    }

    public void placePlatformInCell(Platform platform) {
        List<StorageKeys> availableKeys = getAvailableSpace();
        StorageKeys fittingKey = null;

        for (StorageKeys key : availableKeys) {
            if (platformFitsInCell(platform, key.getCell())) {
                fittingKey = key;
                break;
            }
        }

        if (fittingKey != null) {
            fittingKey.setPlatform(platform);
            platform.setLocationInRack(fittingKey);
            storageKeyRepository.save(fittingKey);
            System.out.println("Plataforma " + platform.getPlatformId() + " colocada en Rack " 
                    + fittingKey.getRack().getRackNumber() + " Celda " + fittingKey.getCell().getNumber());
        } else {
            throw new IllegalStateException("No hay espacio disponible para la plataforma.");
        }
    }

    public void retirePlatformFromCell(Platform platform) {
        StorageKeys platformLocation = platform.getLocationInRack();
        if (platformLocation != null) {
            platformLocation.setPlatform(null);
            storageKeyRepository.save(platformLocation);
            platform.setLocationInRack(null);
            System.out.println("La plataforma " + platform.getPlatformId() + " se retiró de: Rack: "
                    + platformLocation.getRack().getRackNumber() + ", Celda: " + platformLocation.getCell().getNumber());
        } else {
            throw new IllegalStateException("La plataforma no está asociada a ninguna ubicación.");
        }
    }

    public StorageKeys findPlatform(int platformId) {
        return storageKeyRepository.findByPlatformId(platformId)
                .orElseThrow(() -> new IllegalStateException("Plataforma con ID " + platformId + " no encontrada."));
    }

    private boolean platformFitsInCell(Platform platform, Cell cell) {
        if (platform.getLength() <= cell.getLength()) {
            return true;
        }
        if (platform.getWidth() <= cell.getLength() && platform.getLength() <= cell.getWidth()) {
            platform.getDimension().swapLengthAndWidth();
            return true;
        }
        return false;
    }
}
