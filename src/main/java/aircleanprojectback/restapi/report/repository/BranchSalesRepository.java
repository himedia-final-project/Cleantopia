package aircleanprojectback.restapi.report.repository;


import aircleanprojectback.restapi.report.entity.BranchSales;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BranchSalesRepository extends JpaRepository<BranchSales, Integer> {

//    @Query(value = "SELECT m.member_name, b.branch_name FROM tbl_members m JOIN tbl_branch b ON m.member_id = b.member_id", nativeQuery = true)
//    List<BranchAndMembersDTO> findBranchMember();

}
