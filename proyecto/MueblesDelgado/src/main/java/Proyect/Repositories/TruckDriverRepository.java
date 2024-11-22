package Proyect.Repositories;

import Proyect.Logistics.TruckDriver;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TruckDriverRepository extends JpaRepository<TruckDriver, Long> {
    Optional<TruckDriver> findByName(String name); // El Optional es un contenedor que puede o no contener un valor no nulo. 
}