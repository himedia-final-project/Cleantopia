package aircleanprojectback.restapi.car.repository;

import aircleanprojectback.restapi.car.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, String> {


    List<Driver> findAllByAssignCar(String n);
    Driver findByMemberId(String memberId);

    List<Driver> findAllByDriverRegionAndAssignCar(String branchRegion, String y);
}
