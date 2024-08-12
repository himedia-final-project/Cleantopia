package aircleanprojectback.restapi.laundry.controller;

import aircleanprojectback.restapi.common.dto.ResponseDTO;
import aircleanprojectback.restapi.laundry.Message.ResponseMessage;
import aircleanprojectback.restapi.laundry.dto.LaundryDTO;
import aircleanprojectback.restapi.laundry.dto.WaterTankDTO;
import aircleanprojectback.restapi.laundry.service.ManagementService;
import aircleanprojectback.restapi.water.dto.WaterSupplyDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/management")
@Tag(name = "세탁물 관리 API")
public class ManagementController {

    private final ManagementService managementService;

    public ManagementController(ManagementService managementService) {
        this.managementService = managementService;
    }

    @Tag(name = "수질 탱크 조회" ,description = "세탁물 및 수질 관리 관련 API")
    @GetMapping("/waterTank")
    public ResponseEntity<ResponseMessage> waterTank() {
        List<WaterTankDTO> waterTankList = managementService.waterTankList();

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("waterTank", waterTankList);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        return ResponseEntity.ok()
                .headers(headers)
                .body(new ResponseMessage(200, "waterTank 조회 성공", responseMap));
    }

    @Tag(name = "수질 일지 조회", description = "작성한 수질 일지 조회")
    @GetMapping("/waterSupply/{branchCode}")
    public ResponseEntity<ResponseMessage> waterSupply(@PathVariable String branchCode) {
        List<WaterSupplyDTO> waterSupplyList = managementService.SelectWaterSupply(branchCode);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("waterSupply", waterSupplyList);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        return ResponseEntity.ok()
                .headers(headers)
                .body(new ResponseMessage(200, "waterSupply 조회 성공", responseMap));
    }

    @Tag(name = "세탁물 더미 조회", description = "고객에게 받은 세탁물 조회")
    @GetMapping("/selectLaundry/{branchCode}")
    public ResponseEntity<ResponseMessage> selectLaundry(@PathVariable String branchCode) {
        List<LaundryDTO> selectLandryList = managementService.selectLaundry(branchCode);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("selectLandry", selectLandryList);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        return ResponseEntity.ok()
                .headers(headers)
                .body(new ResponseMessage(200, "laundry 조회 성공", responseMap));
    }

    @Tag(name = "도착한 세탁물 조회", description = "고객에게서 지점으로 도착한 세탁물 조회")
    @GetMapping("arrivedLaundry/{branchCode}")
    public ResponseEntity<ResponseDTO> getLaundryArrived(@PathVariable String branchCode) {
        List<LaundryDTO> arrivedLaundryList = managementService.getLaundryArrived(branchCode);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "도착한 세탁물", arrivedLaundryList));
    }

    @Tag(name = "세탁물 상태 업데이트", description = "승낙 버튼 누를시 세탁 상태 변경")
    @PutMapping("/updateLaundryStatus")
    public ResponseEntity<ResponseMessage> updateLaundryStatus(@RequestBody Map<String, Object> payload) {
        int laundryCode = (int) payload.get("laundryCode");
        String statusType = (String) payload.get("statusType");
        String statusValue = (String) payload.get("statusValue");

        boolean updateSuccess = managementService.updateLaundryStatus(laundryCode, statusType, statusValue);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("updateSuccess", updateSuccess);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        if (updateSuccess) {
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(new ResponseMessage(200, "laundry 상태 업데이트 성공", responseMap));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .headers(headers)
                    .body(new ResponseMessage(500, "laundry 상태 업데이트 실패", responseMap));
        }
    }
}
