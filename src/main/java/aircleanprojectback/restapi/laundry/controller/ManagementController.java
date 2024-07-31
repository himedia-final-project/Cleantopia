package aircleanprojectback.restapi.laundry.controller;

import aircleanprojectback.restapi.laundry.Message.ResponseMessage;
import aircleanprojectback.restapi.laundry.dto.WaterTankDTO;
import aircleanprojectback.restapi.laundry.service.ManagementService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


        System.out.println("responseMap = " + responseMap);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));


        return ResponseEntity.ok()
                .headers(headers)
                .body(new ResponseMessage(200, "BranchList 조회 성공", responseMap));
    }




}
