package aircleanprojectback.restapi.member.repository;

import aircleanprojectback.restapi.member.entity.MemberAndDriver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberAndDriverRepository extends JpaRepository<MemberAndDriver,String> {

//    @Query("SELECT m FROM MemberAndDriver m " +
//            "LEFT JOIN FETCH m.driverAndCar d " +
//            "LEFT JOIN FETCH d.car c " +
//            "WHERE m.memberStatus = :memberStatus " +
//            "AND m.memberRole = :memberRole")
@Query("SELECT m FROM MemberAndDriver m " +
        "LEFT JOIN FETCH m.driverAndCar d " +
        "LEFT JOIN FETCH d.car c " +
        "WHERE m.memberStatus = :memberStatus " +
        "AND m.memberRole = :memberRole")
    Page<MemberAndDriver> findAllDriverWithCar(@Param("memberStatus") String y,@Param("memberRole") String d, Pageable pageable);

    Page<MemberAndDriver> findAllByMemberStatus(String n, Pageable pageable);
}
