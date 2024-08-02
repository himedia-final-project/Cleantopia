package aircleanprojectback.restapi.member.repository;

import aircleanprojectback.restapi.member.entity.DriverAndCar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DriverAndCarRepository extends JpaRepository<DriverAndCar, String> {
    List<DriverAndCar> findAllByMemberIdIn(List<String> deleteMember);
}
