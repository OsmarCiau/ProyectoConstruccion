package Proyect.Repositories;

import Proyect.Logistics.DeliveryTruck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryTruckRepository extends JpaRepository<DeliveryTruck, Integer> {
    DeliveryTruck findByTrackingNumber(int trackingNumber);

}
