package aircleanprojectback.restapi.mainpage.controller;

import aircleanprojectback.restapi.common.dto.ResponseDTO;
import aircleanprojectback.restapi.mainpage.model.service.MainService;
import aircleanprojectback.restapi.member.dto.BranchDTO;
import aircleanprojectback.restapi.report.dto.ExpenseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/main")
public class MainController {

    private final MainService service;

    public MainController(MainService service){
        this.service =service;
    }

    @GetMapping("/branch")
    public ResponseEntity<ResponseDTO> getBranch(){

        List<BranchDTO> branchList = service.getBranch();

        branchList.forEach(System.out::println);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"브랜치 조회 성공",branchList));
    }

    @GetMapping("utilityCost")
    public ResponseEntity<ResponseDTO> getUtilityCost(@RequestParam String month ){

        List<ExpenseDTO> expenseDTOS = service.getUtilityCost(month);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"수도광열비 제공",expenseDTOS));
    }

  


}
