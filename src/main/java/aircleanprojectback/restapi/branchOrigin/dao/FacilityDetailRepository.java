package aircleanprojectback.restapi.branchOrigin.dao;

import aircleanprojectback.restapi.branch.entity.FacilityDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacilityDetailRepository extends JpaRepository<FacilityDetail, Integer> {


    List<FacilityDetail> findAllByBranchBranchCode(String branchCode);

    List<FacilityDetail> findAllByBranchBranchCodeIn(List<String> branchCodeList);
    @Query("SELECT f FROM FacilityDetail f WHERE f.branch.branchCode = :branchCode AND f.facilityStatus IN ('H', 'W', 'F')")
    List<FacilityDetail> findAllByBranchBranchCodeAndStatus(@Param("branchCode") String branchCode);
}
