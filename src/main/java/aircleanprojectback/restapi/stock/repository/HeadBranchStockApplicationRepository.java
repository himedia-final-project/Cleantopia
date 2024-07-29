package aircleanprojectback.restapi.stock.repository;

import aircleanprojectback.restapi.stock.entity.BranchStockApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeadBranchStockApplicationRepository extends JpaRepository<BranchStockApplication, Integer> {
    List<BranchStockApplication> findByBranchCodeStartingWith(String br);
}
