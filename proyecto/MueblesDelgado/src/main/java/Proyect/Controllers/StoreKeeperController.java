package Proyect.Controllers;

import Proyect.Repositories.StorageKeysRepository;
import Proyect.StoreKeeper.StoreKeeper;
import Proyect.StoreKeeper.Platform;
import Proyect.StoreKeeper.StorageKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/storekeeper")
public class StoreKeeperController {

    
    private final StoreKeeper storeKeeperService;

    @Autowired
    public StoreKeeperController(StoreKeeper storeKeeper) {
        this.storeKeeperService = storeKeeper;
    }

    // Obtener todos los espacios disponibles
    @GetMapping("/available-spaces")
    public ResponseEntity<List<StorageKeys>> getAvailableSpaces() {
        List<StorageKeys> availableSpaces = storeKeeperService.getAvailableSpace();
        return ResponseEntity.ok(availableSpaces);
    }

    // Colocar una plataforma en un espacio
    @PostMapping("/place-platform")
    public ResponseEntity<String> placePlatform(@RequestBody Platform platform) {
        try {
            storeKeeperService.placePlatformInCell(platform);
            return ResponseEntity.ok("Plataforma colocada exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    // Retirar una plataforma
    @PostMapping("/retire-platform")
    public ResponseEntity<String> retirePlatform(@RequestBody Platform platform) {
        try {
            storeKeeperService.retirePlatformFromCell(platform);
            return ResponseEntity.ok("Plataforma retirada exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    // Buscar una plataforma por ID
    @GetMapping("/find-platform/{platformId}")
    public ResponseEntity<StorageKeys> findPlatform(@PathVariable int platformId) {
        try {
            StorageKeys location = storeKeeperService.findPlatform(platformId);
            return ResponseEntity.ok(location);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

