package aircleanprojectback.restapi.water.dao;

import aircleanprojectback.restapi.water.entity.WaterSupply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaterSupplyRepository extends JpaRepository<WaterSupply, Long> {





}
