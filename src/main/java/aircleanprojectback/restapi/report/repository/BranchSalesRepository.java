package aircleanprojectback.restapi.report.repository;


import aircleanprojectback.restapi.report.entity.BranchSales;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BranchSalesRepository extends JpaRepository<BranchSales, Integer> {

    Page<BranchSales> findByMemberName(String memberName, Pageable branchSalesMemberNamePageable);
}
