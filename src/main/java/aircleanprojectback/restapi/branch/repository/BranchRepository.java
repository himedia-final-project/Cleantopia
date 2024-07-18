package aircleanprojectback.restapi.branch.repository;

import aircleanprojectback.restapi.member.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {

    @Query(value = "SELECT branch_name FROM tbl_branch", nativeQuery = true)
    List<String> findAllBranchNames();
}
