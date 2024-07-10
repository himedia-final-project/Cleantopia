package aircleanprojectback.restapi.member.repository;

import aircleanprojectback.restapi.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member findByMemberId(String memberId);
}
