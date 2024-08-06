package aircleanprojectback.restapi.report.controller;

import aircleanprojectback.restapi.common.dto.Criteria;
import aircleanprojectback.restapi.common.dto.ResponseDTO;
import aircleanprojectback.restapi.report.dto.BranchSalesDTO;
import aircleanprojectback.restapi.report.dto.ExpenseDTO;
import aircleanprojectback.restapi.report.service.ExpenseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paper")
@Slf4j
public class ExpenseController {
    // 지출보고서

    private final ExpenseService expenseService;
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    // 지출보고서 전체조회
    @GetMapping("/company/expenseReports")
    public ResponseEntity<ResponseDTO> selectAllExpense(@RequestParam(defaultValue = "1") String offset) {

        Criteria expenseCriteria = new Criteria();
        expenseCriteria.setPageNum(Integer.parseInt(offset));
        expenseCriteria.setAmount(6);
        Page<ExpenseDTO>expense = expenseService.getAllExpense(expenseCriteria);

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "매출보고서 조회에 성공하셨습니다", expense));
    }

    // 지출보고서 필터링조회
    @GetMapping("/location/expense")
    public ResponseEntity<ResponseDTO> locationExpense(@RequestParam(defaultValue = "1") String offset,
                                                           @RequestParam String memberName) {
        Criteria expenseCriteriaMemberName = new Criteria();
        expenseCriteriaMemberName.setPageNum(Integer.parseInt(offset));
        expenseCriteriaMemberName.setAmount(6);
        Page<ExpenseDTO> expenseMemberName = expenseService.findExpenseMemberName(expenseCriteriaMemberName,memberName);

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK,"필터링조회 완료", expenseMemberName));

    }

    // 지출보고서 상세조회
    @GetMapping("/company/detailExpenseReports/{expenseReportCode}")
    public ResponseEntity<ResponseDTO> detailExpense(@PathVariable int expenseReportCode) {

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "매출보고서 상세조회 성공",expenseService.detailExpenseReports(expenseReportCode)));
    }

//     지출보고서 상태수정 - 승인
    @PutMapping("/company/reports/expenseApprove/{expenseReportCode}")
    public ResponseEntity<ResponseDTO> updateApproveExpense(@PathVariable int expenseReportCode){
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "지출보고서 상태수정 승인", expenseService.updateExpenseState(expenseReportCode, "Y", "Y")));
    }

//     지출보고서 상태수정 - 반려

    @PutMapping("/company/reports/expenseReject/{expenseReportCode}")
    public ResponseEntity<ResponseDTO> updateRejectExpense(@PathVariable int expenseReportCode){
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "지출보고서 상태수정 반려성공", expenseService.updateExpenseState(expenseReportCode, "R", "N")));
    }

    // 등록
    @PostMapping("/newExpense")
    public ResponseEntity<ResponseDTO> newExpense(@RequestBody ExpenseDTO expenseDTO) {

        System.out.println("expenseDTO = " + expenseDTO);
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK,"지출보고서 등록완료", expenseService.newExpense(expenseDTO)));
    }

    // 지출보고서 수정
    @PutMapping("/updateExpense/{expenseReportCode}")
    public ResponseEntity<ResponseDTO> updateExpense(@PathVariable int expenseReportCode, @RequestBody ExpenseDTO expenseDTO) {
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK,"지출보고서 수정 성공", expenseService.updateExpense(expenseReportCode,expenseDTO)));
    }

    // 지출보고서 삭제
    @DeleteMapping("/deleteExpense/{expenseReportCode}")
    public ResponseEntity<ResponseDTO> deleteExpense(@PathVariable int expenseReportCode) {
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK,"지출보고서 삭제 성공",expenseService.deleteExpense(expenseReportCode)));
    }
}
