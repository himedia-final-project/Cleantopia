package aircleanprojectback.restapi.member.repository;

import aircleanprojectback.restapi.member.entity.MembersAndDriver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembersAndDriverRepository extends JpaRepository<MembersAndDriver,String> {
}
