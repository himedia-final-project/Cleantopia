package aircleanprojectback.restapi.car.repository;

import aircleanprojectback.restapi.car.entity.MemberNameAndDriver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberNameAndDriverRepository extends JpaRepository<MemberNameAndDriver,String> {

    @Query(value = "select a.member_name , b.* from tbl_members a join tbl_driver b on a.member_id = b.member_id " +
            "where a.member_status =?2 and b.assign_car = ?1", nativeQuery = true)
    List<MemberNameAndDriver> findAllByAssignCar(String n, String y);
}
