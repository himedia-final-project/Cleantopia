package aircleanprojectback.restapi.laundry.controller;

import aircleanprojectback.restapi.laundry.Message.ResponseMessage;
import aircleanprojectback.restapi.laundry.dto.LaundryDTO;
import aircleanprojectback.restapi.laundry.dto.LaundryWayDTO;
import aircleanprojectback.restapi.laundry.entity.LaundryWay;
import aircleanprojectback.restapi.laundry.service.RegistrationService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "세탁 방법 등록 및 조회 API", description = "세탁 방법 등록 및 조회 관련 API")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @Tag(name = "세탁 방법 도출")
    @PostMapping("registLaundryWay")
    public ResponseEntity<ResponseMessage> updateLaundryStatus(@RequestBody Map<String, Object> payload) throws IOException {

        List<LaundryWayDTO> insertSuccess = registrationService.registLaundryWay(payload);

        Map<String, Object> responseMap = new HashMap<>();
        // responseMap.put("insertSuccess", insertSuccess);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        return ResponseEntity.ok()
                .headers(headers)
                .body(new ResponseMessage(200, "세탁 방법 도출 성공", responseMap));
    }

    @Tag(name = "도출된 세탁 방법 조회")
    @GetMapping("/selectLaundryWay/{branchCode}")
    public ResponseEntity<ResponseMessage> selectLaundryWay(@PathVariable String branchCode) {

        List<LaundryWay> laundryWayList = registrationService.SelectLaundryWay(branchCode);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("laundryWayList", laundryWayList);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        return ResponseEntity.ok()
                .headers(headers)
                .body(new ResponseMessage(200, "laundryWayList 조회 성공", responseMap));
    }
}
