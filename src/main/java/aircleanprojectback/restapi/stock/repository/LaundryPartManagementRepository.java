package aircleanprojectback.restapi.stock.repository;

import aircleanprojectback.restapi.stock.entity.LaundryPartAndManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaundryPartManagementRepository extends JpaRepository<LaundryPartAndManagement, String> {
    List<LaundryPartAndManagement> findByHeadquartersCode(String headquartersCode);

    List<LaundryPartAndManagement> findByBranchCodeStartingWith(String br);

    LaundryPartAndManagement findByLaundryPartManagementCode(String laundryPartManagementCode);

    LaundryPartAndManagement findByLaundryPart_LaundryPartName(String laundryPartName);
}
