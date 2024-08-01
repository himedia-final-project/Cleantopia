package aircleanprojectback.restapi.water.dao;


import aircleanprojectback.restapi.laundry.entity.WaterTank;
import aircleanprojectback.restapi.water.entity.WaterSupply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WaterTankRepository extends JpaRepository<WaterTank, Long> {

    // 브런치 코드로 waterTankNo 조회
    @Query(value = "SELECT wt.water_tank_no FROM tbl_water_tank wt WHERE wt.branch_code = :branchCode", nativeQuery = true)
    String selectWaterTankNo(@Param("branchCode") String branchCode);


}
