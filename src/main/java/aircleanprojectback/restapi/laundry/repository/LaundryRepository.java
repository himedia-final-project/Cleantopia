package aircleanprojectback.restapi.laundry.repository;

import aircleanprojectback.restapi.laundry.entity.Laundry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface LaundryRepository extends JpaRepository<Laundry, Long> {
    

    List<Laundry> findByBranchCode(String branchCode);

    @Query("SELECT l.laundryCode FROM Laundry l WHERE l.branchCode = :branchCode")
    List<Integer> findLaundryCodesByBranchCode(@Param("branchCode") String branchCode);

    @Modifying
    @Query("UPDATE Laundry l SET l.laundryWashingInstructionStatus = :status WHERE l.laundryCode = :laundryCode")
    void updateWashingInstructionStatus(String status, int laundryCode);

    List<Laundry> findByBranchCodeAndLaundryApprovalDate(String branchCode, LocalDate localDate);

    List<Laundry> findByBranchCodeAndLaundryCollectionStatusOrderByLaundryCustomerRegistDate(String branchCode, String n);


    List<Laundry> findAllByLaundryApprovalDate(LocalDate localDate);
}
