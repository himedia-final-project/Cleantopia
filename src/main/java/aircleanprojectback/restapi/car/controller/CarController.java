
package aircleanprojectback.restapi.car.controller;

import aircleanprojectback.restapi.car.dto.CarAndDriverDTO;
import aircleanprojectback.restapi.car.dto.CarDTO;
import aircleanprojectback.restapi.car.dto.DriverDTO;
import aircleanprojectback.restapi.car.dto.MemberNameAndDriverDTO;
import aircleanprojectback.restapi.car.service.CarService;
import aircleanprojectback.restapi.common.dto.Criteria;
import aircleanprojectback.restapi.common.dto.ResponseDTO;
import aircleanprojectback.restapi.member.dto.MemberAndDriverDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<ResponseDTO> selectCarList(@RequestParam(defaultValue = "1") String offset) {
        Criteria criteria = new Criteria();
        criteria.setPageNum(Integer.parseInt(offset));
        criteria.setAmount(5);
        Page<CarAndDriverDTO> carList = carService.findAllCarWithPage(criteria);
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", carList));
    }

    @GetMapping("driver")
    public ResponseEntity<ResponseDTO> selectDriverList() {



        List<MemberNameAndDriverDTO> driverList = carService.getDriverWithN();


        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"배정안된 차량기사",driverList));
    }

    @PostMapping("car")
    public ResponseEntity<ResponseDTO> insertCar(@ModelAttribute CarDTO carDTO , MultipartFile photo1 , MultipartFile photo2){
        System.out.println("carDTO = " + carDTO);
        System.out.println("photo1 = " + photo1.getOriginalFilename());
        System.out.println("photo2 = " + photo2.getOriginalFilename());

        CarDTO newCar = carService.insertCar(carDTO,photo1,photo2);

        return  ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"차량등록 성공",newCar));
    }

    @DeleteMapping("car")
    public ResponseEntity<ResponseDTO> deleteCar(@RequestBody List<String> selectedCars){
        selectedCars.forEach(System.out::println);

        carService.deleteAll(selectedCars);

        return  ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"차량삭제 성공",selectedCars));
    }

//    @PostMapping("/company/assignDriver")
//    public ResponseEntity<ResponseDTO> assignDriverToCar(@RequestBody CarAndDriverDTO carAndDriverDTO) {
//        carService.assignDriverToCar(carAndDriverDTO.getCarNumber(), carAndDriverDTO.getDriverAndMemberDTO().getDriverLicenseNumber());
//        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "배정 성공", null));
//    }
//
//    @PostMapping("/company/unassignDriver")
//    public ResponseEntity<ResponseDTO> unassignDriverFromCar(@RequestBody CarAndDriverDTO carAndDriverDTO) {
//        carService.unassignDriverFromCar(carAndDriverDTO.getCarNumber());
//        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "배정 취소 성공", null));
//    }
}
