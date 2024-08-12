package aircleanprojectback.restapi.water.controller;

import aircleanprojectback.restapi.common.dto.ResponseDTO;
import aircleanprojectback.restapi.water.dto.WaterSupplyRequest;
import aircleanprojectback.restapi.water.entity.WaterCondition;
import aircleanprojectback.restapi.water.service.WaterService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/location")
@Tag(name = "수질 관리 API", description = "수질 상태 조회 및 급수 관련 API")
public class WaterController {

    private final WaterService service;
    private final WaterService waterService;

    public WaterController(WaterService service, WaterService waterService) {
        this.service = service;
        this.waterService = waterService;
    }

    @Tag(name = "수질 상태 조회", description = "서울시 공공 수질 정보 조회 API")
    @GetMapping("/water/{branchCode}")
    public ResponseEntity<ResponseDTO> getWaterCondition(@PathVariable String branchCode) {
        Optional<WaterCondition> waterCondition = service.getWaterCondition(branchCode);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "수질 상태 전송", waterCondition));
    }

    @Tag(name = "물탱크에 물 급수하기", description = "물탱크 정보 업데이트")
    @PostMapping("/waterSupply")
    public ResponseEntity<ResponseDTO> waterSupply(@RequestBody WaterSupplyRequest request) {
        waterService.registWaterSupply(request);
        ResponseDTO response = new ResponseDTO(HttpStatus.OK, "급수 완료", true);
        return ResponseEntity.ok(response);
    }

    @Tag(name = "수도요금 조회")
    @GetMapping("/water/cost")
    public ResponseEntity<ResponseDTO> waterCost(
            @RequestParam String branchCode,
            @RequestParam String month) {

        String year = month.split("-")[0];
        month = month.split("-")[1];
        String result = waterService.findWaterCost(branchCode, month, year);

        Map<String, String> waterCost = new HashMap<>();
        waterCost.put("waterCost", result);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "상수도 금액", waterCost));
    }
}
