package aircleanprojectback.restapi.stock.repository;

import aircleanprojectback.restapi.stock.entity.HeadStockApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeadStockApplicationRepository extends JpaRepository<HeadStockApplication, Integer> {

    @Query("SELECT h FROM HeadStockApplication h WHERE h.memberId LIKE 'E%' OR h.memberId LIKE 'A%'")
    List<HeadStockApplication> findByMemberIdStartingWithEOrA();
}
