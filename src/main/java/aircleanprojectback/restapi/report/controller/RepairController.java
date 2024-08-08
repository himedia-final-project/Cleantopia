package aircleanprojectback.restapi.report.controller;

import aircleanprojectback.restapi.common.dto.Criteria;
import aircleanprojectback.restapi.common.dto.ResponseDTO;
import aircleanprojectback.restapi.report.dto.BranchSalesDTO;
import aircleanprojectback.restapi.report.dto.RepairDTO;
import aircleanprojectback.restapi.report.entity.Repair;
import aircleanprojectback.restapi.report.service.RepairService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<ResponseDTO> AllFindRepair(@RequestParam(defaultValue = "1") String offset) {

        Criteria repairCriteria = new Criteria();
        repairCriteria.setPageNum(Integer.parseInt(offset));
        repairCriteria.setAmount(6);
        Page<RepairDTO> repairDTO = repairService.AllFindRepair(repairCriteria);


        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "지점 수리보고서 전체 조회", repairDTO));
    }

    // 시설물수리보고서 필터링 조회
    @GetMapping("/location/repair")
    public ResponseEntity<ResponseDTO> repairMemberName(@RequestParam(defaultValue = "1") String offset,
                                                        @RequestParam String memberName) {
        Criteria repairCriteriaMemberName = new Criteria();
        repairCriteriaMemberName.setPageNum(Integer.parseInt(offset));
        repairCriteriaMemberName.setAmount(6);
        Page<RepairDTO> repairMemberName = repairService.findRepairMemberName(repairCriteriaMemberName,memberName);

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK,"필터링조회 완료", repairMemberName));

    }

    // 지점 수리보고서 세부조회
    @GetMapping("/company/repair/{repairReportCode}")
    public ResponseEntity<ResponseDTO> FindRepairByReportCode(@PathVariable int repairReportCode) {
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK,"지점 수리보고서 세부조회",repairService.FindRepairByReportCode(repairReportCode)));
    }

    //지점 수리보고서 등록
    @PostMapping("/location/newRepair")
    public ResponseEntity<ResponseDTO> NewRepair(@ModelAttribute RepairDTO repairDTO, MultipartFile repairImage) {

        System.out.println("repairDTO = " + repairDTO);
        System.out.println("repairImage = " + repairImage);
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK,"지점 수리보고서 등록 성공", repairService.insertRepair(repairDTO,repairImage)));
    }


    // 시설물수리보고서 상태 - 승인
    @PutMapping("/company/reports/repairApprove/{repairReportCode}")
    public ResponseEntity<ResponseDTO> updateRepairApprove(@PathVariable int repairReportCode){
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "시설물수리보고서 승인 완료",repairService.updateRepairStatus(repairReportCode,"Y", "Y")));
    }
    // 시설물수리보고서 상태 - 반려
    @PutMapping("/company/reports/repairReject/{repairReportCode}")
    public ResponseEntity<ResponseDTO> updateRepairReject(@PathVariable int repairReportCode){
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK,"시설물수리보고서 반려 완료", repairService.updateRepairStatus(repairReportCode,"R", "N")));
    }

    // 시설물 수리보고서 수정
    @PutMapping("/location/repair/{repairReportCode}")
    public ResponseEntity<ResponseDTO> UpdateRepair(@PathVariable int repairReportCode,
                                                    @ModelAttribute RepairDTO repairDTO,
                                                    MultipartFile repairImages) {

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "지점 수리보고서 수정 성공적", repairService.updateRepair(repairReportCode,repairDTO,repairImages)));
    }


    // 시설물수리보고서 삭제
    @DeleteMapping("/location/repair/{repairReportCode}")
    public ResponseEntity<ResponseDTO> DeleteRepair(@PathVariable int repairReportCode) {
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK,"지점 수리보고서 삭제 성공", repairService.deleteRepair(repairReportCode)));
    }




}