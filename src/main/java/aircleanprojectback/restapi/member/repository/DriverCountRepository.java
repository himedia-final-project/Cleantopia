package aircleanprojectback.restapi.member.repository;

import aircleanprojectback.restapi.member.entity.DriverCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DriverCountRepository extends JpaRepository<DriverCount, String> {


    @Query(value = "select a.driver_region , count(a.driver_region) as region_count from tbl_driver a " +
            "join tbl_members b on a.member_id = b.member_id " +
            "where b.member_status = ?1 group by a.driver_region",nativeQuery = true)
    List<DriverCount> findDriverCount(String y);
}

