package aircleanprojectback.restapi.member.controller;


import aircleanprojectback.restapi.common.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class HumanResourceController {

    // 조회
    @GetMapping
    public ResponseEntity<ResponseDTO> findEmployee(){
        return null;
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
    @PutMapping("employee/soft-delete/{members}")
    public ResponseEntity<ResponseDTO> softDeleteEmployee(@PathVariable String[] members){
        return null;
    }
    @PutMapping("branch/soft-delete/{members}")
    public ResponseEntity<ResponseDTO> softDeleteBranch(@PathVariable String[] members){
        return null;
    }
    @PutMapping("driver/soft-delete/{members}")
    public ResponseEntity<ResponseDTO> softDeleteDriver(@PathVariable String[] members){
        return null;
    }





}
