package aircleanprojectback.restapi.facility.controller;

import aircleanprojectback.restapi.branch.dto.FacilityDetailDTO;
import aircleanprojectback.restapi.common.dto.ResponseDTO;
import aircleanprojectback.restapi.facility.dto.FacilityDetailOnlyDTO;
import aircleanprojectback.restapi.facility.dto.LaundryAndLaundryWayDTO;
import aircleanprojectback.restapi.facility.service.FacilityService;
import aircleanprojectback.restapi.laundry.dto.WaterTankDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "시설물현황 관리", description = "시설물 관리 API 문서화")
@RestController
@Slf4j
@RequestMapping("/location")
public class FacilityController {

    private final FacilityService service;

    public FacilityController(FacilityService service) {
        this.service = service;
    }

    @Operation(summary = "시설물 상세정보 조회", description = "지점코드로 시설물 상세정보 조회하기")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "시설물 조회 성공"),
            @ApiResponse(responseCode = "404", description = "시설물 조회 실패")
    })
    @GetMapping("/facility")
    public ResponseEntity<ResponseDTO> showFacilityDetail(@Parameter(description = "지점 코드") @RequestParam String branchCode) {
        List<FacilityDetailDTO> facilityList = service.findFacilityByBranchCode(branchCode);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"시설물 조회 ",facilityList));
    }

    @Operation(summary = "물탱크 수량 업데이트", description = "시설물 동작 시 세제량, 물탱크 수량 업데이트")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "수량 업데이트 성공"),
            @ApiResponse(responseCode = "400", description = "수량 업데이트 실패")
    })
    @PutMapping("/facility/laundryupdate")
    public ResponseEntity<ResponseDTO> updateCurWater(@RequestBody WaterTankDTO updateTank,
                                                      @Parameter(description = "세제량") @RequestParam String laundryDetergentAmount,
                                                      @Parameter(description = "세탁 코드") @RequestParam String laundryCode) {
        service.saveUpdaterWaterCapacity(updateTank ,laundryDetergentAmount,laundryCode);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "수량 업데이트 완료", "수량 업데이트"));
    }

    @Operation(summary = "건조기 관련 업데이트", description = "지점코드로 건조기 관련 세탁물 정보 업데이트")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "건조기 정보 업데이트 성공"),
            @ApiResponse(responseCode = "400", description = "건조기 정보 업데이트 실패")
    })
    @PutMapping("/facility/dryupdate")
    public ResponseEntity<ResponseDTO> updateDryer(@Parameter(description = "세탁 코드") @RequestParam String laundryCode,
                                                   @Parameter(description = "지점 코드") @RequestParam String branchCode) {
        service.saveDryUpdate(laundryCode, branchCode);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "수량 업데이트 완료", "수량 업데이트"));
    }

    @Operation(summary = "드라이 클리너 관련 업데이트", description = "지점코드로 드라이클리너 관련 세탁물 정보 업데이트")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "드라이 클리너 정보 업데이트 성공"),
            @ApiResponse(responseCode = "400", description = "드라이 클리너 정보 업데이트 실패")
    })
    @PutMapping("/facility/cleanerupdate")
    public ResponseEntity<ResponseDTO> updateCleaner(@Parameter(description = "세탁 코드") @RequestParam String laundryCode,
                                                     @Parameter(description = "지점 코드") @RequestParam String branchCode) {
        service.saveCleanerUpdate(laundryCode, branchCode);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "수량 업데이트 완료", "수량 업데이트"));
    }

    @Operation(summary = "시설물 등록하기", description = "신규 시설물 등록")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "시설물 등록 성공"),
            @ApiResponse(responseCode = "400", description = "시설물 등록 실패")
    })
    @PostMapping("/facility/register")
    public ResponseEntity<ResponseDTO> registFacility(@RequestBody FacilityDetailOnlyDTO request) {
        service.saveRegisterFacility(request);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "시설물 등록 완료", "등록 완료"));
    }

    @Operation(summary = "시설물 상태 업데이트", description = "시설물 상태 고장 또는 삭제로 업데이트")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "시설물 상태 업데이트 성공"),
            @ApiResponse(responseCode = "400", description = "시설물 상태 업데이트 실패")
    })
    @PutMapping("/facility/update")
    public ResponseEntity<ResponseDTO> updateFacility(@RequestBody FacilityDetailOnlyDTO request) {
        service.updateFacilityStatus(request);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "시설물 업데이트 완료", "업데이트 완료"));
    }

    @Operation(summary = "시설물 도출된 세탁 정보 조회하기", description = "세탁물관리에서 도출된 세탁 정보 조회하기")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "세탁 정보 조회 성공"),
            @ApiResponse(responseCode = "404", description = "세탁 정보 조회 실패")
    })
    @GetMapping("/facility/laundryWay")
    public ResponseEntity<ResponseDTO> getLaundryWay(@Parameter(description = "지점 코드") @RequestParam String branchCode) {
        List<LaundryAndLaundryWayDTO> laundryWayList = service.findMyLaundryWay(branchCode);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "시설 도출된 세탁정보 조회 완료", laundryWayList));
    }
}
