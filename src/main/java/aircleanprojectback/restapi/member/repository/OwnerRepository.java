package aircleanprojectback.restapi.member.repository;

import aircleanprojectback.restapi.member.entity.BranchOwner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<BranchOwner ,Integer> {



    @Query(value = "select a.*, b.owner_code , c.* " +
            "from tbl_members a " +
            "left join tbl_branch_owner b on a.member_id = b.member_id " +
            "left join tbl_branch c on c.branch_code = b.branch_code " +
            "where a.member_role = ?1 and a.member_status = ?2", nativeQuery = true)
    Page<BranchOwner> findAllLeftJoin(String role, String status, Pageable pageable);

    @Query(value = "insert into tbl_branch_owner (branch_code , member_id) values (?1,?2)",nativeQuery = true)
    void insertData(String branchCode, String memberId);

    @Query(value ="insert into tbl_branch_owner(member_id) values (?1)",nativeQuery = true)
    void insertDataByMemberId(String memberId);

    @Query(value = "select a.*, b.owner_code , c.* " +
            "from tbl_members a " +
            "left join tbl_branch_owner b on a.member_id = b.member_id " +
            "left join tbl_branch c on c.branch_code = b.branch_code " +
            "where a.member_id = ?1", nativeQuery = true)
    BranchOwner findByMemberId(String memberId);

//    @Query(value = "a.*, b.owner_code , c.* " +
//            "")
//    Page<BranchOwner> findAllByMemberStatusAndMemberRole(String n, String b, Pageable pageable);
}
