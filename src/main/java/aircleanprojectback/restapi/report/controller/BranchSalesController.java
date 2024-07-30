package aircleanprojectback.restapi.report.controller;

import aircleanprojectback.restapi.common.dto.ResponseDTO;
import aircleanprojectback.restapi.report.dto.BranchSalesDTO;
import aircleanprojectback.restapi.report.service.BranchSalesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/paper")
@Slf4j
public class BranchSalesController {
    // 지점지출보고서

    private final BranchSalesService branchSalesService;
    public BranchSalesController(BranchSalesService branchSalesService) {this.branchSalesService = branchSalesService;}

//    private List<BranchSalesDTO> branchSales = new ArrayList<>();


    // /company 매출보고서 전체 조회
    @GetMapping("/company/reports")
    public ResponseEntity<ResponseDTO> selectAllBranchSales() {

        List<BranchSalesDTO> branchSales = branchSalesService.getAllBranchSales();
        return  ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회성공", branchSales));
    }

    // /company 매출보고서 세부조회
    @GetMapping("/company/{branchCode}/detailBranch")
    public ResponseEntity<ResponseDTO> selectBranchSalesByBranchCode(@PathVariable int branchCode) {


        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "지출보고서 부분조회 성공",branchSalesService.detailBranchSales(branchCode)));
    }



    // /location 지점 매출 보고서 자신이 쓴 지출 보고서 전체 조회
    @GetMapping("/location/{branchCode}/reports")
    public ResponseEntity<ResponseDTO> selectBranchSalesByBranchCode(@PathVariable String branchCode) {

         List<BranchSalesDTO> branchSales = new ArrayList<>();

        return  ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"조회성공",branchSales));
    }

    // /location 지점 매출보고서 작성
    @PostMapping("/location/reports")
    public ResponseEntity<ResponseDTO> insertBranchSales(@RequestBody BranchSalesDTO branchSalesDTO) {

        System.out.println("branchSalesDTO = " + branchSalesDTO);
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK,"보고서 등록 성공", branchSalesService.insertBranchSales(branchSalesDTO)));

    }

    // /location 지점 매출보고서 수정
    @PutMapping("/location/reports/{branchReportCode}")
    public ResponseEntity<ResponseDTO> updateBranchSales(@RequestBody BranchSalesDTO branchSalesDTO, @PathVariable int branchReportCode) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "수정에 성공하였습니다", branchSalesService.updateBranchSales(branchSalesDTO, branchReportCode)));
    }

    // 매출보고서 상태변화 수정 - 승인
    @PutMapping("/company/reports/approve/{branchReportCode}")
    public ResponseEntity<ResponseDTO> updateApproveBranchSales(@PathVariable int branchReportCode) {

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "매출보고서 상태수정 승인 성공", branchSalesService.updateBranchSalesState(branchReportCode, "승인")));

    }

    // 매출보고서 상태변화 수정 - 반려
    @PutMapping("/company/reports/reject/{branchReportCode}")
    public ResponseEntity<ResponseDTO> updateRejectBranchSales(@PathVariable int branchReportCode) {
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "매출보고서 상태수정 반려 성공", branchSalesService.updateBranchSalesState(branchReportCode, "반려")));
    }

    // /location 지점장이 매출보고서 삭제
    @DeleteMapping("/location/reports/{branchReportCode}")
    public ResponseEntity<ResponseDTO> deleteBranchSales(@PathVariable int branchReportCode) {

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "삭제에 성공하였습니다", branchSalesService.deleteBranchSales(branchReportCode)));

    }








}
