package aircleanprojectback.restapi.report.repository;

import aircleanprojectback.restapi.report.entity.CarMembers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarMembersRepository extends JpaRepository<CarMembers, String> {

    @Query(value = "select a.member_id , a.member_name , b.driver_license_number , c.car_number from tbl_members a join tbl_driver b on a.member_id = b.member_id" +
            " join tbl_car c on c.driver_license_number = b.driver_license_number",nativeQuery = true)
    List<CarMembers> findDriver();
}