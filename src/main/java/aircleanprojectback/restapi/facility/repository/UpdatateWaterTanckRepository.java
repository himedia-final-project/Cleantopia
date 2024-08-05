package aircleanprojectback.restapi.facility.repository;

import aircleanprojectback.restapi.laundry.entity.WaterTank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UpdatateWaterTanckRepository extends JpaRepository<WaterTank, Integer> {
    WaterTank findByBranchCode(String branchCode);
}
