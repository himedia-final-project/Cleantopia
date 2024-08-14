package aircleanprojectback.restapi.member.controller;


import aircleanprojectback.restapi.car.dto.DriverDTO;
import aircleanprojectback.restapi.common.dto.Criteria;
import aircleanprojectback.restapi.common.dto.PageDTO;
import aircleanprojectback.restapi.common.dto.PagingResponseDTO;
import aircleanprojectback.restapi.common.dto.ResponseDTO;
import aircleanprojectback.restapi.member.dto.*;
import aircleanprojectback.restapi.member.service.HumanResourceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/members")
@Slf4j
@Tag(name = "인적 자원 관리 api", description = "인적 자원 관련 데이터 컨트롤러")
public class HumanResourceController {

    private final HumanResourceService service;
    private final ObjectMapper objectMapper;

    @Autowired
    public HumanResourceController(HumanResourceService service,ObjectMapper objectMapper){
        this.service = service;
        this.objectMapper=objectMapper;
    }
    // 조회
    @GetMapping("/employee")
    @Operation(summary = "직원 조회", description = "직원 데이터 조회")
    public ResponseEntity<ResponseDTO> findEmployee(@RequestParam(defaultValue = "1") String offset){

        System.out.println("findEmployee 동작합니다");

        System.out.println("offset = " + offset);

        Criteria cri = new Criteria(Integer.parseInt(offset),12);

        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();

        Page<MembersAndEmployeeDTO> employeeList = service.getEmployeeListWithPaging(cri);

        System.out.println("employeeList = " + employeeList.getContent());

        pagingResponseDTO.setData(employeeList);


        pagingResponseDTO.setPageInfo(new PageDTO(cri,(int)employeeList.getTotalElements()));


        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"직원 조회 성공",pagingResponseDTO));
    }

    @GetMapping("branch")
    @Operation(summary = "지점장 조회",description = "지점장 데이터 조회")
    public ResponseEntity<ResponseDTO> findBranch(@RequestParam(defaultValue = "1")String offset ){

        System.out.println("findBranch 동작");
        Criteria cri = new Criteria(Integer.parseInt(offset),12);

        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();

        Page<BranchOwnerDTO> branchList = service.getBranchAndMemberWithPage(cri);

        pagingResponseDTO.setPageInfo(new PageDTO(cri, (int) branchList.getTotalElements()));
        pagingResponseDTO.setData(branchList);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "지점장 조회 성공",pagingResponseDTO));

    }


    @GetMapping("driver")
    @Operation(summary = "차량기사 조회", description = "차량기사 데이터 조회")
    public ResponseEntity<ResponseDTO> findDriver(@RequestParam(defaultValue = "1") String offset){

        Criteria cri = new Criteria(Integer.parseInt(offset),12);

        Page<MemberAndDriverDTO> memberList = service.findAllDriver(cri);

        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
        pagingResponseDTO.setPageInfo(new PageDTO(cri, (int) memberList.getTotalElements()));
        pagingResponseDTO.setData(memberList);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"driver 정보",pagingResponseDTO));
    }

    @GetMapping("employee/search")
    public ResponseEntity<ResponseDTO> findEmployeeById(@RequestBody SearchDTO searchDTO){

        PagingResponseDTO pagingResponseDTO= new PagingResponseDTO();
        Page<MembersAndEmployeeDTO> employeeList = null;


        switch (searchDTO.getCategory()){
            case "name":

                break;
            case "dept":

                break;
            case "position":

                break;
        }
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"일단 들어옴",searchDTO));
    }

    @GetMapping("branch/{memberId}")
    public ResponseEntity<ResponseDTO> findBranchId(@PathVariable String memberId){
        return null;
    }

    @GetMapping("driver/{memberId}")
    public ResponseEntity<ResponseDTO> findDriverById(@PathVariable String memberId){
        return null;
    }

    // 등록
    @PostMapping("employee")
    @Operation(summary = "본사 직원 등록", description = "본사 데이터 입력")
    public ResponseEntity<ResponseDTO> registEmployee(@ModelAttribute MemberDTO memberDTO, @ModelAttribute EmployeeDTO employeeDTO, MultipartFile image){

        System.out.println("memberDTO = " + memberDTO);
        System.out.println("employeeDTO = " + employeeDTO);

        memberDTO.setMemberStatus("Y");
        memberDTO.setMemberRole("e");

        service.registEmployee(memberDTO,employeeDTO,image);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"등록 성공",memberDTO));
    }
    @PostMapping("branch")
    @Operation(summary = "지점장 등록", description = "지점장 데이터 입력")
    public ResponseEntity<ResponseDTO> registBranch(@ModelAttribute RegistBranchManagerDTO memberDTO,MultipartFile image){

        System.out.println("memberDTO = " + memberDTO);

        if(memberDTO.getBranchCode() == null || memberDTO.getBranchCode().isEmpty() ){
            memberDTO.setBranchOwnership("N");
        }else{
            memberDTO.setBranchOwnership("Y");
        }

        memberDTO.setMemberStatus("Y");
        memberDTO.setMemberRole("b");

        service.registBranch(memberDTO,image);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"등록 성공",memberDTO));

    }
    @PostMapping("driver")
    @Operation(summary = "차량 기사 등록", description = "차량기사 데이터 입력")
    public ResponseEntity<ResponseDTO> registDriver(@ModelAttribute MemberDTO memberDTO , @ModelAttribute DriverDTO driverDTO , MultipartFile image){




        service.registDriver(memberDTO,driverDTO,image);
        
        return null;
    }

    // 수정
    @PutMapping("employee/{employeeCode}")
    @Operation(summary = "직원 정보 수정",description = "입력 받은 직원 정보 수정")
    public ResponseEntity<ResponseDTO> modifyEmployee(@PathVariable int employeeCode, @ModelAttribute MemberModifyDTO memberModifyDTO,MultipartFile image){

        System.out.println("employeeCode = " + employeeCode);
        System.out.println("memberModifyDTO = " + memberModifyDTO);

        service.modifyEmployeeInfo(employeeCode,memberModifyDTO,image);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"일단 들어옴",memberModifyDTO));
    }
    @PutMapping("branch/{memberId}")
    @Operation(summary = "지점장 정보 수정",description = "입력 받은 지점장 정보 수정")
    public ResponseEntity<ResponseDTO> modifyBranch(@PathVariable String memberId, @ModelAttribute MemberModifyDTO memberModifyDTO,MultipartFile image){

        System.out.println("memberId = " + memberId);
        System.out.println("memberModifyDTO = " + memberModifyDTO);

        service.modifyBranchManagerInfo(memberId,memberModifyDTO,image);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"지점장 정보 수정","수정 성공"));
    }
    @PutMapping("driver/{memberId}")
    @Operation(summary = "차량기사 정보 수정",description = "입력받은 차량기사 정보 수정")
    public ResponseEntity<ResponseDTO> modifyDriver( @PathVariable String memberId, @ModelAttribute MemberModifyDTO memberModifyDTO, MultipartFile image){
        System.out.println("memberId = " + memberId);
        System.out.println("memberModifyDTO = " + memberModifyDTO);


        service.modifyDriverInfo(memberId,memberModifyDTO,image);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"차량기사 정보 수정","수정 성공"));
    }

    // softDelete
    @PutMapping("/employee/soft-delete")
    @Operation(summary = "직원 임시 삭제",description = "직원 임시 삭제")
    public ResponseEntity<ResponseDTO> softDeleteEmployee(@RequestBody List<String> deleteMember) {


        for(String m : deleteMember){
            System.out.println("m = " + m);
        }

        service.findEmployeeById(deleteMember);


        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"soft delete 수행",deleteMember));
    }

    @PutMapping("branch/soft-delete")
    @Operation(summary = "지점장 임시 삭제",description = "지점장 임시 삭제")
    public ResponseEntity<ResponseDTO> softDeleteBranch(@RequestBody List<String> deleteMember){

        service.findBranchManagerByMemberId(deleteMember);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"soft delete 수행",deleteMember));
    }
    @PutMapping("driver/soft-delete")
    @Operation(summary = "차량기사 임시 삭제" , description = "차량 기사 임시 삭제")
    public ResponseEntity<ResponseDTO> softDeleteDriver(@RequestBody List<String> deleteMember){

        service.findDriverById(deleteMember);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"soft delete 수행",deleteMember));
    }


    // 삭제가능 회원 조회
    @GetMapping("employee/unstatus")
    @Operation(summary = "임시 삭제 직원 조회")
    public ResponseEntity<ResponseDTO> findMemberEmployeeWithN(@RequestParam(defaultValue = "1")String offset, @RequestParam(defaultValue = "10")String amount){

        System.out.println("offset = " + offset);
        System.out.println("amount = " + amount);


        Criteria cri = new Criteria(Integer.parseInt(offset),Integer.parseInt(amount));

        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();

        Page<MembersAndEmployeeDTO> employeeList = service.getEmployeeWhereN(cri);

        System.out.println("employeeList = " + employeeList.getContent());

        pagingResponseDTO.setData(employeeList);

        pagingResponseDTO.setPageInfo(new PageDTO(cri,(int)employeeList.getTotalElements()));



        return  ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"일단 들어옴",pagingResponseDTO));
    }

    @GetMapping("branch/unstatus")
    @Operation(summary = "임시 삭제 지점장 조회")
    public ResponseEntity<ResponseDTO> findMemberBranchWithN(@RequestParam(defaultValue = "1")String offset, @RequestParam(defaultValue = "10")String amount){

        System.out.println("offset = " + offset);
        System.out.println("amount = " + amount);


        Criteria cri = new Criteria(Integer.parseInt(offset),Integer.parseInt(amount));

        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();

        Page<MemberDTO> branchManagerList = service.getBranchWithN(cri);

        System.out.println("branchMangerList = " + branchManagerList.getContent());

        pagingResponseDTO.setData(branchManagerList);

        pagingResponseDTO.setPageInfo(new PageDTO(cri,(int)branchManagerList.getTotalElements()));



        return  ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"일단 들어옴",pagingResponseDTO));
    }

    @GetMapping("driver/unstatus")
    @Operation(summary = "임시 삭제 차량기사 조회")
    public ResponseEntity<ResponseDTO> findMemberDriverWithN(@RequestParam(defaultValue = "1")String offset, @RequestParam(defaultValue = "10")String amount){

        System.out.println("offset = " + offset);
        System.out.println("amount = " + amount);


        Criteria cri = new Criteria(Integer.parseInt(offset),Integer.parseInt(amount));

        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();

        Page<MemberAndDriverDTO> driverList = service.getDriverWithN(cri);

        System.out.println("branchMangerList = " + driverList.getContent());

        pagingResponseDTO.setData(driverList);

        pagingResponseDTO.setPageInfo(new PageDTO(cri,(int)driverList.getTotalElements()));



        return  ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"일단 들어옴",pagingResponseDTO));
    }

    //회원 삭제
    @DeleteMapping("/employee")
    @Operation(summary = "직원 완전 삭제")
    public ResponseEntity<ResponseDTO> deleteEmployee(@RequestBody List<String> memberId){

        memberId.forEach(System.out::println);



        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"삭제 성공","간디"));
    }



    // branch without owner

    @GetMapping("branch/no-owner")
    @Operation(summary = "지점 부여 받지 못한 지점장 검색")
    public ResponseEntity<ResponseDTO> getBranchWithoutOwner(){

        List<BranchDTO> branchList = service.findAllBranchWithoutOwner();

        branchList.forEach(System.out::println);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"owner 없는 branch",branchList));
    }



    // 지점장 로그인시 branch 정보
    @GetMapping("loging/{memberId}")
    @Operation(summary = "지점장 로그인 시 지점장 정보", description = "지점장이 로그인시 지점의 정보를 제공합니다")
    public ResponseEntity<ResponseDTO> getBranchInfo(@PathVariable String memberId){

        System.out.println("memberId = " + memberId);

        BranchOwnerDTO branchOwnerDTO = service.findOwnBranchByMemberId(memberId);

        System.out.println("branchOwnerDTO = " + branchOwnerDTO);
        if(Objects.isNull(branchOwnerDTO.getBranchDTO())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(HttpStatus.NOT_FOUND,"지점이 존재하지 않음",null));
        };

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"지점장에게 브랜치 정보 제공",branchOwnerDTO.getBranchDTO()));

    }

    @GetMapping("driver/count")
    @Operation(summary = "지역별 차량기사",description = "지역별 차량기사의 정보를 제공합니다")
    public ResponseEntity<ResponseDTO> getBranchCount(){
        System.out.println("getBranchCount");

        List<DriverCountDTO> driverCountDTOS = service.findDriverRegionCount();

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"지역 조회",driverCountDTOS));
    }



}
