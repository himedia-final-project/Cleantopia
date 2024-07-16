package aircleanprojectback.restapi.report.controller;

import aircleanprojectback.restapi.common.dto.ResponseDTO;
import aircleanprojectback.restapi.report.dto.BranchSalesDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/paper")
@Slf4j
public class BranchSalesController {
    // 지점매출보고서
    private List<BranchSalesDTO> branchSales = new ArrayList<>();

    // 샘플 데이터 추가 (테스트용)
    public BranchSalesController() {
        branchSales.add(new BranchSalesDTO(1, Date.valueOf("2024-07-16"), 100000, "Submitted", "Ariel", "Downy", "Clorox", "Shout", "Affresh", "Bounce", "B001"));
        branchSales.add(new BranchSalesDTO(2, Date.valueOf("2024-07-17"), 200000, "Submitted", "Tide", "Snuggle", "OxiClean", "Zout", "Lemi Shine", "Snuggle", "B002"));
    }

    @GetMapping("/company/reports")
    public ResponseEntity<ResponseDTO> selectAllBranchSales() {

        return  ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회성공", branchSales));
    }


}
