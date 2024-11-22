package Proyect.Logistics;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;  // Agregar transacciones

import Proyect.Repositories.DeliveryTruckRepository;
import Proyect.Repositories.TruckAssignmentRepository;
import Proyect.Repositories.TruckDriverRepository;

@Service
public class DeliveryTruckAdmin {

    private DeliveryTruckRepository deliveryTruckRepository;
    private TruckDriverRepository truckDriverRepository;
    private TruckAssignmentRepository truckAssignmentRepository;

    @Autowired
    public DeliveryTruckAdmin(
        DeliveryTruckRepository p_deliveryTruckRepository,   
        TruckDriverRepository p_truckDriverRepository, 
        TruckAssignmentRepository p_truckAssignmentRepository) {
        this.deliveryTruckRepository = p_deliveryTruckRepository;
        this.truckDriverRepository = p_truckDriverRepository;
        this.truckAssignmentRepository = p_truckAssignmentRepository;
    }

    @Transactional  // Asegurarse de que los métodos de persistencia se realicen dentro de una transacción
    public void registerDeliveryTruck(DeliveryTruck p_deliveryTruck) {
        deliveryTruckRepository.save(p_deliveryTruck);
    }

    @Transactional
    public void registerTruckDriver(TruckDriver p_truckDriver) {
        truckDriverRepository.save(p_truckDriver);
    }

    public List<DeliveryTruck> getAvailableTrucks() {
        // Filtrar camiones disponibles si es necesario
        return deliveryTruckRepository.findAll();  // Puedes agregar filtrado de disponibilidad aquí
    }

    public List<TruckDriver> getAvailableDrivers() {
        // Filtrar conductores disponibles si es necesario
        return truckDriverRepository.findAll();  // Puedes agregar filtrado de disponibilidad aquí
    }

    @Transactional  // Asegura la asignación de conductor a camión se realice dentro de una transacción
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
