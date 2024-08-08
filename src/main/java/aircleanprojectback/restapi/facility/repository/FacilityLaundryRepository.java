package aircleanprojectback.restapi.facility.repository;

import aircleanprojectback.restapi.laundry.dto.LaundryDTO;
import aircleanprojectback.restapi.laundry.entity.Laundry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacilityLaundryRepository extends JpaRepository<Laundry, Integer> {


    List<Laundry> findAllByBranchCode(String branchCode);
}
