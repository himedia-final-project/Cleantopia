package aircleanprojectback.restapi.mainpage.model.dao;

import aircleanprojectback.restapi.mainpage.model.entity.BranchSalesAndBranch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchSalesAndBranchRepository extends JpaRepository<BranchSalesAndBranch, Integer> {

    @Query(value = "select * from tbl_branch_sales_report where MONTH(branch_submission_date) = ?1 and YEAR(branch_submission_date)=?2 and branch_report_approve = ?3", nativeQuery = true)
    List<BranchSalesAndBranch> findAllByBranchSubmissionDateIn(String month, String year, String y);

    @Query(value= "select sum(total_branch_sales_cost) from tbl_branch_sales_report where YEAR(branch_submission_date)= ?1 and branch_report_approve = ?2",nativeQuery = true)
    long findAnnualRevenue(String year, String y);

    @Query(value= "select sum(total_branch_sales_cost) from tbl_branch_sales_report where MONTH(branch_submission_date)=?1 and YEAR(branch_submission_date)= ?2 and branch_report_approve = ?3",nativeQuery = true)
    long findMonthRevenue(String month, String year, String y);

    @Query(value =  "select * from tbl_branch_sales_report where MONTH(branch_submission_date) = ?1 and YEAR(branch_submission_date)=?2 and branch_code = ?3 and branch_report_approve = ?4",nativeQuery = true)
    List<BranchSalesAndBranch> findAllByMonthAndBranchCode(String month, String year, String branchCode, String y);

    @Query(value= "select sum(total_branch_sales_cost) from tbl_branch_sales_report where YEAR(branch_submission_date)= ?1 and branch_report_approve = ?2 and branch_code = ?3",nativeQuery = true)
    long findAnnualRevenueAndBranchCode(String year, String y, String branchCode);

    @Query(value= "select sum(total_branch_sales_cost) from tbl_branch_sales_report where MONTH(branch_submission_date)=?1 and YEAR(branch_submission_date)= ?2 and branch_report_approve = ?3 and branch_code = ?4",nativeQuery = true)
    long findMonthRevenueWithBranchCode(String month, String year, String y, String branchCode);
}
