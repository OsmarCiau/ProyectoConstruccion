package Proyect.Repositories;


import Proyect.Admin.Authentication.Administrator;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
    Optional<Administrator> findByName(String name);
    boolean existsByName(String name);
}
