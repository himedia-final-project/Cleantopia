package aircleanprojectback.restapi.laundry.controller;


import aircleanprojectback.restapi.laundry.Message.ResponseMessage;
import aircleanprojectback.restapi.laundry.dto.LaundryDTO;
import aircleanprojectback.restapi.laundry.dto.LaundryWayDTO;
import aircleanprojectback.restapi.laundry.entity.LaundryWay;
import aircleanprojectback.restapi.laundry.service.RegistrationService;
import aircleanprojectback.restapi.water.dto.WaterSupplyDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    // 세탁 방법 도출 하기
    @PostMapping("registLaundryWay")
    public ResponseEntity<ResponseMessage> updateLaundryStatus(@RequestBody Map<String, Object> payload) throws IOException {

        System.out.println("여기를 확인해주세요");
        System.out.println("payload 너 뭐야?"+payload);



        List<LaundryWayDTO> insertSuccess = registrationService.registLaundryWay(payload);


        Map<String, Object> responseMap = new HashMap<>();
//        responseMap.put("insertSuccess", insertSuccess);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        return ResponseEntity.ok()
                .headers(headers)
                .body(new ResponseMessage(200, "세탁 방법 도출 성공", responseMap));
    }

    // 도출된 세탁방법 조회
    @GetMapping("/selectLaundryWay/{branchCode}")
    public ResponseEntity<ResponseMessage> selectLaundryWay(@PathVariable String branchCode) {


        List<LaundryWay> laundryWayList = registrationService.SelectLaundryWay(branchCode);

//        System.out.println(laundryWayList);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("laundryWayList", laundryWayList);


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));


        return ResponseEntity.ok()
                .headers(headers)
                .body(new ResponseMessage(200, "laundryWayList 조회 성공", responseMap));
    }



}
