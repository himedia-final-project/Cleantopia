package aircleanprojectback.restapi.report.controller;

import aircleanprojectback.restapi.common.dto.Criteria;
import aircleanprojectback.restapi.common.dto.ResponseDTO;
import aircleanprojectback.restapi.report.dto.BranchSalesDTO;
import aircleanprojectback.restapi.report.service.BranchSalesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/paper")
@Slf4j
@Tag(name = "지점 매출보고서", description = "지점매출보고서 관리 API")
public class BranchSalesController {

    private final BranchSalesService branchSalesService;

    public BranchSalesController(BranchSalesService branchSalesService) {
        this.branchSalesService = branchSalesService;
    }

    @Operation(summary = "전지점 매출보고서 조회", description = "모든 지점 매출보고서를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    })
    @GetMapping("/company/reports")
    public ResponseEntity<ResponseDTO> selectAllBranchSales(@RequestParam(defaultValue = "1") String offset) {
        Criteria branchSalesCriteria = new Criteria();
        branchSalesCriteria.setPageNum(Integer.parseInt(offset));
        branchSalesCriteria.setAmount(6);
        Page<BranchSalesDTO> branchSales = branchSalesService.getAllBranchSales(branchSalesCriteria);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회성공", branchSales));
    }

    @Operation(summary = "매출보고서 지점장 필터링 조회", description = "지점장 이름으로 매출보고서를 필터링하여 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "필터링 조회 완료",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    })
    @GetMapping("/location/branch-sales")
    public ResponseEntity<ResponseDTO> locationBranchSales(
            @RequestParam(defaultValue = "1") String offset,
            @RequestParam String memberName) {
        Criteria branchSalesCriteriaMemberName = new Criteria();
        branchSalesCriteriaMemberName.setPageNum(Integer.parseInt(offset));
        branchSalesCriteriaMemberName.setAmount(6);
        Page<BranchSalesDTO> branchSalesMemberName = branchSalesService.findBranchSalesMemberName(branchSalesCriteriaMemberName, memberName);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "필터링조회 완료", branchSalesMemberName));
    }

    @Operation(summary = "매출보고서 세부조회", description = "지점 코드로 매출보고서를 세부 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "부분조회 성공",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "보고서 없음",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    })
    @GetMapping("/company/{branchCode}/detailBranch")
    public ResponseEntity<ResponseDTO> selectBranchSalesByBranchCode(@PathVariable int branchCode) {
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "지출보고서 부분조회 성공", branchSalesService.detailBranchSales(branchCode)));
    }

    @Operation(summary = "지점장 자신이 쓴 보고서 전체 조회", description = "지점장 이름으로 자신이 쓴 지출 보고서를 전체 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    })
    @GetMapping("/location/{branchCode}/reports")
    public ResponseEntity<ResponseDTO> selectBranchSalesByBranchCode(@PathVariable String branchCode) {
        List<BranchSalesDTO> branchSales = new ArrayList<>();
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회성공", branchSales));
    }

    @Operation(summary = "매출보고서 작성", description = "새로운 매출보고서를 작성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "보고서 등록 성공",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    })
    @PostMapping("/location/reports")
    public ResponseEntity<ResponseDTO> insertBranchSales(@RequestBody BranchSalesDTO branchSalesDTO) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "보고서 등록 성공", branchSalesService.insertBranchSales(branchSalesDTO)));
    }

    @Operation(summary = "매출보고서 수정", description = "기존 매출보고서를 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "수정에 성공하였습니다",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    })
    @PutMapping("/location/reports/{branchReportCode}")
    public ResponseEntity<ResponseDTO> updateBranchSales(@RequestBody BranchSalesDTO branchSalesDTO, @PathVariable int branchReportCode) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "수정에 성공하였습니다", branchSalesService.updateBranchSales(branchSalesDTO, branchReportCode)));
    }

    @Operation(summary = "매출보고서 승인", description = "매출보고서를 승인 상태로 변경합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "매출보고서 상태수정 승인 성공",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    })
    @PutMapping("/company/reports/approve/{branchReportCode}")
    public ResponseEntity<ResponseDTO> updateApproveBranchSales(@PathVariable int branchReportCode) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "매출보고서 상태수정 승인 성공", branchSalesService.updateBranchSalesState(branchReportCode, "Y", "Y")));
    }

    @Operation(summary = "매출보고서 반려", description = "매출보고서를 반려 상태로 변경합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "매출보고서 상태수정 반려 성공",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    })
    @PutMapping("/company/reports/reject/{branchReportCode}")
    public ResponseEntity<ResponseDTO> updateRejectBranchSales(@PathVariable int branchReportCode) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "매출보고서 상태수정 반려 성공", branchSalesService.updateBranchSalesState(branchReportCode, "N", "R")));
    }

    @Operation(summary = "매출보고서 삭제", description = "매출보고서를 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "삭제에 성공하였습니다",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    })
    @DeleteMapping("/location/reports/{branchReportCode}")
    public ResponseEntity<ResponseDTO> deleteBranchSales(@PathVariable int branchReportCode) {
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "삭제에 성공하였습니다", branchSalesService.deleteBranchSales(branchReportCode)));
    }
}
