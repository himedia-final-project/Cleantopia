package aircleanprojectback.restapi.stock.repository;

import aircleanprojectback.restapi.stock.entity.LaundrySupplyManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaundrySupplyManagementRepository extends JpaRepository<LaundrySupplyManagement, String> {

    List<LaundrySupplyManagement> findByHeadquartersCode(String headquartersCode);
}
