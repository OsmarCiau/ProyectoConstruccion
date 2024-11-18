package Proyect.Controllers;

import Proyect.Inventory.InventoryAdmin;
import Proyect.Inventory.PackingList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryAdminController {

    @Autowired
    private InventoryAdmin inventoryAdminService;

    @PostMapping("/add_furniture")
    public ResponseEntity<Void> addFurniture(@RequestBody PackingList packingList) {
        inventoryAdminService.addFurniture(packingList);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/remove_furniture")
    public ResponseEntity<Void> removeFurniture(@RequestBody PackingList packingList) {
        inventoryAdminService.removeFurniture(packingList);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update_furniture")
    public ResponseEntity<Void> updateFurniture(@RequestBody PackingList packingList) {
        inventoryAdminService.updateFurniture(packingList);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
