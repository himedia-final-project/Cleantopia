package aircleanprojectback.restapi.stock.controller;

import aircleanprojectback.restapi.common.dto.ResponseDTO;
import aircleanprojectback.restapi.stock.dto.*;
import aircleanprojectback.restapi.stock.entity.BranchStockApplication;
import aircleanprojectback.restapi.stock.entity.HeadStockApplication;
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
        
        HeadStockApplicationDTO headStockApplication = stockService.saveHeadStockApplication(headStockApplicationDTO);

        System.out.println("신청내역 확인 DTO");

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"들어옴","headStockApplication"));
    }

    @Tag(name = "본사 재고 업데이트")
    @PutMapping("/company/stock/update")
    public ResponseEntity<ResponseDTO> headStockUpdate(@RequestBody HeadStockUpdate request) {

        System.out.println("request = " + request);
        stockService.updateStock(request);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "재고업데이트성공", "headStockUpdated"));

    }

    @Tag(name = "본사 재고 신청내역 조회")
    @GetMapping("/company/stock/application")
    public ResponseEntity<ResponseDTO> headStockHistory() {

        System.out.println("controller 들어옴");

        List<HeadStockApplicationDTO> headStockApplicationDTO = stockService.getHeadStockApplicationHistory();

        System.out.println("headStockApplicationDTO = " + headStockApplicationDTO);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "내역조회 성공", headStockApplicationDTO));
    }

    @Tag(name = "본사 지점발주내역 조회")
    @GetMapping("/company/stock/branchApplication")
    public ResponseEntity<ResponseDTO> headBranchApplication() {

        List<BranchStockApplicationDTO> branchStockApplicationDTO = stockService.getHeadBranchStockApplication();

        System.out.println("branchStockApplicationDTO" + branchStockApplicationDTO);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", branchStockApplicationDTO));

    }

    @Tag(name = "본사 지점발주내역 승인 및 본사 재고 업데이트")
    @PutMapping("/company/stock/branchStockUpdate")
    public ResponseEntity<ResponseDTO> headBranchStockUpdate(@RequestBody HeadStockUpdate request) {

        System.out.println("업데이트 정보 request = " + request);
        stockService.updateHeadBranchStock(request);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "발주관련 본사 재고 업데이트 성공", "headStockUpdated"));

    }

    @Tag(name = "본사 지점발주신청 정보 업데이트")
    @PutMapping("/company/stock/branchApplication/{bApplicationCode}")
    public ResponseEntity<ResponseDTO> headBranchStatusUpdate(@PathVariable int bApplicationCode, @RequestBody BranchStockApplicationDTO request) {

        stockService.updateHeadBranchStockApplication(bApplicationCode, request);

        System.out.println("발주신청 업데이트 정보 확인" + request);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "업데이트 성공", "발주신청 상태 업데이트 성공"));

    }





}