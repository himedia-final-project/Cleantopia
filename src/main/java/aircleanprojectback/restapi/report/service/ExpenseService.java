package aircleanprojectback.restapi.report.service;

import aircleanprojectback.restapi.common.dto.Criteria;
import aircleanprojectback.restapi.report.dto.BranchSalesDTO;
import aircleanprojectback.restapi.report.dto.ExpenseDTO;
import aircleanprojectback.restapi.report.entity.BranchSales;
import aircleanprojectback.restapi.report.entity.Expense;
import aircleanprojectback.restapi.report.repository.ExpenseRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    // 지출보고서 전체조회
    public Page<ExpenseDTO> getAllExpense(Criteria expenseCriteria) {

        Pageable expensePageable = PageRequest.of(expenseCriteria.getPageNum()-1, expenseCriteria.getAmount(), Sort.by(Sort.Direction.DESC, "expenseReportCode"));

        Page<Expense> expensePage = expenseRepository.findAll(expensePageable);
        Page<ExpenseDTO> expenseDTO  = expensePage.map(expense -> modelMapper.map(expense, ExpenseDTO.class));

        return expenseDTO;
    }

    // 지출보고서 필터링 전체조회
    public Page<ExpenseDTO> findExpenseMemberName(Criteria expenseCriteriaMemberName, String memberName) {

        Pageable expenseMemberNamePageable = PageRequest.of(expenseCriteriaMemberName.getPageNum() -1, expenseCriteriaMemberName.getAmount(),Sort.by(Sort.Direction.DESC, "expenseReportCode"));
        Page<Expense> expenseMemberNameList = expenseRepository.findByMemberName(memberName, expenseMemberNamePageable);
        Page<ExpenseDTO> expenseDTOMemberNameDTO = expenseMemberNameList.map(expense -> modelMapper.map(expense, ExpenseDTO.class));

        return expenseDTOMemberNameDTO;

    }

    // 지출보고서 세부조회
    public ExpenseDTO detailExpenseReports(int expenseReportCode) {
        Expense expense = expenseRepository.findById(expenseReportCode).get();
        ExpenseDTO expenseDTO = modelMapper.map(expense, ExpenseDTO.class);
        return expenseDTO;
    }

    // 지출보고서 승인/반려
    public Expense updateExpenseState(int expenseReportCode, String expenseReportStatus, String expenseApprove) {
        Expense expense = expenseRepository.findById(expenseReportCode)
                .orElseThrow(() -> new IllegalArgumentException("Invalid expenseReportCode: " + expenseReportCode));
        expense.setExpenseReportStatus(expenseReportStatus);
        expense.setExpenseApprove(expenseApprove);
        return expenseRepository.save(expense);
    }

    // 지출보고서 등록
    @Transactional
    public String newExpense(ExpenseDTO expenseDTO) {

        Expense expense = modelMapper.map(expenseDTO, Expense.class);
        expenseRepository.save(expense);
        return "지출보고서 등록 성공";
    }

    // 지출보고서 수정
    @Transactional
    public Expense updateExpense(int expenseReportCode, ExpenseDTO expenseDTO) {

        Expense expense = expenseRepository.findById(expenseReportCode)
                .orElseThrow(() -> new RuntimeException("지출보고서를 찾을 수 없습니다. : " + expenseReportCode));

        expense = expense
                .electricityBill(expenseDTO.getElectricityBill())
                .waterBill(expenseDTO.getWaterBill())
                .gasBill(expenseDTO.getGasBill())
                .partTimeSalary(expenseDTO.getPartTimeSalary())
                .repairCost(expenseDTO.getRepairCost())
                .expenseRemark(expenseDTO.getExpenseRemark());

        expenseRepository.save(expense);
        return expense;
    }

    // 지출보고서 삭제
    public String deleteExpense(int expenseReportCode) {

        expenseRepository.deleteById(expenseReportCode);
        return "삭제 성공";
    }



}