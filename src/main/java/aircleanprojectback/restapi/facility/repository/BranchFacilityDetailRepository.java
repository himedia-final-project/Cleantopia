//package aircleanprojectback.restapi.facility.repository;
//
//import aircleanprojectback.restapi.branch.entity.FacilityDetail;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface BranchFacilityDetailRepository extends JpaRepository<FacilityDetail, Integer> {
//
//
//    @Query("SELECT fd FROM FacilityDetail fd WHERE fd.branch.branchCode IN :branchCodesListEntity")
//    List<FacilityDetail> findByBranchCodes(List<String> branchCodesListEntity);
//}
