package aircleanprojectback.restapi.mainpage.model.dao;

import aircleanprojectback.restapi.mainpage.model.entity.ExpenseAndBranch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseAndBranchRepository extends JpaRepository<ExpenseAndBranch , Integer> {

    @Query(value = "select * from tbl_expense_report where YEAR(expense_submission_date)=?1 and MONTH(expense_submission_date)=?2 and expense_report_status =?3", nativeQuery = true)
    List<ExpenseAndBranch> findAllWithDateAndStatus(String year, String month, String y);
}
