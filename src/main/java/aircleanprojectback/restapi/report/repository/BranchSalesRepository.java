package aircleanprojectback.restapi.report.repository;


import aircleanprojectback.restapi.report.dto.BranchAndMember;
import aircleanprojectback.restapi.report.entity.BranchSales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BranchSalesRepository extends JpaRepository<BranchSales, Integer> {



}
