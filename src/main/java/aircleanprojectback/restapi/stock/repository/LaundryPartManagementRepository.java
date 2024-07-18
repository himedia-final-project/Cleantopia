package aircleanprojectback.restapi.stock.repository;

import aircleanprojectback.restapi.stock.entity.LaundryPartAndManagement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaundryPartManagementRepository extends JpaRepository<LaundryPartAndManagement, String> {
}
