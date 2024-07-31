package aircleanprojectback.restapi.branchOrigin.controller;

import aircleanprojectback.restapi.branch.dto.FacilityDetailDTO;
import aircleanprojectback.restapi.branchOrigin.dto.BranchPageDTO;
import aircleanprojectback.restapi.branchOrigin.service.BranchService;
import aircleanprojectback.restapi.common.dto.ResponseDTO;
import aircleanprojectback.restapi.member.dto.BranchDTO;
import aircleanprojectback.restapi.member.dto.MemberDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/branch")
public class BranchController {

    private final BranchService branchService;

    public BranchController(BranchService branchService){
        this.branchService=branchService;
    }

    @GetMapping("info")
    public ResponseEntity<ResponseDTO> getBranchInfo(){

        List<BranchPageDTO> result = branchService.getBranchInfo();

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"branch 정보 조회 성공 ",result));
    }

    @GetMapping("facility/{branchCode}")
    public ResponseEntity<ResponseDTO> getBranchFacilityInfo(@PathVariable String branchCode){

        System.out.println("getBranchFacilityInfo 동작 : "+branchCode);

        List<FacilityDetailDTO> result  =branchService.findFacilityByBranchCode(branchCode);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"시설 정보 조회 성공",result));
    }

    @DeleteMapping("")
    public ResponseEntity<ResponseDTO> deleteBranch(@RequestBody List<BranchDTO> branch){

        branch.forEach(System.out::println);

        List<String> branchCodeList = new ArrayList<>();

        branch.forEach(b-> branchCodeList.add(b.getBranchCode()));

        branchService.deleteBranch(branchCodeList);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"브랜치 삭제 성공","삭제"));

    }

    @GetMapping("manager")
    public ResponseEntity<ResponseDTO> getManager(){

        System.out.println("getManager 동작");
        List<MemberDTO> memberList = branchService.getManager();

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"지점장 정보조회 성공",memberList));
    }

}
