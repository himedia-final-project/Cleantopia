package aircleanprojectback.restapi.car.repository;

import aircleanprojectback.restapi.car.entity.LaundryAndDriver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaundryAndDriverRepository extends JpaRepository<LaundryAndDriver,Integer> {
    Page<LaundryAndDriver> findAllByBranchCode(String branchCode, Pageable pageable);


    Page<LaundryAndDriver> findAllByBranchCodeAndLaundryCompleted(String branchCode, String y, Pageable pageable);

    Page<LaundryAndDriver> findAllByBranchCodeAndLaundryCollectionStatusAndLaundryCompleted(String branchCode, String y, String n, Pageable pageable);
}
