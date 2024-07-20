package aircleanprojectback.restapi.report.repository;

import aircleanprojectback.restapi.report.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, String> {
}
