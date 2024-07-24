package aircleanprojectback.restapi.report.controller;

import aircleanprojectback.restapi.common.dto.ResponseDTO;
import aircleanprojectback.restapi.report.dto.VehicleRepairDTO;
import aircleanprojectback.restapi.report.service.VehicleRepairService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paper")
@Slf4j
public class VehicleRepairController {
    // 본사 차량 수리 보고서

    private final VehicleRepairService vehicleRepairService;
    public VehicleRepairController(VehicleRepairService vehicleRepairService) {this.vehicleRepairService = vehicleRepairService;}

    // 본사 차량보고서 전체조회
    @GetMapping("/company/newReports")
    public ResponseEntity<ResponseDTO> selectAllVehicleRepairs() {

        List<VehicleRepairDTO> vehicleRepair = vehicleRepairService.getAllVehicleRepair();

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "차량수리보고서 전체 조회성공", vehicleRepair));
    }

    // 차량수리비 세부조회
    @GetMapping("/company/detailVehicleRepair/{vehicleReportCode}")
    public ResponseEntity<ResponseDTO> selectVehicleRepairByVehicleReportCode(@PathVariable int vehicleReportCode) {

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "차량수리보고서 상세조회", vehicleRepairService.detailVehicleRepair(vehicleReportCode)));
    }

    // 본사 차량보고서 작성
    @PostMapping("/company/newReports")
    public ResponseEntity<ResponseDTO> newVehicleReports(@RequestBody VehicleRepairDTO vehicleRepairDTO){

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "차량수리비보고서 등록완료", vehicleRepairService.insertVehicleRepair(vehicleRepairDTO)));

    }



}
