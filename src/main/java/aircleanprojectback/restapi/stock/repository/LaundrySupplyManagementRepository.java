package aircleanprojectback.restapi.stock.repository;

import aircleanprojectback.restapi.stock.entity.LaundrySupplyAndManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaundrySupplyManagementRepository extends JpaRepository<LaundrySupplyAndManagement, String> {

    List<LaundrySupplyAndManagement> findByHeadquartersCode(String headquartersCode);

    List<LaundrySupplyAndManagement> findByBranchCodeStartingWith(String prefix);

    LaundrySupplyAndManagement findByLaundrySupplyManagementCode(String laundrySupplyManagementCode);

    LaundrySupplyAndManagement findByLaundrySupply_LaundrySupplyName(String laundrySupplyName);
}