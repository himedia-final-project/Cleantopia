package aircleanprojectback.restapi.car.repository;

import aircleanprojectback.restapi.car.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, String> {
}
