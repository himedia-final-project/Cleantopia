package aircleanprojectback.restapi.branch.repository;

import aircleanprojectback.restapi.member.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {

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
    
}
