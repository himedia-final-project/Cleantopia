package aircleanprojectback.restapi.laundry.controller;

import aircleanprojectback.restapi.common.dto.ResponseDTO;
import aircleanprojectback.restapi.laundry.Message.ResponseMessage;
import aircleanprojectback.restapi.laundry.dto.LaundryDTO;
import aircleanprojectback.restapi.laundry.dto.WaterTankDTO;
import aircleanprojectback.restapi.laundry.service.ManagementService;
import aircleanprojectback.restapi.water.dto.WaterSupplyDTO;
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
public class ManagementController {

    private final ManagementService managementService;

    public ManagementController(ManagementService managementService) {
        this.managementService = managementService;
    }

    @GetMapping("/waterTank")
    public ResponseEntity<ResponseMessage> waterTank() {

        List<WaterTankDTO> waterTankList = managementService.waterTankList();

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("waterTank", waterTankList);


//        System.out.println("responseMap = " + responseMap);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));


        return ResponseEntity.ok()
                .headers(headers)
                .body(new ResponseMessage(200, "BranchList 조회 성공", responseMap));
    }

    @GetMapping("/waterSupply/{branchCode}")
    public ResponseEntity<ResponseMessage> waterSupply(@PathVariable String branchCode) {

        List<WaterSupplyDTO> waterSupplyList = managementService.SelectWaterSupply(branchCode);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("waterSupply", waterSupplyList);


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));


        return ResponseEntity.ok()
                .headers(headers)
                .body(new ResponseMessage(200, "BranchList 조회 성공", responseMap));
    }

    @GetMapping("/selectLaundry/{branchCode}")
    public ResponseEntity<ResponseMessage> selectLaundry(@PathVariable String branchCode) {

//        System.out.println("너 실행되니?");
//        System.out.println(branchCode);
//        List<WaterSupplyDTO> waterSupplyList = managementService.SelectWaterSupply(branchCode);
        List<LaundryDTO> selectLandryList = managementService.selectLaundry(branchCode);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("selectLandry", selectLandryList);

//        System.out.println("여기확인");
//        System.out.println(branchCode);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));


        return ResponseEntity.ok()
                .headers(headers)
                .body(new ResponseMessage(200, "laundry 조회 성공", responseMap));
    }

    @GetMapping("arrivedLaundry/{branchCode}")
    public ResponseEntity<ResponseDTO> getLaundryArrived(@PathVariable String branchCode){

        List<LaundryDTO> arrivedLaundryList = managementService.getLaundryArrived(branchCode);

        System.out.println("arrivedLaundryList = " + arrivedLaundryList);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"도착한 세탁물 ",arrivedLaundryList));
    }

    @PutMapping("/updateLaundryStatus")
    public ResponseEntity<ResponseMessage> updateLaundryStatus(@RequestBody Map<String, Object> payload) {
        int laundryCode = (int) payload.get("laundryCode");
        String statusType = (String) payload.get("statusType");
        String statusValue = (String) payload.get("statusValue");

        System.out.println("Updating laundry status:");
        System.out.println("Laundry Code: " + laundryCode);
        System.out.println("Status Type: " + statusType);
        System.out.println("Status Value: " + statusValue);

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
