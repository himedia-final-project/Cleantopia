package aircleanprojectback.restapi.member.repository;

import aircleanprojectback.restapi.member.entity.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DriverRepository extends JpaRepository<Driver , String> {

    Page<Driver> findAllByMembersMemberStatusAndMembersMemberRole(String y, String d, Pageable pageable);


//    @Query(name = "select c.* , d.* from (" +
//            "select a.*, b.driver_license , b.driver_region from tbl_members a " +
//            "join tbl_driver b on a.member_id = b.member_id ) as c " +
//            "left join tbl_car d on c.driver_license_number = d.driver_license_number " +
//            "where c.member_role =?2 and c.member_status = ?1", nativeQuery = true)
//    Page<Driver> findAllByMemberStatusAndMemberRole(String status, String role, Pageable pageable);


}
