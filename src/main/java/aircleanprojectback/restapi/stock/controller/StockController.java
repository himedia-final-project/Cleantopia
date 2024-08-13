package aircleanprojectback.restapi.stock.controller;

import aircleanprojectback.restapi.common.dto.ResponseDTO;
import aircleanprojectback.restapi.stock.dto.*;
import aircleanprojectback.restapi.stock.service.StockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "재고관리", description = "재고 관리 관련 API를 제공합니다.")
@RestController
@Slf4j
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @Operation(summary = "세탁용품 재고 정보 조회", description = "본사 세탁용품 재고 정보를 전체 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LaundrySupplyAndManagementDTO.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping("/company/stock/detergents")
    public ResponseEntity<ResponseDTO> showDetergentsInfo(
            @Parameter(description = "멤버의 역할 정보가 포함된 맵", required = true)
            @RequestParam Map<String, String> members) {

        log.info("membersSubDTO = {}", members);

        String memberRole = members.get("memberRole");

        log.info("memberRole = {}", memberRole);

        List<LaundrySupplyAndManagementDTO> detergentsStockInfo = stockService.getDetergentsStockInfo(memberRole);

        log.info("detergentsStockInfo = {}", detergentsStockInfo);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", detergentsStockInfo));
    }

    @Operation(summary = "부품 재고 정보 조회", description = "본사 세탁부품 재고 정보를 전체 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LaundryPartAndManagementDTO.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping("/company/stock/parts")
    public ResponseEntity<ResponseDTO> showPartsInfo(
            @Parameter(description = "멤버의 역할 정보가 포함된 맵", required = true)
            @RequestParam Map<String, String> members) {

        log.info("members = {}", members);

        String memberRole = members.get("memberRole");

        List<LaundryPartAndManagementDTO> partsStockInfo = stockService.getPartsStockInfo(memberRole);

        log.info("partsStockInfo = {}", partsStockInfo);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", partsStockInfo));
    }

    @Operation(summary = "본사 재고 신청", description = "본사에서 재고 신청서 작성 시 신청서 DB 업데이트를 수행합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "신청 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = HeadStockApplicationDTO.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("/company/stock/application")
    public ResponseEntity<ResponseDTO> headStockApplication(
            @Parameter(description = "본사 재고 신청서 정보", required = true)
            @RequestBody HeadStockApplicationDTO headStockApplicationDTO) {

        log.info("headStockApplicationDTO = {}", headStockApplicationDTO);

        HeadStockApplicationDTO headStockApplication = stockService.saveHeadStockApplication(headStockApplicationDTO);




        System.out.println("신청내역 확인 DTO");

        log.info("신청내역 확인 DTO");


        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "신청 성공", headStockApplication));
    }

    @Operation(summary = "지점 발주 신청", description = "지점에서 발주 신청서 작성 시 신청서 DB 업데이트를 수행합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "신청 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BranchStockApplicationDTO.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("/client/stock/application")
    public ResponseEntity<ResponseDTO> branchStockApplication(
            @Parameter(description = "지점 발주 신청서 정보", required = true)
            @RequestBody BranchStockApplicationDTO branchStockApplicationDTO) {

        log.info("branchStockApplicationDTO = {}", branchStockApplicationDTO);

        BranchStockApplicationDTO branchStockApplication = stockService.saveBranchStockApplication(branchStockApplicationDTO);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "지점 발주 신청 완료", branchStockApplication));
    }

    @Operation(summary = "본사 재고 업데이트", description = "본사에서 재고 신청 시 전체 재고 업데이트를 수행합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "업데이트 성공",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PutMapping("/company/stock/update")
    public ResponseEntity<ResponseDTO> headStockUpdate(
            @Parameter(description = "본사 재고 업데이트 정보", required = true)
            @RequestBody HeadStockUpdate request) {

        log.info("request = {}", request);
        stockService.updateStock(request);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "재고 업데이트 성공", "headStockUpdated"));
    }

    @Operation(summary = "본사 재고 신청내역 조회", description = "본사에서 재고 신청 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "내역 조회 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = HeadStockApplicationDTO.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping("/company/stock/application")
    public ResponseEntity<ResponseDTO> headStockHistory() {

        log.info("controller 들어옴");

        List<HeadStockApplicationDTO> headStockApplicationDTO = stockService.getHeadStockApplicationHistory();

        log.info("headStockApplicationDTO = {}", headStockApplicationDTO);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "내역 조회 성공", headStockApplicationDTO));
    }

    @Operation(summary = "본사 지점 발주내역 조회", description = "본사에서 지점의 발주내역을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BranchStockApplicationDTO.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping("/company/stock/branchApplication")
    public ResponseEntity<ResponseDTO> headBranchApplication() {

        List<BranchStockApplicationDTO> branchStockApplicationDTO = stockService.getHeadBranchStockApplication();

        log.info("branchStockApplicationDTO = {}", branchStockApplicationDTO);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", branchStockApplicationDTO));
    }

    @Operation(summary = "본사 지점발주내역 승인 및 본사 재고 업데이트", description = "본사에서 지점의 발주를 승인 시 상태 변경 및 본사 재고 업데이트를 수행합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "업데이트 성공",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PutMapping("/company/stock/branchStockUpdate")
    public ResponseEntity<ResponseDTO> headBranchStockUpdate(
            @Parameter(description = "본사 지점 발주 업데이트 정보", required = true)
            @RequestBody HeadStockUpdate request) {


        log.info("업데이트 정보 request = {}", request);
        stockService.updateHeadBranchStock(request);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "발주 관련 본사 재고 업데이트 성공", "headStockUpdated"));

    }

    @Operation(summary = "본사 지점발주신청 정보 업데이트", description = "본사에서 지점 발주신청 관련 정보(승인자, 승인일, 승인상태)를 업데이트합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "업데이트 성공",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PutMapping("/company/stock/branchApplication/{bApplicationCode}")
    public ResponseEntity<ResponseDTO> headBranchStatusUpdate(
            @Parameter(description = "지점 발주 신청 코드", required = true)
            @PathVariable int bApplicationCode,
            @Parameter(description = "지점 발주 신청 정보", required = true)
            @RequestBody BranchStockApplicationDTO request) {

        System.out.println("bApplicationCode = " + bApplicationCode);
        System.out.println("request = " + request);

        stockService.updateHeadBranchStockApplication(bApplicationCode, request);

        log.info("발주신청 업데이트 정보 확인 = {}", request);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "업데이트 성공", "발주 신청 상태 업데이트 성공"));
    }

    @Operation(summary = "지점 신청내역 정보 조회", description = "지점에서 발주내역을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "내역 조회 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BranchStockApplicationDTO.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping("/client/stock/branchHistory")
    public ResponseEntity<ResponseDTO> specificBranchHistory(
            @Parameter(description = "지점 코드", required = true)
            @RequestParam String branchCode) {

        List<BranchStockApplicationDTO> branchStockApplicationDTO = stockService.getBranchStockHistory(branchCode);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "특정 지점 신청내역 조회 성공", branchStockApplicationDTO));
    }

    @Operation(summary = "지점 배송완료 상태 업데이트", description = "지점에서 배송완료 선택 시 상태를 업데이트합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "업데이트 성공",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PutMapping("/client/stock/deliveryUpdate")
    public ResponseEntity<ResponseDTO> branchDeliveryUpdate(
            @Parameter(description = "지점 배송 완료 상태 정보", required = true)
            @RequestBody BranchStockApplicationDTO branchStockApplicationDTO) {

        log.info("branchStockApplicationDTO = {}", branchStockApplicationDTO);

        stockService.updateBranchAppDeliveryStatus(branchStockApplicationDTO);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "배송 상태 업데이트 성공", "배송 상태 업데이트 성공"));
    }

    @Operation(summary = "지점 세탁용품 재고 업데이트", description = "지점에서 배송완료 선택 시 지점 세탁용품 재고를 업데이트합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "업데이트 성공",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("/client/stock/updateLaundrySupplyManagement")
    public ResponseEntity<ResponseDTO> branchSupplyUpdate(
            @Parameter(description = "지점 세탁용품 재고 업데이트 정보", required = true)
            @RequestBody BranchStockUpdate request) {

        log.info("세탁용품 request = {}", request);

        stockService.updateBranchSupply(request);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "지점 세탁용품 재고 업데이트 성공", "지점 세탁용품 업데이트 성공"));
    }

    @Operation(summary = "지점 세탁부품 재고 업데이트", description = "지점에서 배송완료 선택 시 지점 세탁부품 재고를 업데이트합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "업데이트 성공",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("/client/stock/updateLaundryPartManagement")
    public ResponseEntity<ResponseDTO> branchPartUpdate(
            @Parameter(description = "지점 세탁부품 재고 업데이트 정보", required = true)
            @RequestBody BranchStockUpdate request) {

        log.info("세탁부품 request = {}", request);

        stockService.updateBranchPart(request);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "지점 세탁부품 재고 업데이트 성공", "지점 세탁부품 업데이트 성공"));
    }

}
