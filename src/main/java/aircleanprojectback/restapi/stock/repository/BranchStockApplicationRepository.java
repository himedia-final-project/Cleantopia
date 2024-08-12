package aircleanprojectback.restapi.stock.repository;

import aircleanprojectback.restapi.stock.entity.BranchStockApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchStockApplicationRepository extends JpaRepository<BranchStockApplication, Integer> {
    List<BranchStockApplication> findAllByBranchCode(String branchCode);

    BranchStockApplication findByBApplicationCode(int i);
}
