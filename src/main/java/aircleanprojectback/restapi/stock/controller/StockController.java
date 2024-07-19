package aircleanprojectback.restapi.stock.controller;

import aircleanprojectback.restapi.common.dto.ResponseDTO;
import aircleanprojectback.restapi.stock.dto.LaundryPartAndManagementDTO;
import aircleanprojectback.restapi.stock.dto.LaundrySupplyAndManagementDTO;
import aircleanprojectback.restapi.stock.dto.MembersSubDTO;
import aircleanprojectback.restapi.stock.entity.LaundryPartAndManagement;
import aircleanprojectback.restapi.stock.service.StockService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Tag(name = "재고관리 스웨거 연동")
@RestController
@Slf4j
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    // React 에서 토큰 정보 땡겨와서 하기!
    @Tag(name = "본사 세탁용품 재고정보 조회")
    @GetMapping("/company/h-stock/detergents")
    public ResponseEntity<ResponseDTO> showAllHStockDetergentsInfo(@RequestParam Map<String,String> members) {

        System.out.println("membersSubDTO = " + members);
//        List<LaundrySupplyAndManagementDTO> hDetergentList = stockService.selectAllHDetergent();

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", members));
    }

//    @Tag(name = "본사 세탁부품 재고정보 조회")
//    @GetMapping("company/h-stock/parts")
//    public ResponseEntity<ResponseDTO> showStockPartsInfo() {
//
//    }



    @Tag(name = "본사 부품 재고정보 조회")
    @GetMapping("company/h-stock/parts")
    public ResponseEntity<ResponseDTO> showAllHStockPartsInfo() {

        List<LaundryPartAndManagementDTO> hPartList = stockService.selectAllHPart();

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", hPartList));

    }



}
