package aircleanprojectback.restapi.branch.controller;

import aircleanprojectback.restapi.branch.Message.Message;
import aircleanprojectback.restapi.branch.Message.ResponseMessage;
import aircleanprojectback.restapi.branch.dto.BranchDTO;
import aircleanprojectback.restapi.branch.service.BranchService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
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

    @DeleteMapping("/deleteBranches")
    public ResponseEntity<Message> deleteBranches(@RequestBody Map<String, List<String>> request) {
        // branches 리스트에 대한 삭제 로직을 구현합니다.
        List<String> branches = request.get("branches");
        System.out.println(branches);

        branchService.deleteBranchs(branches);

        // 해더 타입 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        

        // 삭제가 성공적으로 완료되면 성공 메시지를 반환합니다.
        return ResponseEntity.ok()
                .headers(headers)
                .body(new Message(200, "삭제 성공"));
    }

    @PostMapping("/register")
    public ResponseEntity<Message> registerBranch(
            @ModelAttribute BranchDTO branchDTO,
            @RequestParam(value = "branchImageFile", required = false) MultipartFile branchImageFile) {
        try {
            System.out.println("Received branchDTO: " + branchDTO);

            if (branchImageFile != null) {
                System.out.println("Received branchImageFile: " + branchImageFile.getOriginalFilename());

                branchDTO.setBranchImage(branchImageFile.getOriginalFilename());
                branchDTO.setBranchImageFile(branchImageFile);
            } else {
                System.out.println("branchImageFile is null");
            }

            branchService.saveBranch(branchDTO);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(new Message(200, "등록 성공"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .body(new Message(500, "등록 실패: " + e.getMessage()));
        }
    }

    


}
