//package aircleanprojectback.restapi.facility.repository;
//
//import aircleanprojectback.restapi.member.entity.Branch;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface FindBranchInfoRepository extends JpaRepository<Branch, String> {
//
//    @Query("SELECT m.branchCode FROM BranchRefMember m WHERE m.memberId= :sub")
//    List<String> findBranchCodesByMemberId(@Param("sub") String sub);
//}
