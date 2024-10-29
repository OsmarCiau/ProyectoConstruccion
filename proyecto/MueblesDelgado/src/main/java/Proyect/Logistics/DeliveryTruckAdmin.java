package Proyect.Logistics;

import java.util.List;

import Proyect.Repositories.DeliveryTruckRepository;
import Proyect.Repositories.TruckAssignmentRepository;
import Proyect.Repositories.TruckDriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryTruckAdmin{

    @Autowired
    private DeliveryTruckRepository deliveryTruckRepository;

    @Autowired
    private TruckDriverRepository truckDriverRepository;

    @Autowired
    private TruckAssignmentRepository truckAssignmentRepository;

    //private HashMap<DeliveryTruck, TruckDriver> deliveryTruckTruckDriverMap = new HashMap<>(); YA NO ES NECESARIO

    public void registerDeliveryTruck(int p_trackingNumber, double p_capacity, double p_mileage) {
        DeliveryTruck deliveryTruck = new DeliveryTruck(p_trackingNumber, p_capacity, p_mileage);
        deliveryTruckRepository.save(deliveryTruck);
    }

    public void registerTruckDriver(String p_name, int p_licenseNumber) {
        TruckDriver truckDriver = new TruckDriver(p_name, p_licenseNumber);
        truckDriverRepository.save(truckDriver);
    }

    public List<DeliveryTruck> getAvailableTrucks() {
        return deliveryTruckRepository.findAll();
    }

    public List<TruckDriver> getAvailableDrivers() {
        return truckDriverRepository.findAll();
    }


    /* CAMBIO PARA ALMACENAR EN UNA BD, Y NO EN UN MAP */
    public void assignDriverToTruck(int p_trackingNumber, String p_name) {
        DeliveryTruck selectedTruck = deliveryTruckRepository.findByTrackingNumber(p_trackingNumber);
        TruckDriver selectedDriver = truckDriverRepository.findByName(p_name);

        if (selectedTruck != null && selectedDriver != null) {
            truckAssignmentRepository.save(new TruckAssignment(selectedTruck, selectedDriver));
        }
    }

    public List<TruckAssignment> getTruckAssignments() {
        return truckAssignmentRepository.findAll();
    }
}
