package aircleanprojectback.restapi.stock.repository;

import aircleanprojectback.restapi.stock.entity.LaundryPartManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OnlyLaundryPartManagementRepository extends JpaRepository<LaundryPartManagement, String> {
    List<LaundryPartManagement> findByLaundryPartCode(String laundryPartCode);
}
