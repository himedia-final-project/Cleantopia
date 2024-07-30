package aircleanprojectback.restapi.branch.repository;

//import aircleanprojectback.restapi.branch.entity.BranchFacility;
import aircleanprojectback.restapi.member.dto.MemberDTO;
import aircleanprojectback.restapi.member.entity.Branch;
import aircleanprojectback.restapi.member.entity.Members;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BranchRepository extends JpaRepository<Branch, String> {

    @Query(value = "SELECT branch_name FROM tbl_branch WHERE branch_region = :branch_region", nativeQuery = true)
    List<String> findBranchNames(@Param("branch_region") String locationName);

    @Query(value = "SELECT branch_name From tbl_branch", nativeQuery = true)
    List<String> findAllBranchNames();


    @Query(value = "SELECT branch_region, COUNT(*) FROM tbl_branch GROUP BY branch_region", nativeQuery = true)
    List<Object[]> selectMapCounts();

    @Query(value = "SELECT * FROM tbl_branch WHERE branch_code = :branchCode", nativeQuery = true)
    Optional<Branch> findByBranchCode(String branchCode);

    List<Branch> findAllByMemberId(String memberId);


//    버튼눌렀을때

    @Query(value = "SELECT * FROM tbl_branch WHERE branch_name = :branchName", nativeQuery = true)
    List<Branch> findAllBranchName(String branchName);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tbl_branch WHERE branch_name = :branchName", nativeQuery = true)
    void deleteByBranchName(@Param("branchName") String branchName);



    @Query(value = "SELECT m FROM Members m WHERE m.memberId = :member_id")
    List<Members> findMembersByMemberIds(@Param("member_id") String memberId);


//    @Query("SELECT b FROM BranchFacility b WHERE b.branchCode = :branchCode")
//    List<BranchFacility> selectFacility(@Param("branchCode") String branchCode);


//    @Query("SELECT b FROM BranchFacility b WHERE b.branchCode = :branchCode")
//    List<BranchFacility> selectFacility(@Param("branchCode") String branchCode);



    @Query("SELECT m FROM Members m WHERE m.memberRole = 'b' AND m.branchOwnerShip = 'N'")
    List<Members> findMemberNameAndId();

}
