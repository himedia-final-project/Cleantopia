package aircleanprojectback.restapi.report.controller;

import aircleanprojectback.restapi.common.dto.Criteria;
import aircleanprojectback.restapi.common.dto.ResponseDTO;
import aircleanprojectback.restapi.report.dto.ExpenseDTO;
import aircleanprojectback.restapi.report.service.ExpenseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/paper")
@Slf4j
@Tag(name = "지점 지출보고서", description = "지출보고서 관리 API")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @Operation(summary = "전 지점 지출보고서 전체 조회", description = "모든 지출보고서를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    })
    @GetMapping("/company/expenseReports")
    public ResponseEntity<ResponseDTO> selectAllExpense(
            @Parameter(description = "페이지 번호", example = "1")
            @RequestParam(defaultValue = "1") String offset) {

        Criteria expenseCriteria = new Criteria();
        expenseCriteria.setPageNum(Integer.parseInt(offset));
        expenseCriteria.setAmount(6);
        Page<ExpenseDTO> expense = expenseService.getAllExpense(expenseCriteria);

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "매출보고서 조회에 성공하셨습니다", expense));
    }

    @Operation(summary = "지출보고서 지점장 필터링 조회", description = "지점장 이름 으로 지출보고서를 필터링하여 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "필터링 조회 완료",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    })
    @GetMapping("/location/expense")
    public ResponseEntity<ResponseDTO> locationExpense(
            @Parameter(description = "페이지 번호", example = "1")
            @RequestParam(defaultValue = "1") String offset,
            @Parameter(description = "멤버 이름", example = "John Doe")
            @RequestParam String memberName) {
        Criteria expenseCriteriaMemberName = new Criteria();
        expenseCriteriaMemberName.setPageNum(Integer.parseInt(offset));
        expenseCriteriaMemberName.setAmount(6);
        Page<ExpenseDTO> expenseMemberName = expenseService.findExpenseMemberName(expenseCriteriaMemberName, memberName);

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "필터링 조회 완료", expenseMemberName));
    }

    @Operation(summary = "지출보고서 상세 조회", description = "지출보고서 코드를 사용하여 상세 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "상세 조회 성공",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "보고서 없음",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    })
    @GetMapping("/company/detailExpenseReports/{expenseReportCode}")
    public ResponseEntity<ResponseDTO> detailExpense(
            @Parameter(description = "지출보고서 코드", example = "123")
            @PathVariable int expenseReportCode) {

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "매출보고서 상세 조회 성공", expenseService.detailExpenseReports(expenseReportCode)));
    }

    @Operation(summary = "지출보고서 상태 수정 - 승인", description = "지출보고서 상태를 승인으로 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "상태 수정 성공",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    })
    @PutMapping("/company/reports/expenseApprove/{expenseReportCode}")
    public ResponseEntity<ResponseDTO> updateApproveExpense(
            @Parameter(description = "지출보고서 코드", example = "123")
            @PathVariable int expenseReportCode) {
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "지출보고서 상태 수정 승인", expenseService.updateExpenseState(expenseReportCode, "Y", "Y")));
    }

    @Operation(summary = "지출보고서 상태 반려", description = "지출보고서 상태를 반려로 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "상태 수정 반려 성공",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    })
    @PutMapping("/company/reports/expenseReject/{expenseReportCode}")
    public ResponseEntity<ResponseDTO> updateRejectExpense(
            @Parameter(description = "지출보고서 코드", example = "123")
            @PathVariable int expenseReportCode) {
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "지출보고서 상태 수정 반려 성공", expenseService.updateExpenseState(expenseReportCode, "R", "N")));
    }

    @Operation(summary = "지출보고서 등록", description = "새로운 지출보고서를 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "등록 완료",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    })
    @PostMapping("/newExpense")
    public ResponseEntity<ResponseDTO> newExpense(
            @Parameter(description = "지출보고서 데이터", required = true)
            @RequestBody ExpenseDTO expenseDTO) {
        System.out.println("expenseDTO = " + expenseDTO);
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "지출보고서 등록 완료", expenseService.newExpense(expenseDTO)));
    }

    @Operation(summary = "지출보고서 수정", description = "기존 지출보고서를 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "수정 성공",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    })
    @PutMapping("/updateExpense/{expenseReportCode}")
    public ResponseEntity<ResponseDTO> updateExpense(
            @Parameter(description = "지출보고서 코드", example = "123")
            @PathVariable int expenseReportCode,
            @Parameter(description = "수정할 지출보고서 데이터", required = true)
            @RequestBody ExpenseDTO expenseDTO) {
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "지출보고서 수정 성공", expenseService.updateExpense(expenseReportCode, expenseDTO)));
    }

    @Operation(summary = "지출보고서 삭제", description = "기존 지출보고서를 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "삭제 성공",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    })
    @DeleteMapping("/deleteExpense/{expenseReportCode}")
    public ResponseEntity<ResponseDTO> deleteExpense(
            @Parameter(description = "지출보고서 코드", example = "123")
            @PathVariable int expenseReportCode) {
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "지출보고서 삭제 성공", expenseService.deleteExpense(expenseReportCode)));
    }
}
