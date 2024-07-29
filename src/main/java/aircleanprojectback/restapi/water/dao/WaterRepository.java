package aircleanprojectback.restapi.water.dao;

import aircleanprojectback.restapi.water.entity.WaterCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaterRepository extends JpaRepository<WaterCondition,String> {
}
