package aircleanprojectback.restapi.mainpage.controller;

import aircleanprojectback.restapi.common.dto.ResponseDTO;
import aircleanprojectback.restapi.mainpage.model.dto.ExpenseAndBranchDTO;
import aircleanprojectback.restapi.mainpage.model.dto.RankingDTO;
import aircleanprojectback.restapi.mainpage.model.service.MainService;
import aircleanprojectback.restapi.member.dto.BranchDTO;
import aircleanprojectback.restapi.report.dto.ExpenseDTO;
import aircleanprojectback.restapi.stock.entity.StockExpense;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/main")
@Slf4j
@Tag(name = "메인페이지", description = "메인페이지 관리 api")
public class MainController {

    private final MainService service;

    public MainController(MainService service){
        this.service =service;
    }

    @GetMapping("/branch")
    @Operation(summary = "지점정보 가지고 오기", description = "모든 지점코드를 가지고 옵니다")
    public ResponseEntity<ResponseDTO> getBranch(){

        List<BranchDTO> branchList = service.getBranch();

        branchList.forEach(System.out::println);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"브랜치 조회 성공",branchList));
    }

    @GetMapping("revenue/{branchCode}")
    @Operation(summary = "매출 정보 api" , description = "선택한 지점의 매출 데이터를 가지고 옵니다")
    public ResponseEntity<ResponseDTO> getRevenue(@PathVariable String branchCode, @RequestParam String month){

        System.out.println("매출 정보 api ddkdkddk");
        System.out.println("branchCode = " + branchCode);
        System.out.println("month = " + month);

        String year = month.split("-")[0];
        month = month.split("-")[1];



        return  ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"매출 조회",service.getRevenue(branchCode , month, year)));
    }

    @GetMapping("utility/{branchCode}")
    @Operation(summary = "수도광열비 api" ,description = "선택한 날짜와 지점의 수도광열비를 가지고 옵니다")
    public ResponseEntity<ResponseDTO> getUtilityCost(@RequestParam String date, @PathVariable String branchCode ){



        List<ExpenseAndBranchDTO> expenseDTOS = service.getUtilityCost(date,branchCode);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"수도광열비 제공",expenseDTOS));
    }

    @GetMapping("maintenance/{type}")
    @Operation(summary = "유지관리비 api" , description = "전체 지점의 유지 관리비를 제공 합니다")
    public ResponseEntity<ResponseDTO> getMaintenance(@PathVariable String type , @RequestParam(defaultValue = "Total") String branchCode){

        Map<String , Object> result = new HashMap<>();

        if(type.equals("facility")){
            result = service.getFacilityCost(branchCode);
            result.put("name", "facility");
        }else{
            result = service.getCarCost();
            result.put("name","car");
        }
        return  ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"유지관리비",result));
    }

    @GetMapping("rank")
    @Operation(summary = "매출 순 지점", description = "매출 순서대로 지점을 가지고 옵니다")
    public ResponseEntity<ResponseDTO> getRanking(){

        List<RankingDTO> rankingDTOS = service.getRanking();

        System.out.println("rankingDTOS = " + rankingDTOS);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"랭킹 제공",rankingDTOS));
    }


}
