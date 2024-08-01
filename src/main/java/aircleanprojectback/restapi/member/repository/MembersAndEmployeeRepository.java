package aircleanprojectback.restapi.member.repository;

import aircleanprojectback.restapi.member.entity.MemberAndDriver;
import aircleanprojectback.restapi.member.entity.MembersAndEmployee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembersAndEmployeeRepository extends JpaRepository<MembersAndEmployee , Integer> {
    @Query(value = "select a.*, b.employee_code, b.employee_dept, b.employee_position from tbl_employee b join tbl_members a on a.member_id = b.member_id where a.member_role = ?1 and a.member_status =?2",nativeQuery = true)
    Page<MembersAndEmployee> findByMemberRoleAndMemberStatus(String a, String y, Pageable paging);


}
