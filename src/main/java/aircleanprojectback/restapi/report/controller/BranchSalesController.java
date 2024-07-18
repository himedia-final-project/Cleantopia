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
    // 지점매출보고서

    private final BranchSalesService branchSalesService;
    public BranchSalesController(BranchSalesService branchSalesService) {this.branchSalesService = branchSalesService;}

//    private List<BranchSalesDTO> branchSales = new ArrayList<>();


    // /company 지점지출보고서 전체 조회
    @GetMapping("/company/reports")
    public ResponseEntity<ResponseDTO> selectAllBranchSales() {

        List<BranchSalesDTO> branchSales = branchSalesService.getAllBranchSales();

        return  ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회성공", branchSales));
    }

    // /location 지점 지출 보고서 자신이 쓴 지출 보고서 전체 조회
    @GetMapping("/location/{branchCode}/reports")
    public ResponseEntity<ResponseDTO> selectBranchSalesByBranchCode(@PathVariable String branchCode) {

         List<BranchSalesDTO> branchSales = new ArrayList<>();

        return  ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"조회성공",branchSales));
    }

    // /location 지점 지출보고서 작성
    @PostMapping("/location/reports")
    public ResponseEntity<ResponseDTO> insertBranchSales(@RequestBody BranchSalesDTO branchSalesDTO) {

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK,"보고서 등록 성공", branchSalesService.insertBranchSales(branchSalesDTO)));

    }

    // /location 지점 지출보고서 수정
    @PutMapping("/location/reports/{branchReportCode}")
    public ResponseEntity<ResponseDTO> updateBranchSales(@RequestBody BranchSalesDTO branchSalesDTO, @PathVariable int branchReportCode) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "수정에 성공하였습니다", branchSalesService.updateBranchSales(branchSalesDTO, branchReportCode)));
    }

    // /location 지점장이 지출보고서 삭제
    @DeleteMapping("/location/reports/{branchReportCode}")
    public ResponseEntity<ResponseDTO> deleteBranchSales(@PathVariable int branchReportCode) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "삭제에 성공하였습니다", branchSalesService.deleteBranchSales(branchReportCode)));

    }







}
