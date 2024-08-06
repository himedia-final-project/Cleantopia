package aircleanprojectback.restapi.report.repository;

import aircleanprojectback.restapi.report.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
    Page<Expense> findByMemberName(String memberName, Pageable expenseMemberNamePageable);
}
