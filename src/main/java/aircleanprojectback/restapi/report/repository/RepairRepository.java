package aircleanprojectback.restapi.report.repository;

import aircleanprojectback.restapi.report.entity.Repair;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepairRepository extends JpaRepository<Repair, Integer> {
    Page<Repair> findByMemberName(String memberName, Pageable repairMemberNamePageable);
}