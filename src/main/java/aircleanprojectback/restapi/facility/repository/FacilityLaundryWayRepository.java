package aircleanprojectback.restapi.facility.repository;

import aircleanprojectback.restapi.laundry.dto.LaundryWayDTO;
import aircleanprojectback.restapi.laundry.entity.LaundryWay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacilityLaundryWayRepository extends JpaRepository<LaundryWay, Integer> {


    List<LaundryWay> findAllByLaundryLaundryCodeIn(List<Integer> laundryCodes);
}
