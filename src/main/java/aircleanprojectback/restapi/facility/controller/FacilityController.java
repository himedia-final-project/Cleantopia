package aircleanprojectback.restapi.facility.controller;
//
//import aircleanprojectback.restapi.branch.dto.FacilityDetailDTO;
//import aircleanprojectback.restapi.branch.entity.FacilityDetail;
import aircleanprojectback.restapi.branch.dto.FacilityDetailDTO;
import aircleanprojectback.restapi.common.dto.ResponseDTO;
//import aircleanprojectback.restapi.facility.service.FacilityService;
import aircleanprojectback.restapi.facility.service.FacilityService;
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
@Tag(name = "재고관리 스웨거 연동")
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
    @Tag(name = "시설물 상세정보 조회")
    @GetMapping("/facility")
    public ResponseEntity<ResponseDTO> showFacilityDetail(@RequestParam String branchCode) {

        System.out.println("branchCode = " + branchCode);
        List<FacilityDetailDTO> facilityList = service.findFacilityByBranchCode(branchCode);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"시설물 조회 ",facilityList));
    }

    @Tag(name = "물탱크 수량 업데이트")
    @PutMapping("/facility/waterTankUpdate")
    public ResponseEntity<ResponseDTO> updateCurWater(@RequestBody WaterTankDTO waterTankDTO) {

        System.out.println("waterTankDTO" + waterTankDTO);

        service.saveUpdaterWaterCapacity(waterTankDTO);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "수량 업데이트 완료", "수량 업데이트"));

    }



}
