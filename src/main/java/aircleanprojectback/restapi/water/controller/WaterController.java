package aircleanprojectback.restapi.water.controller;

import aircleanprojectback.restapi.branch.repository.BranchRepository;
import aircleanprojectback.restapi.common.dto.ResponseDTO;
import aircleanprojectback.restapi.water.dto.Row;
import aircleanprojectback.restapi.water.dto.WaterSupplyRequest;
import aircleanprojectback.restapi.water.entity.WaterCondition;
import aircleanprojectback.restapi.water.service.WaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/location")
public class WaterController {

    private final WaterService service;
    private final WaterService waterService;

    public WaterController(WaterService service, WaterService waterService){
        this.service = service;
        this.waterService = waterService;
    }



    // 수질 조회
    @GetMapping("/water/{branchCode}")
    public ResponseEntity<ResponseDTO> getWaterCondition(@PathVariable String branchCode){


        Optional<WaterCondition> waterCondition = service.getWaterCondition(branchCode);


        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"수질 전송",waterCondition));

    }

    @PostMapping("/waterSupply")
    public ResponseEntity<ResponseDTO> waterSupply(@RequestBody WaterSupplyRequest request) {
        

        waterService.registWaterSupply(request);


        ResponseDTO response = new ResponseDTO(HttpStatus.OK, "급수 완료", true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/water/cost")
    public ResponseEntity<ResponseDTO> waterCost(@RequestParam String branchCode, @RequestParam String month){
        System.out.println("branchCode = " + branchCode);
//        System.out.println("month = " + month);

        String year = month.split("-")[0];
        month = month.split("-")[1];
        System.out.println("year = " + year);
        System.out.println("month = " + month);

        String result = waterService.findWaterCost(branchCode,month,year);

        Map<String,String> waterCost= new HashMap<>();

        waterCost.put("waterCost",result);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"상수도 금액",waterCost));
    }




}
