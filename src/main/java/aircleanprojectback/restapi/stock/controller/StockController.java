package aircleanprojectback.restapi.stock.controller;

import aircleanprojectback.restapi.common.dto.ResponseDTO;
import aircleanprojectback.restapi.stock.dto.HeadStockApplicationDTO;
import aircleanprojectback.restapi.stock.dto.LaundryPartAndManagementDTO;
import aircleanprojectback.restapi.stock.dto.LaundrySupplyAndManagementDTO;
import aircleanprojectback.restapi.stock.dto.MembersSubDTO;
import aircleanprojectback.restapi.stock.entity.LaundryPartAndManagement;
import aircleanprojectback.restapi.stock.entity.LaundrySupplyAndManagement;
import aircleanprojectback.restapi.stock.service.StockService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @Tag(name = "세탁용품 재고 정보 조회")
    @GetMapping("/company/stock/detergents")
    public ResponseEntity<ResponseDTO> showDetergentsInfo(@RequestParam Map<String,String> members) {

        System.out.println("membersSubDTO = " + members);

        String memberRole = members.get("memberRole");

        System.out.println("memberRole = " + memberRole);

        List<LaundrySupplyAndManagementDTO> detergentsStockInfo = stockService.getDetergentsStockInfo(memberRole);

        System.out.println("detergentsStockInfo = " + detergentsStockInfo);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", detergentsStockInfo));
    }

    @Tag(name = "부품 재고 정보 조회")
    @GetMapping("/company/stock/parts")
    public ResponseEntity<ResponseDTO> showPartsInfo(@RequestParam Map<String, String> members) {

        System.out.println("members = " + members);

        String memberRole = members.get("memberRole");

        List<LaundryPartAndManagementDTO> partsStockInfo = stockService.getPartsStockInfo(memberRole);

        System.out.println("partsStockInfo = " + partsStockInfo);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", partsStockInfo));
    }

    @Tag(name = "본사 재고 신청")
    @PostMapping("/company/stock/application")
    public ResponseEntity<ResponseDTO> headStockApplication(@RequestBody HeadStockApplicationDTO headStockApplicationDTO) {

        System.out.println("headStockApplicationDTO = " + headStockApplicationDTO);
        
//        HeadStockApplicationDTO headStockApplication = stockService.saveHeadStockApplication(headStockApplicationDTO);

//        System.out.println("신청내역 확인 DTO= " + headStockApplication);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"들어옴","headStockApplication"));
    }



}
