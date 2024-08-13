package aircleanprojectback.restapi.stock.repository;

import aircleanprojectback.restapi.stock.entity.StockExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockExpenseRepository extends JpaRepository<StockExpense , Integer> {

    @Query(value = "select * from tbl_stock_expense where MONTH(expense_date)= ?2 and YEAR(expense_date)=?3 and branch_code = ?1",nativeQuery = true)
    List<StockExpense> findAllByBranchCode(String branchCode, String month, String year);

    @Query(value = "select  * from tbl_stock_expense where YEAR(expense_date)=?2 and branch_code = ?1",nativeQuery = true)
    List<StockExpense> findTotalToday(String head, String year);
}
