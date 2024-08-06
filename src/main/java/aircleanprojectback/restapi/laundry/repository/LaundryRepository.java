package aircleanprojectback.restapi.laundry.repository;

import aircleanprojectback.restapi.laundry.entity.Laundry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LaundryRepository extends JpaRepository<Laundry, Long> {
    

    List<Laundry> findByBranchCode(String branchCode);

    @Modifying
    @Query("UPDATE Laundry l SET l.laundryWashingInstructionStatus = :status WHERE l.laundryCode = :laundryCode")
    void updateWashingInstructionStatus(String status, int laundryCode);
}
