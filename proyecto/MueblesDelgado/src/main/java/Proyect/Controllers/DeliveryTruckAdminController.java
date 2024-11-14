package Proyect.Controllers;

import Proyect.Logistics.DeliveryTruck;
import Proyect.Logistics.DeliveryTruckAdmin;
import Proyect.Logistics.TruckAssignment;
import Proyect.Logistics.TruckDriver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryTruckAdminController {

    @Autowired
    private DeliveryTruckAdmin deliveryTruckAdminService;

    @PostMapping("/truck")
    public ResponseEntity<Void> registerDeliveryTruck(@RequestBody DeliveryTruck p_deliveryTruck) {
        deliveryTruckAdminService.registerDeliveryTruck(p_deliveryTruck);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/driver")
    public ResponseEntity<Void> registerTruckDriver(@RequestBody TruckDriver p_truckDriver) {
        deliveryTruckAdminService.registerTruckDriver(p_truckDriver);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/trucks")
    public ResponseEntity<List<DeliveryTruck>> getAvailableTrucks() {
        List<DeliveryTruck> trucks = deliveryTruckAdminService.getAvailableTrucks();
        return new ResponseEntity<>(trucks, HttpStatus.OK);
    }

    @GetMapping("/drivers")
    public ResponseEntity<List<TruckDriver>> getAvailableDrivers() {
        List<TruckDriver> drivers = deliveryTruckAdminService.getAvailableDrivers();
        return new ResponseEntity<>(drivers, HttpStatus.OK);
    }

    @PostMapping("/assign")
    public ResponseEntity<Void> assignDriverToTruck(@RequestParam String p_trackingNumber, @RequestParam String p_name) {
        deliveryTruckAdminService.assignDriverToTruck(p_trackingNumber, p_name);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/assignments")
    public ResponseEntity<List<TruckAssignment>> getAssignments() {
        List<TruckAssignment> assignments = deliveryTruckAdminService.getTruckAssignments();
        return new ResponseEntity<>(assignments, HttpStatus.OK);
    }
}
