package aircleanprojectback.restapi.report.repository;


import aircleanprojectback.restapi.report.entity.BranchSales;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BranchSalesRepository extends JpaRepository<BranchSales, Integer> {

    Page<BranchSales> findByMemberName(String memberName, Pageable branchSalesMemberNamePageable);

//    @Query(value = "select * from tbl_branch_sales_report where MONTH(branch_submission_date) = ?1 and YEAR(branch_submission_date)=?2 and branch_report_approve = ?3", nativeQuery = true)
//    List<BranchSales> findAllByBranchSubmissionDateIn(String month , String year, String y);
//
//    @Query(value =  "select * from tbl_branch_sales_report where MONTH(branch_submission_date) = ?1 and YEAR(branch_submission_date)=?2 and branch_code = ?3 and branch_report_approve = ?4",nativeQuery = true)
//    List<BranchSales> findAllByMonthAndBranchCode(String month, String year,String branchCode,  String y);
//
//    @Query(value= "select sum(total_branch_sales_cost) from tbl_branch_sales_report where YEAR(branch_submission_date)= ?1 and branch_report_approve = ?2",nativeQuery = true)
//    long findAnnualRevenue(String year, String y);
//
//    @Query(value= "select sum(total_branch_sales_cost) from tbl_branch_sales_report where YEAR(branch_submission_date)= ?1 and branch_report_approve = ?2 and branch_code = ?3",nativeQuery = true)
//    long findAnnualRevenueAndBranchCode(String year, String y, String branchCode);
//
//    @Query(value= "select sum(total_branch_sales_cost) from tbl_branch_sales_report where MONTH(branch_submission_date)=?1 and YEAR(branch_submission_date)= ?2 and branch_report_approve = ?3",nativeQuery = true)
//    long findMonthRevenue(String month, String year, String y);
//
//    @Query(value= "select sum(total_branch_sales_cost) from tbl_branch_sales_report where MONTH(branch_submission_date)=?1 and YEAR(branch_submission_date)= ?2 and branch_report_approve = ?3 and branch_code = ?4",nativeQuery = true)
//    long findMonthRevenueWithBranchCode(String month, String year, String y, String branchCode);
}