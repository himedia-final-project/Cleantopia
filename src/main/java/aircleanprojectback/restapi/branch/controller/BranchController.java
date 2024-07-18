//package aircleanprojectback.restapi.branch.controller;
//
//import aircleanprojectback.restapi.branch.Message.ResponseMessage;
//import aircleanprojectback.restapi.branch.dto.BranchDTO;
//import aircleanprojectback.restapi.branch.service.BranchService;
//import aircleanprojectback.restapi.member.service.MemberService;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.nio.charset.Charset;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/branch")
//public class BranchController {
//
//
//    private final BranchService branchService;
//
//    public BranchController(BranchService branchService) {
//        this.branchService = branchService;
//    }
//
//
//    @GetMapping("/branchList")
//    public ResponseEntity<ResponseMessage> selectAll(){
//
//        List<String> branchList = branchService.branchList();
//
//
//        Map<String, Object> responseMap = new HashMap<>();
//        responseMap.put("branchList", branchList);
//
//        System.out.println(responseMap.get("branchList"));
//
////        해더 타입
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
//
//
//        return ResponseEntity.ok()
//                .headers(headers)
//                .body(new ResponseMessage(200, "BranchList 조회 성공", responseMap));
//
//    }
//
////    @GetMapping("/branchList")
////    public ResponseEntity<ResponseMessage> selectAll(
////            @RequestParam("component") String component,
////            @RequestParam("sub") String sub) {
////
////        //        react 에서 받아온 유저 정보
////        String member_id = sub;
////        System.out.println(member_id);
////
////        List<BranchDTO> branchList = branchService.branchList(member_id);
////        System.out.println(branchList);
////
////        Map<String, Object> responseMap = new HashMap<>();
////        responseMap.put("branchList", branchList);
////
////
//////        해더 타입
////        HttpHeaders headers = new HttpHeaders();
////        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
////
////
////        if ("BranchList".equals(component)) {
//////            responseMap = getBranchListData();
////            return ResponseEntity.ok()
////                    .headers(headers)
////                    .body(new ResponseMessage(200, "BranchList 조회 성공", responseMap));
////        } else {
////            responseMap.put("error", "Invalid component");
////            return ResponseEntity.badRequest()
////                    .headers(headers)
////                    .body(new ResponseMessage(400, "실패", responseMap));
////        }
////    }
//}
