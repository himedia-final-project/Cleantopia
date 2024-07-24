package aircleanprojectback.restapi.report.controller;

import aircleanprojectback.restapi.common.dto.ResponseDTO;
import aircleanprojectback.restapi.report.dto.RepairDTO;
import aircleanprojectback.restapi.report.service.RepairService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paper")
@Slf4j
public class RepairController {
    // 지점 시설물 수리보고서

    private final RepairService repairService;
    public RepairController(RepairService repairService) {
        this.repairService = repairService;
    }

    // 지점 시설물 수리보고서 전체 조회
    @GetMapping("/company/repair")
    public ResponseEntity<ResponseDTO> AllFindRepair() {

        List<RepairDTO> repair = repairService.AllFindRepair();
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "지점 수리보고서 전체 조회", repair));
    }

    // 지점 수리보고서 세부조회
    @GetMapping("/company/repair/{repairReportCode}")
    public ResponseEntity<ResponseDTO> FindRepairByReportCode(@PathVariable int repairReportCode) {
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK,"지점 수리보고서 세부조회",repairService.FindRepairByReportCode(repairReportCode)));
    }

    //지점 수리보고서 등록
    @PostMapping("/location/newRepair")
    public ResponseEntity<ResponseDTO> NewRepair(@RequestBody RepairDTO repairDTO) {
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK,"지점 수리보고서 등록 성공", repairService.insertRepair(repairDTO)));
    }

    // 지점 수리보고서 수정
    @PutMapping("/location/repair/{repairReportCode}")
    public ResponseEntity<ResponseDTO> UpdateRepair(@PathVariable int repairReportCode, @RequestBody RepairDTO repairDTO) {

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "지점 수리보고서 수정 성공적", repairService.updateRepair(repairReportCode,repairDTO)));
    }

    // 지점 수리보고서 삭제
    @DeleteMapping("/location/repair/{repairReportCode}")
    public ResponseEntity<ResponseDTO> DeleteRepair(@PathVariable int repairReportCode) {
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK,"지점 수리보고서 삭제 성공", repairService.deleteRepair(repairReportCode)));
    }




}
