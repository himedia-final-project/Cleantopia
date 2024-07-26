package aircleanprojectback.restapi.report.service;

import aircleanprojectback.restapi.report.dto.ExpenseDTO;
import aircleanprojectback.restapi.report.entity.Expense;
import aircleanprojectback.restapi.report.repository.ExpenseRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final ModelMapper modelMapper;

    public ExpenseService(ExpenseRepository expenseRepository, ModelMapper modelMapper) {
        this.expenseRepository = expenseRepository;
        this.modelMapper = modelMapper;
    }

    public List<ExpenseDTO> getAllExpense() {

        List<Expense> expenses = expenseRepository.findAll();
        List<ExpenseDTO> expenseDTOList = expenses.stream()
                .map(expense -> modelMapper.map(expense, ExpenseDTO.class))
                .collect(Collectors.toList());

        return expenseDTOList;

    }

    public ExpenseDTO detailExpenseReports(int expenseReportCode) {
        Expense expense = expenseRepository.findById(expenseReportCode).get();
        ExpenseDTO expenseDTO = modelMapper.map(expense, ExpenseDTO.class);
        return expenseDTO;
    }

    // 지출보고서 승인/반려
    public Expense updateExpenseState(int expenseReportCode, String expenseReportStatus) {
        Expense expense = expenseRepository.findById(expenseReportCode)
                .orElseThrow(() -> new IllegalArgumentException("Invalid expenseReportCode: " + expenseReportCode));
        expense.setExpenseReportStatus(expenseReportStatus);
        return expenseRepository.save(expense);
    }
}
