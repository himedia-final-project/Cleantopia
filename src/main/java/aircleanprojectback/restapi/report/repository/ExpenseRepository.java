package aircleanprojectback.restapi.report.repository;

import aircleanprojectback.restapi.report.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
    Page<Expense> findByMemberName(String memberName, Pageable expenseMemberNamePageable);

//    @Query(value = "select * from tbl_expense_report where YEAR(expense_submission_date)=?1 and MONTH(epxense_submission_date)=?2 and expense_report_status =?3", nativeQuery = true)
//    List<Expense> findAllExpense(String year, String month, String y);

    @Query(value = "select * from tbl_expense_report where YEAR(expense_submission_date)=?1 and MONTH(epxense_submission_date)=?2 and expense_report_status =?3", nativeQuery = true)
    List<Expense> findAllWithDateAndStatus(String year, String month, String y);
}

