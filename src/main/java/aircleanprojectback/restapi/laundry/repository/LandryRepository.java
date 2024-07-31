package aircleanprojectback.restapi.laundry.repository;

import aircleanprojectback.restapi.laundry.entity.WaterTank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LandryRepository extends JpaRepository<WaterTank, Long> {



}
