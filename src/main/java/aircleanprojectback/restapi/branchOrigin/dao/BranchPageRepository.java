package aircleanprojectback.restapi.branchOrigin.dao;

import aircleanprojectback.restapi.branchOrigin.dto.BranchOwnerPageDTO;
import aircleanprojectback.restapi.branchOrigin.entity.BranchPage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchPageRepository extends JpaRepository<BranchPage , String> {

    @Query(value = "select a.* , b.member_id, b.branch_ownership, b.member_address" +
            ", b.member_birth_date , b.member_email, b.member_gender, b.member_hire_date," +
            "b.member_image,b.member_name, b.member_password, b.member_phone_number, b.member_role" +
            ",b.member_status from tbl_branch a left join (" +
            "select c.owner_code , c.branch_code , d.* from tbl_branch_owner c left join tbl_members d on " +
            "c.member_id = d.member_id ) b " +
            "on a.branch_code = b.branch_code ", nativeQuery = true)
    List<BranchPage> findLeftJoin();
}
