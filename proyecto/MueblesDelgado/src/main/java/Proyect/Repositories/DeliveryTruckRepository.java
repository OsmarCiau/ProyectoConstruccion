package Proyect.Repositories;

import Proyect.Logistics.DeliveryTruck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryTruckRepository extends JpaRepository<DeliveryTruck, Integer> {
    Optional<DeliveryTruck> findByTrackingNumber(String trackingNumber);
}
