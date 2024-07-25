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

//    @Query("SELECT bam FROM BranchAndMembers bam JOIN FETCH bam.branch WHERE bam.memberRole = :role AND bam.memberStatus = :status")
    Page<BranchAndMembers> findAllByMemberRoleAndMemberStatus(@Param("role") String role, @Param("status") String status, Pageable paging);

}
