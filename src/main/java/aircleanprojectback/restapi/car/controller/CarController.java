//package aircleanprojectback.restapi.car.controller;
//
//import aircleanprojectback.restapi.car.dto.CarDTO;
//import aircleanprojectback.restapi.car.service.CarService;
//import aircleanprojectback.restapi.common.dto.Criteria;
//import aircleanprojectback.restapi.common.dto.PagingResponseDTO;
//import aircleanprojectback.restapi.common.dto.ResponseDTO;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.data.domain.Page;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.List;
//
//@Tag(name = "차량 관련 API", description = "차량 관련 CRUD 및 배정 기능을 제공하는 API")
//@RestController
//@RequestMapping("/carsservice")
//@Slf4j
//public class CarController {
//
//    private final CarService carService;
//
//    public CarController(CarService carService) {
//        this.carService = carService;
//    }
//
////    @Tag(name = "차량 목록 조회", description = "전체 차량 목록을 조회")
////    @GetMapping("/company/cars")
////    public ResponseEntity<ResponseDTO> selectCarList(@RequestParam (defaultValue = "1") String offset) {
////        log.info("[CarController] selectCarList Start ============ ");
////        Criteria criteria = new Criteria(Integer.parseInt(offset),10);
////
////        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
////
////        Page<CarDTO> carList = carService.getCarListWithPaging(criteria);
////
////
//////        List<CarDTO> carList = carService.selectCarList();
////        log.info("[CarController] selectCarList End ============ ");
////        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", carList));
////    }
//
//
//    @Tag(name = "차량 상세 정보 조회", description = "차량 번호에 맞는 상세 정보를 조회")
//    @GetMapping("/company/cars/{carNumber}")
//    public ResponseEntity<ResponseDTO> selectCarDetail(@PathVariable String carNumber) {
//        log.info("[CarController] selectCarDetail Start ============ ");
//        log.info("[CarController] selectCarDetail carNumber : {} ", carNumber);
//        CarDTO carDTO = carService.selectCar(carNumber);
//        log.info("[CarController] selectCarDetail End ============ ");
//        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "차량 상세정보 조회 성공", carDTO));
//    }
//
//    @Tag(name="차량 등록", description = "새로운 차량을 등록")
//    @PostMapping("/company/cars")
//    public ResponseEntity<ResponseDTO> insertCar(@ModelAttribute CarDTO carDTO, @RequestParam("carPhoto") MultipartFile carPhoto) {
//        log.info("[CarController] insertCar Start ============ ");
//        log.info("[CarController] insertCar carDTO : {} ", carDTO);
//        carService.insertCar(carDTO, carPhoto);
//        log.info("[CarController] insertCar End ============ ");
//        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "차량 등록 성공", null));
//    }
//
//    @Tag(name="차량 배정", description = "차량과 운전자를 배정")
//    @PostMapping("/company/cars/assign")
//    public ResponseEntity<ResponseDTO> assignCar(@ModelAttribute CarDTO carDTO) {
//        log.info("[CarController] assignCar Start ============ ");
//        log.info("[CarController] assignCar carDTO : {} ", carDTO);
//
//        carService.assignCar(carDTO);
//        log.info("[CarController] assignCar End ============ ");
//        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "차량 배정 성공", null));
//    }
//}
