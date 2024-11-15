package Proyect.Logistics;

import java.util.List;

import Proyect.Repositories.DeliveryTruckRepository;
import Proyect.Repositories.TruckAssignmentRepository;
import Proyect.Repositories.TruckDriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryTruckAdmin{

    private DeliveryTruckRepository deliveryTruckRepository;
    private TruckDriverRepository truckDriverRepository;
    private TruckAssignmentRepository truckAssignmentRepository;

    @Autowired
    public DeliveryTruckAdmin(DeliveryTruckRepository p_deliveryTruckRepository, TruckDriverRepository p_truckDriverRepository, TruckAssignmentRepository p_truckAssignmentRepository) {
        this.deliveryTruckRepository = p_deliveryTruckRepository;
        this.truckDriverRepository = p_truckDriverRepository;
        this.truckAssignmentRepository = p_truckAssignmentRepository;
    }

    public void registerDeliveryTruck(DeliveryTruck p_deliveryTruck) {
        deliveryTruckRepository.save(p_deliveryTruck);
    }

    public void registerTruckDriver(TruckDriver p_truckDriver) {
        truckDriverRepository.save(p_truckDriver);
    }

    public List<DeliveryTruck> getAvailableTrucks() {
        return deliveryTruckRepository.findAll();
    }

    public List<TruckDriver> getAvailableDrivers() {
        return truckDriverRepository.findAll();
    }


    public void assignDriverToTruck(String p_trackingNumber, String p_name) {
        DeliveryTruck selectedTruck = deliveryTruckRepository.findByTrackingNumber(p_trackingNumber)
                .orElseThrow(() -> new IllegalArgumentException("Truck not found: Tracking Number " + p_trackingNumber));
        TruckDriver selectedDriver = truckDriverRepository.findByName(p_name)
                .orElseThrow(() -> new IllegalArgumentException("Driver not found: Name " + p_name));

        // Verificar si la asignación ya existe
        if (truckAssignmentRepository.existsByDeliveryTruckAndTruckDriver(selectedTruck, selectedDriver)) {
            throw new IllegalArgumentException("Assignment already exists");
        }
        truckAssignmentRepository.save(new TruckAssignment(selectedTruck, selectedDriver));
    }

    public List<TruckAssignment> getTruckAssignments() {
        return truckAssignmentRepository.findAll();
    }
}
