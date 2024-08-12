package aircleanprojectback.restapi.stock.repository;

import aircleanprojectback.restapi.stock.entity.LaundrySupplyManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OnlyLaundrySupplyManagementRepository extends JpaRepository<LaundrySupplyManagement, String> {
    List<LaundrySupplyManagement> findByLaundrySupplyCode(String laundrySupplyCode);

    LaundrySupplyManagement findByBranchCodeAndLaundrySupplyCode(String branchCode, String ls001);
}
