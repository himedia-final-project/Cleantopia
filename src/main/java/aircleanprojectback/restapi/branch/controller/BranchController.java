package aircleanprojectback.restapi.branch.controller;

import aircleanprojectback.restapi.branch.Message.ResponseMessage;
import aircleanprojectback.restapi.branch.dto.BranchDTO;
import aircleanprojectback.restapi.branch.service.BranchService;
import aircleanprojectback.restapi.member.service.MemberService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/branch")
public class BranchController {


    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }


    @GetMapping("/mapCounts")
    public ResponseEntity<ResponseMessage> mapCounts(){

        Map<String, Integer> mapCounts = branchService.mapCounts();
        System.out.println(mapCounts);
        
        Map<String, Integer> responseMap = new HashMap<>();
        responseMap.put("중앙", mapCounts.getOrDefault("중앙", 0));
        responseMap.put("북부", mapCounts.getOrDefault("북부", 0));
        responseMap.put("동부", mapCounts.getOrDefault("동부", 0));
        responseMap.put("서부", mapCounts.getOrDefault("서부", 0));
        responseMap.put("남부", mapCounts.getOrDefault("남부", 0));

        System.out.println("responseMap = " + responseMap);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        return ResponseEntity.ok()
                .headers(headers)
                .body(new ResponseMessage(200, "BranchList 조회 성공", responseMap));
    }


    @GetMapping("/branchList")
    public ResponseEntity<ResponseMessage> selectAll(@RequestParam(required = false) String locationName) {
        List<String> branchList = branchService.branchList(locationName);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("branchList", branchList);


        // 해더 타입 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        return ResponseEntity.ok()
                .headers(headers)
                .body(new ResponseMessage(200, "BranchList 조회 성공", responseMap));
    }



//    기본동작 정보 메소드
    @GetMapping("/defaltBranchInformation")
    public ResponseEntity<ResponseMessage> defaltSelectBranchInformation(
            @RequestParam("sub") String sub) {

        //        react 에서 받아온 유저 정보
        String member_id = sub;
        System.out.println(member_id);

        List<BranchDTO> branchList = branchService.defaltBranchinformation(member_id);
        System.out.println(branchList);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("branchList", branchList);


//        해더 타입
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));


        return ResponseEntity.ok()
                .headers(headers)
                .body(new ResponseMessage(200, "BranchList 조회 성공", responseMap));
    }


    // 버튼 클릭 정보
    @GetMapping("/branchInformation")
    public ResponseEntity<ResponseMessage> selectBranchInformation(
            @RequestParam("sub") String sub,
            @RequestParam("branchName") String branchName) {

        // react 에서 받아온 유저 정보
        String member_id = sub;
        System.out.println(member_id);

        List<BranchDTO> branchList = branchService.branchInformation(branchName);
        System.out.println(branchList);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("branchList", branchList);

        // 해더 타입
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        return ResponseEntity.ok()
                .headers(headers)
                .body(new ResponseMessage(200, "BranchList 조회 성공", responseMap));
    }
}
