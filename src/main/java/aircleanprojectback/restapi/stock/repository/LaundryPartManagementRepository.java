package aircleanprojectback.restapi.stock.repository;

import aircleanprojectback.restapi.stock.entity.LaundryPartManagement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaundryPartManagementRepository extends JpaRepository<LaundryPartManagement, String> {
}
