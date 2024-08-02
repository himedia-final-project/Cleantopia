package aircleanprojectback.restapi.car.repository;

import aircleanprojectback.restapi.car.entity.CarAndDriver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarAndDriverRepostiory extends JpaRepository<CarAndDriver ,String> {
}
