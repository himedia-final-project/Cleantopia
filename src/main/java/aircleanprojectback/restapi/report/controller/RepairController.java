package aircleanprojectback.restapi.report.controller;

import aircleanprojectback.restapi.common.dto.Criteria;
import aircleanprojectback.restapi.common.dto.ResponseDTO;
import aircleanprojectback.restapi.report.dto.RepairDTO;
import aircleanprojectback.restapi.report.service.RepairService;
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

@RestController
@RequestMapping("/paper")
@Slf4j
@Tag(name = "지점 시설물수리보고서", description = "지점 시설물 수리보고서 관리 API")
public class RepairController {

    private final RepairService repairService;

    public RepairController(RepairService repairService) {
        this.repairService = repairService;
    }

    @Operation(summary = "전지점 시설물 수리보고서 전체 조회", description = "모든 지점 시설물 수리보고서를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    })
    @GetMapping("/company/repair")
    public ResponseEntity<ResponseDTO> AllFindRepair(
            @Parameter(description = "페이지 번호", example = "1")
            @RequestParam(defaultValue = "1") String offset) {

        Criteria repairCriteria = new Criteria();
        repairCriteria.setPageNum(Integer.parseInt(offset));
        repairCriteria.setAmount(6);
        Page<RepairDTO> repairDTO = repairService.AllFindRepair(repairCriteria);

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "지점 수리보고서 전체 조회", repairDTO));
    }

    @Operation(summary = "시설물 수리보고서 지점장 필터링 조회", description = "지점장 이름 으로 시설물 수리보고서를 필터링하여 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "필터링 조회 완료",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    })
    @GetMapping("/location/repair")
    public ResponseEntity<ResponseDTO> repairMemberName(
            @Parameter(description = "페이지 번호", example = "1")
            @RequestParam(defaultValue = "1") String offset,
            @Parameter(description = "멤버 이름", example = "John Doe")
            @RequestParam String memberName) {
        Criteria repairCriteriaMemberName = new Criteria();
        repairCriteriaMemberName.setPageNum(Integer.parseInt(offset));
        repairCriteriaMemberName.setAmount(6);
        Page<RepairDTO> repairMemberName = repairService.findRepairMemberName(repairCriteriaMemberName, memberName);

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "필터링 조회 완료", repairMemberName));
    }

    @Operation(summary = "지점 수리보고서 세부 조회", description = "지점 수리보고서 코드를 사용하여 세부 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "세부 조회 성공",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "보고서 없음",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    })
    @GetMapping("/company/repair/{repairReportCode}")
    public ResponseEntity<ResponseDTO> FindRepairByReportCode(
            @Parameter(description = "수리보고서 코드", example = "123")
            @PathVariable int repairReportCode) {
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "지점 수리보고서 세부 조회", repairService.FindRepairByReportCode(repairReportCode)));
    }

    @Operation(summary = "지점 수리보고서 등록", description = "새로운 지점 수리보고서를 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "등록 성공",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    })
    @PostMapping("/company/repair")
    public ResponseEntity<ResponseDTO> NewRepair(
            @Parameter(description = "수리보고서 데이터", required = false)
            @ModelAttribute RepairDTO repairDTO,
            @Parameter(description = "수리 이미지 파일", required = false)
            @RequestParam(required = false)MultipartFile repairImage) {

        System.out.println("repairDTO = " + repairDTO);
        System.out.println("repairImage = " + repairImage);
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "지점 수리보고서 등록 성공", repairService.insertRepair(repairDTO, repairImage)));
    }

    @Operation(summary = "시설물 수리보고서 승인", description = "시설물 수리보고서 상태를 승인으로 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "상태 수정 성공",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    })
    @PutMapping("/company/reports/repairApprove/{repairReportCode}")
    public ResponseEntity<ResponseDTO> updateRepairApprove(
            @Parameter(description = "수리보고서 코드", example = "123")
            @PathVariable int repairReportCode) {
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "시설물 수리보고서 승인 완료", repairService.updateRepairStatus(repairReportCode, "Y", "Y")));
    }

    @Operation(summary = "시설물 수리보고서 반려", description = "시설물 수리보고서 상태를 반려로 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "상태 수정 성공",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    })
    @PutMapping("/company/reports/repairReject/{repairReportCode}")
    public ResponseEntity<ResponseDTO> updateRepairReject(
            @Parameter(description = "수리보고서 코드", example = "123")
            @PathVariable int repairReportCode) {
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "시설물 수리보고서 반려 완료", repairService.updateRepairStatus(repairReportCode, "R", "N")));
    }

    @Operation(summary = "시설물 수리보고서 수정", description = "기존 시설물 수리보고서를 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "수정 성공",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    })
    @PutMapping("/location/repair/{repairReportCode}")
    public ResponseEntity<ResponseDTO> UpdateRepair(
            @Parameter(description = "수리보고서 코드", example = "123")
            @PathVariable int repairReportCode,
            @Parameter(description = "수정할 수리보고서 데이터", required = false)
            @ModelAttribute RepairDTO repairDTO,
            @Parameter(description = "수리 이미지 파일", required = false)
            @RequestParam  (required = false) MultipartFile repairImages) {

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "지점 수리보고서 수정 성공", repairService.updateRepair(repairReportCode, repairDTO, repairImages)));
    }

    @Operation(summary = "시설물 수리보고서 삭제", description = "기존 시설물 수리보고서를 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "삭제 성공",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    })
    @DeleteMapping("/company/repair/{repairReportCode}")
    public ResponseEntity<ResponseDTO> DeleteRepair(
            @Parameter(description = "수리보고서 코드", example = "123")
            @PathVariable int repairReportCode) {
        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK, "지점 수리보고서 삭제 성공", repairService.deleteRepair(repairReportCode)));
    }
}
