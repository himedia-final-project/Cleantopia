package aircleanprojectback.restapi.water.controller;

import aircleanprojectback.restapi.branch.repository.BranchRepository;
import aircleanprojectback.restapi.common.dto.ResponseDTO;
import aircleanprojectback.restapi.water.entity.WaterCondition;
import aircleanprojectback.restapi.water.service.WaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/location")
public class WaterController {

    private final WaterService service;

    public WaterController(WaterService service){
        this.service = service;
    }



    // 수질 조회
    @GetMapping("/water/{branchCode}")
    public ResponseEntity<ResponseDTO> getWaterCondition(@PathVariable String branchCode){


        Optional<WaterCondition> waterCondition = service.getWaterCondition(branchCode);


        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"수질 전송",waterCondition));

    }
}
