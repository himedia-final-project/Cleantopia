package aircleanprojectback.restapi.car.controller;

import aircleanprojectback.restapi.car.dto.CarAndDriverAndEmployeeDTO;
import aircleanprojectback.restapi.car.dto.CarAndDriverDTO;
import aircleanprojectback.restapi.car.service.CarService;
import aircleanprojectback.restapi.common.dto.Criteria;
import aircleanprojectback.restapi.common.dto.ResponseDTO;
import aircleanprojectback.restapi.report.entity.CarMembers;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "차량 관련 API", description = "차량 관련 CRUD 및 배정 기능을 제공하는 API")
@RestController
@RequestMapping("/carsservice")
@Slf4j
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }



    @Tag(name = "차량 목록 조회", description = "전체 차량 목록을 조회")
    @GetMapping("/company/cars")
    public ResponseEntity<ResponseDTO> selectCarList(@RequestParam(defaultValue = "1")String offset){
        Criteria criteria = new Criteria();
        criteria.setPageNum(Integer.parseInt(offset));
        criteria.setAmount(10);
        Page<CarAndDriverDTO> carList = carService.findAllCarWithPage(criteria);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", carList));
    }
}
