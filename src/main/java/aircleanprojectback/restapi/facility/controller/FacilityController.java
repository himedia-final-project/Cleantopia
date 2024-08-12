package aircleanprojectback.restapi.facility.controller;
//
//import aircleanprojectback.restapi.branch.dto.FacilityDetailDTO;
//import aircleanprojectback.restapi.branch.entity.FacilityDetail;
import aircleanprojectback.restapi.branch.dto.FacilityDetailDTO;
import aircleanprojectback.restapi.common.dto.ResponseDTO;
//import aircleanprojectback.restapi.facility.service.FacilityService;
import aircleanprojectback.restapi.facility.dto.FacilityDetailOnlyDTO;
import aircleanprojectback.restapi.facility.dto.LaundryAndLaundryWayDTO;
import aircleanprojectback.restapi.facility.service.FacilityService;
import aircleanprojectback.restapi.laundry.dto.LaundryWayDTO;
import aircleanprojectback.restapi.laundry.dto.WaterTankDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//
import java.util.List;
//
@Tag(name = "시설물현황 스웨거 연동")
@RestController
@Slf4j
@RequestMapping("/location")
public class FacilityController {

    private final FacilityService service;
//
    public FacilityController(FacilityService service) {
        this.service = service;
    }
//
    @Tag(name = "시설물 상세정보 조회", description = "지점코드로 시설물 상세정보 조회하기")
    @GetMapping("/facility")
    public ResponseEntity<ResponseDTO> showFacilityDetail(@RequestParam String branchCode) {

        System.out.println("branchCode = " + branchCode);
        List<FacilityDetailDTO> facilityList = service.findFacilityByBranchCode(branchCode);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"시설물 조회 ",facilityList));
    }

    @Tag(name = "물탱크 수량 업데이트", description = "시설물 동작 시 세제량, 물탱크 수량 업데이트")
    @PutMapping("/facility/laundryupdate")
    public ResponseEntity<ResponseDTO> updateCurWater(@RequestBody WaterTankDTO updateTank ,@RequestParam String laundryDetergentAmount,@RequestParam String laundryCode) {

        System.out.println("waterTankDTO" + updateTank);

        System.out.println("laundryCode = " + laundryCode);

        System.out.println("laundryDetergentAmount = " + laundryDetergentAmount);

        service.saveUpdaterWaterCapacity(updateTank ,laundryDetergentAmount,laundryCode);




        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "수량 업데이트 완료", "수량 업데이트"));

    }

    @Tag(name = "건조기 관련 업데이트", description = "지점코드로 건조기 관련 세탁물 정보 업데이트")
    @PutMapping("/facility/dryupdate")
    public ResponseEntity<ResponseDTO> updateDryer(@RequestParam String laundryCode,@RequestParam String branchCode) {



        System.out.println("laundryCode = " + laundryCode);



        service.saveDryUpdate(laundryCode,branchCode);




        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "수량 업데이트 완료", "수량 업데이트"));

    }

    @Tag(name = "드라이 클리너 관련 업데이트", description = "지점코드로 드라이클리너 관련 세탁물 정보 업데이트")
    @PutMapping("/facility/cleanerupdate")
    public ResponseEntity<ResponseDTO> updateCleaner(@RequestParam String laundryCode,@RequestParam String branchCode) {

        System.out.println("laundryCode = " + laundryCode);

        service.saveCleanerUpdate(laundryCode,branchCode);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "수량 업데이트 완료", "수량 업데이트"));

    }

    @Tag(name = "시설물 등록하기", description = "신규 시설물 등록")
    @PostMapping("/facility/register")
    public ResponseEntity<ResponseDTO> registFacility(@RequestBody FacilityDetailOnlyDTO request) {

        System.out.println("시설물 등록 request" + request);

        service.saveRegisterFacility(request);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "시설물 등록 완료", "등록 완료"));

    }

    @Tag(name = "시설물 상태 업데이트", description = "시설물 상태 고장 또는 삭제로 업데이트")
    @PutMapping("/facility/update")
    public ResponseEntity<ResponseDTO> updateFacility(@RequestBody FacilityDetailOnlyDTO request) {

        System.out.println("시설물 업데이트 request" + request);

        service.updateFacilityStatus(request);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "시설물 업데이트 완료", "업데이트 완료"));

    }

    @Tag(name = "시설물 도출된 세탁 정보 조회하기", description = "세탁물관리에서 도출된 세탁 정보 조회하기")
    @GetMapping("/facility/laundryWay")
    public ResponseEntity<ResponseDTO> getLaundryWay(@RequestParam String branchCode) {

        List<LaundryAndLaundryWayDTO> laundryWayList = service.findMyLaundryWay(branchCode);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "시설 도출된 세탁정보 조회 완료", laundryWayList));

    }

}
