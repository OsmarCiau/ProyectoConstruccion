package Proyect.Controllers;

import Proyect.Logistics.DeliveryTruck;
import Proyect.Logistics.DeliveryTruckAdmin;
import Proyect.Logistics.TruckAssignment;
import Proyect.Logistics.TruckDriver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryTruckAdminController {

    @Autowired
    private DeliveryTruckAdmin deliveryTruckAdminService;

    @PostMapping("/truck")
    public void registerDeliveryTruck(@RequestParam int trackingNumber, @RequestParam double capacity, @RequestParam double mileage) {
        deliveryTruckAdminService.registerDeliveryTruck(trackingNumber, capacity, mileage);
    }

    @PostMapping("/driver")
    public void registerTruckDriver(@RequestParam String name, @RequestParam int licenseNumber) {
        deliveryTruckAdminService.registerTruckDriver(name, licenseNumber);
    }

    @GetMapping("/trucks")
    public List<DeliveryTruck> getAvailableTrucks() {
        return deliveryTruckAdminService.getAvailableTrucks();
    }

    @GetMapping("/drivers")
    public List<TruckDriver> getAvailableDrivers() {
        return deliveryTruckAdminService.getAvailableDrivers();
    }

    @PostMapping("/assign")
    public void assignDriverToTruck(@RequestParam int trackingNumber, @RequestParam String name) {
        deliveryTruckAdminService.assignDriverToTruck(trackingNumber, name);
    }

    @GetMapping("/assignments")
    public List<TruckAssignment> getAssignments() {
        return deliveryTruckAdminService.getTruckAssignments();
    }
}
