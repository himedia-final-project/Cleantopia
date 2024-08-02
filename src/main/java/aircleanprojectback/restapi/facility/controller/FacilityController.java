package aircleanprojectback.restapi.facility.controller;
//
//import aircleanprojectback.restapi.branch.dto.FacilityDetailDTO;
//import aircleanprojectback.restapi.branch.entity.FacilityDetail;
import aircleanprojectback.restapi.branch.dto.FacilityDetailDTO;
import aircleanprojectback.restapi.common.dto.ResponseDTO;
//import aircleanprojectback.restapi.facility.service.FacilityService;
import aircleanprojectback.restapi.facility.service.FacilityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//
import java.util.List;
//
@Tag(name = "재고관리 스웨거 연동")
@RestController
@Slf4j
@RequestMapping("/location")
public class FacilityController {
//
//    private final FacilityService facilityService;
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

}
