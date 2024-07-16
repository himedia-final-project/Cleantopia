package aircleanprojectback.restapi.member.repository;

import aircleanprojectback.restapi.member.entity.Ask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AskRepository extends JpaRepository<Ask,Integer> {
}
