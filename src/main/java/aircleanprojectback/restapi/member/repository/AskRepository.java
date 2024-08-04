package aircleanprojectback.restapi.member.repository;

import aircleanprojectback.restapi.member.entity.Ask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AskRepository extends JpaRepository<Ask,Integer> {
    List<Ask> findAllByAskStatus(String y);

    Ask findByMemberId(String memberId);

    List<Ask> findAllByMemberId(String memberId);
}
