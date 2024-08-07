package aircleanprojectback.restapi.branchOrigin.dao;

import aircleanprojectback.restapi.branch.entity.FacilityDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacilityDetailRepository extends JpaRepository<FacilityDetail, Integer> {


    List<FacilityDetail> findAllByBranchBranchCode(String branchCode);

    List<FacilityDetail> findAllByBranchBranchCodeIn(List<String> branchCodeList);
}
