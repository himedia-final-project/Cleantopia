package aircleanprojectback.restapi.stock.repository;

import aircleanprojectback.restapi.stock.entity.LaundryPartAndManagement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LaundryPartManagementRepository extends JpaRepository<LaundryPartAndManagement, String> {
    List<LaundryPartAndManagement> findByHeadquartersCode(String headquartersCode);
}
