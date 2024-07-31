package aircleanprojectback.restapi.water.dao;

import aircleanprojectback.restapi.water.entity.WaterCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WaterRepository extends JpaRepository<WaterCondition,String> {

    @Modifying
    @Query("UPDATE WaterTank w SET w.waterCurCapacity = :waterCurCapacity WHERE w.branchCode = :branchCode")
    int updateWaterCurCapacity(@Param("waterCurCapacity") int waterCurCapacity, @Param("branchCode") String branchCode);

}
