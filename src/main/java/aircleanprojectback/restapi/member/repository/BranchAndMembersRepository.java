package aircleanprojectback.restapi.member.repository;

import aircleanprojectback.restapi.member.entity.BranchAndMembers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchAndMembersRepository extends JpaRepository<BranchAndMembers,String> {

    @Query(
            value = "select a.*, b.branch_code, b.branch_address, b.branch_image, b.branch_name , b.branch_open_date ,b.branch_phone, b.branch_region , b.owner_status from tbl_members a join tbl_branch b on a.member_id =b.member_id where a.member_role =?1 and member_status = ?2"
            , nativeQuery = true
    )
    Page<BranchAndMembers> findAllByMemberRoleAndMemberStatus(String role, String status, Pageable paging);

}
