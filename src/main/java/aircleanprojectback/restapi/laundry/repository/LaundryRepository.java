package aircleanprojectback.restapi.laundry.repository;

import aircleanprojectback.restapi.laundry.entity.Laundry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LaundryRepository extends JpaRepository<Laundry, Long> {
    

    List<Laundry> findByBranchCode(String branchCode);
}
