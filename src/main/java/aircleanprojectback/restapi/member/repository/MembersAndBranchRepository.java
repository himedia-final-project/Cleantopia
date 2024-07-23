package aircleanprojectback.restapi.member.repository;

import aircleanprojectback.restapi.member.entity.MembersAndBranch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MembersAndBranchRepository extends JpaRepository<MembersAndBranch,String> {

    @Query(value = "select a.*," +
            "b.branch_open_date, b.branch_address,b.branch_code,b.branch_image,b.branch_name,b.branch_phone,b.branch_region " +
            "from tbl_branch b join tbl_members a on a.member_id = b.member_id where a.member_role = ?1 " +
            "and a.member_status = ?2",nativeQuery = true)
    Page<MembersAndBranch> findByMemberRoleAndMemberStatus(String b, String y, Pageable paging);
}
