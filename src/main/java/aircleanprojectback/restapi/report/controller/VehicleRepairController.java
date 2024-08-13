package aircleanprojectback.restapi.report.controller;

import aircleanprojectback.restapi.common.dto.Criteria;
import aircleanprojectback.restapi.common.dto.ResponseDTO;
import aircleanprojectback.restapi.report.dto.CarMembersDTO;
import aircleanprojectback.restapi.report.dto.VehicleRepairDTO;
import aircleanprojectback.restapi.report.service.CarMembersService;
import aircleanprojectback.restapi.report.service.VehicleRepairService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/paper")
@Slf4j
@Tag(name = "차량수리 보고서", description = "본사 차량 수리 보고서 관리 API")
public class VehicleRepairController {

    private final VehicleRepairService vehicleRepairService;
    private final CarMembersService service;

    public VehicleRepairController(VehicleRepairService vehicleRepairService, CarMembersService service) {
        this.vehicleRepairService = vehicleRepairService;
        this.service = service;
    }

    @Operation(summary = "본사 차량보고서 전체 조회", description = "모든 차량 수리보고서를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    })
    @GetMapping("/company/vehicle-repair")
    public ResponseEntity<ResponseDTO> selectAllVehicleRepairs(
            @Parameter(description = "페이지 번호", example = "1")
            @RequestParam(defaultValue = "1") String offset) {

        Criteria vehicleRepairCriteria = new Criteria();
        vehicleRepairCriteria.setPageNum(Integer.parseInt(offset));
        vehicleRepairCriteria.setAmount(6);
        Page<VehicleRepairDTO> vehicleRepairDTO = vehicleRepairService.getAllVehicleRepair(vehicleRepairCriteria);

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "차량 수리보고서 전체 조회 성공", vehicleRepairDTO));
    }

    @Operation(summary = "차량수리비 세부 조회", description = "차량수리보고서 코드를 사용하여 상세 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "상세 조회 성공",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "보고서 없음",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    })
    @GetMapping("/company/detailVehicleRepair/{vehicleReportCode}")
    public ResponseEntity<ResponseDTO> selectVehicleRepairByVehicleReportCode(
            @Parameter(description = "차량수리보고서 코드", example = "123")
            @PathVariable int vehicleReportCode) {

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "차량수리보고서 상세 조회", vehicleRepairService.detailVehicleRepair(vehicleReportCode)));
    }

    @Operation(summary = "차량 번호 및 차량 기사 조회", description = "모든 차량 번호 및 차량 기사를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    })
    @GetMapping("driver/numbers")
    public ResponseEntity<ResponseDTO> selectAllDriverNumbers() {
        List<CarMembersDTO> carMembersList = service.findCarMembers();
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "차량 기사 번호 조회", carMembersList));
    }

    @Operation(summary = "차량수리비보고서 승인", description = "차량수리비보고서 상태를 승인으로 변경합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "상태 수정 성공",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    })
    @PutMapping("/company/reports/vehicleRepairApprove/{vehicleReportCode}")
    public ResponseEntity<ResponseDTO> updateVehicleRepairApprove(
            @Parameter(description = "차량수리보고서 코드", example = "123")
            @PathVariable int vehicleReportCode) {
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "차량수리비보고서 상태 수정 승인 완료", vehicleRepairService.updateVehicleRepairStatus(vehicleReportCode, "Y", "Y")));
    }

    @Operation(summary = "차량수리비보고서 반려", description = "차량수리비보고서 상태를 반려로 변경합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "상태 수정 성공",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    })
    @PutMapping("/company/reports/vehicleRepairReject/{vehicleReportCode}")
    public ResponseEntity<ResponseDTO> updateVehicleRepairReject(
            @Parameter(description = "차량수리보고서 코드", example = "123")
            @PathVariable int vehicleReportCode) {
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "차량수리비보고서 상태 수정 반려 완료", vehicleRepairService.updateVehicleRepairStatus(vehicleReportCode, "R", "N")));
    }

    @Operation(summary = "차량수리비보고서 등록", description = "새로운 차량수리비보고서를 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "등록 성공",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    })
    @PostMapping("/company/vehicle-repair")
    public ResponseEntity<ResponseDTO> insertVehicleRepair(
            @Parameter(description = "차량수리비보고서 데이터", required = false)
            @ModelAttribute VehicleRepairDTO vehicleRepairDTO,
            @Parameter(description = "수리 전 이미지 파일", required = false)
            @RequestParam(required = false) MultipartFile beforeImage,
            @Parameter(description = "수리 후 이미지 파일", required = false)
            @RequestParam(required = false) MultipartFile afterImage) {

        System.out.println("vehicleRepairDTO = " + vehicleRepairDTO);
        System.out.println("beforeVehicleRepairImage = " + beforeImage);
        System.out.println("afterVehicleRepairImage = " + afterImage);

        log.info("vehicleRepairDTO = {}", vehicleRepairDTO);
        log.info("beforeVehicleRepairImage = {}", beforeImage);
        log.info("afterVehicleRepairImage = {}", afterImage);

        vehicleRepairService.insertVehicleReports(vehicleRepairDTO, beforeImage, afterImage);
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "등록 성공", vehicleRepairDTO));
    }



}
