package aircleanprojectback.restapi.laundry.repository;

import aircleanprojectback.restapi.laundry.entity.LaundryWay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LaundryWayRepository extends JpaRepository<LaundryWay, Long> {


    @Query("SELECT lw FROM LaundryWay lw WHERE lw.laundry.laundryCode IN :laundryCodes")
    List<LaundryWay> findByLaundryCodes(@Param("laundryCodes") List<Integer> laundryCodes);
}
