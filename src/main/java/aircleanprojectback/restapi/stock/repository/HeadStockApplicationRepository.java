package aircleanprojectback.restapi.stock.repository;

import aircleanprojectback.restapi.stock.entity.HeadStockApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeadStockApplicationRepository extends JpaRepository<HeadStockApplication, Integer> {


}
