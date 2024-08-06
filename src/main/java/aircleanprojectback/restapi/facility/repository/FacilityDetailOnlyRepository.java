package aircleanprojectback.restapi.facility.repository;

import aircleanprojectback.restapi.facility.entity.FacilityDetailOnly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilityDetailOnlyRepository extends JpaRepository<FacilityDetailOnly, Integer> {

    FacilityDetailOnly findByFacilityId(int facilityId);
}
