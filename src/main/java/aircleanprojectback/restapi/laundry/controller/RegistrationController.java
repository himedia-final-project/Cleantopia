package aircleanprojectback.restapi.laundry.controller;


import aircleanprojectback.restapi.laundry.Message.ResponseMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/registration")
public class RegistrationController {


    @PostMapping("registLaundryWay")
    public ResponseEntity<ResponseMessage> updateLaundryStatus(@RequestBody Map<String, Object> payload) {

        System.out.println("여기를 확인해주세요");
        System.out.println("payload 너 뭐야?"+payload);
        

        Map<String, Object> responseMap = new HashMap<>();
//        responseMap.put("updateSuccess", updateSuccess);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        return ResponseEntity.ok()
                .headers(headers)
                .body(new ResponseMessage(200, "세탁 방법 도출 성공", responseMap));
    }



}
