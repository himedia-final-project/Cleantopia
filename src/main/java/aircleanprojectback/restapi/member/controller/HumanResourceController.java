package aircleanprojectback.restapi.member.controller;


import aircleanprojectback.restapi.common.dto.Criteria;
import aircleanprojectback.restapi.common.dto.PageDTO;
import aircleanprojectback.restapi.common.dto.PagingResponseDTO;
import aircleanprojectback.restapi.common.dto.ResponseDTO;
import aircleanprojectback.restapi.member.dto.DeleteMemberDTO;
import aircleanprojectback.restapi.member.dto.MembersAndEmployeeDTO;
import aircleanprojectback.restapi.member.service.HumanResourceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
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
    public ResponseEntity<ResponseDTO> findBranch(){
        return null;
    }

    @GetMapping("driver")
    public ResponseEntity<ResponseDTO> findDriver(){
        return null;
    }

    @GetMapping("employee/{memberId}")
    public ResponseEntity<ResponseDTO> findEmployeeById(@PathVariable String memberId){
        return null;
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
    public ResponseEntity<ResponseDTO> registEmployee(/*@RequestBody*/){
        return null;
    }
    @PostMapping("branch")
    public ResponseEntity<ResponseDTO> registBranch(/*@RequestBody*/){
        return null;
    }
    @PostMapping("driver")
    public ResponseEntity<ResponseDTO> registDriver(/*@RequestBody*/){
        return null;
    }

    // 수정
    @PutMapping("employee/{memberId}")
    public ResponseEntity<ResponseDTO> modifyEmployee(/*@RequestBody*/@PathVariable String memberId){
        return null;
    }
    @PutMapping("branch/{memberId}")
    public ResponseEntity<ResponseDTO> modifyBranch(/*@RequestBody*/@PathVariable String memberId){
        return null;
    }
    @PutMapping("driver/{memberId}")
    public ResponseEntity<ResponseDTO> modifyDriver(/*@RequestBody*/ @PathVariable String memberId){
        return null;
    }

    // softDelete
    @PutMapping("/employee/soft")
    public ResponseEntity<ResponseDTO> softDeleteEmployee(@RequestBody DeleteMemberDTO deleteMemberDTO) {


        System.out.println("delete = " + deleteMemberDTO);

        System.out.println("들어오냐");


        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"일단 들어옴","간디"));
    }

    @PutMapping("branch/soft-delete/")
    public ResponseEntity<ResponseDTO> softDeleteBranch(@PathVariable String[] members){
        return null;
    }
    @PutMapping("driver/soft-delete/")
    public ResponseEntity<ResponseDTO> softDeleteDriver(@PathVariable String[] members){
        return null;
    }





}
