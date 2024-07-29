package aircleanprojectback.restapi.car.service;

import aircleanprojectback.restapi.car.dto.CarDTO;
import aircleanprojectback.restapi.car.entity.Car;
import aircleanprojectback.restapi.car.repository.CarRepository;
import aircleanprojectback.restapi.common.dto.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<CarDTO> selectCarList() {
        List<Car> cars = carRepository.findAll();
        return cars.stream().map(car -> new CarDTO(
                car.getCarNumber(),
                car.getDriverLicenseNumber(),
                car.getCarAssignedStatus(),
                car.getCarDate(),
                car.getCarPhoto(),
                car.getCarEtc(),
                car.getBranchRegion()
        )).toList();
    }

    public CarDTO selectCar(String carNumber) {
        Car car = carRepository.findById(carNumber).orElseThrow(() -> new RuntimeException("차량을 찾을 수 없습니다."));
        return new CarDTO(
                car.getCarNumber(),
                car.getDriverLicenseNumber(),
                car.getCarAssignedStatus(),
                car.getCarDate(),
                car.getCarPhoto(),
                car.getCarEtc(),
                car.getBranchRegion()
        );
    }

    public Car insertCar(CarDTO carDTO, MultipartFile carPhoto) {
        Car car = new Car(
                carDTO.getCarNumber(),
                carDTO.getDriverName(),
                carPhoto.getOriginalFilename(),
                carDTO.getCarAssignedStatus(),
                carDTO.getCarDate(),
                carDTO.getCarEtc(),
                carDTO.getBranchRegion()
        );
        return carRepository.save(car);
    }

    public void assignCar(CarDTO carDTO) {
        Car car = carRepository.findById(carDTO.getCarNumber())
                .orElseThrow(() -> new RuntimeException("차량을 찾을 수 없습니다."));
        car.setDriverLicenseNumber(carDTO.getDriverName());
        car.setCarAssignedStatus("Assigned");
        carRepository.save(car);
    }

//    public Page<CarDTO> getCarListWithPaging(Criteria criteria) {
//
//        int index = criteria.getPageNum()-1;
//        int count = criteria.getAmount();
//
////        Pageable paging = PageRequest.of(index,count,)
////        Page<Car> result = carRepository.
//
//
//
//    }
}
