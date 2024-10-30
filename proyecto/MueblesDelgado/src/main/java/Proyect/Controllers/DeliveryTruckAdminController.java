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
    public void registerDeliveryTruck(@RequestBody DeliveryTruck p_deliveryTruck) {
        deliveryTruckAdminService.registerDeliveryTruck(p_deliveryTruck);
    }

    @PostMapping("/driver")
    public void registerTruckDriver(@RequestBody TruckDriver p_truckDriver) {
        deliveryTruckAdminService.registerTruckDriver(p_truckDriver);
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
    public void assignDriverToTruck(@RequestParam int p_trackingNumber, @RequestParam String p_name) {
        deliveryTruckAdminService.assignDriverToTruck(p_trackingNumber, p_name);
    }

    @GetMapping("/assignments")
    public List<TruckAssignment> getAssignments() {
        return deliveryTruckAdminService.getTruckAssignments();
    }
}
