package aircleanprojectback.restapi.water.dao;

import aircleanprojectback.restapi.water.entity.WaterSupply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WaterSupplyRepository extends JpaRepository<WaterSupply, Long> {


    @Query(value = "SELECT * FROM tbl_water_supply WHERE water_tank_no = :waterTankNo", nativeQuery = true)
    List<WaterSupply> findByWaterTankNo(@Param("waterTankNo") String waterTankNo);

}

