package aircleanprojectback.restapi.car.service;

import aircleanprojectback.restapi.car.dto.CarAndDriverAndEmployeeDTO;
import aircleanprojectback.restapi.car.dto.CarAndDriverDTO;
import aircleanprojectback.restapi.car.dto.DriverAndMemberDTO;
import aircleanprojectback.restapi.car.dto.DriverCarDTO;
import aircleanprojectback.restapi.car.entity.Car;


import aircleanprojectback.restapi.car.entity.CarAndDriver;
import aircleanprojectback.restapi.car.entity.DriverAndMember;
import aircleanprojectback.restapi.car.repository.CarAndDriverRepostiory;
import aircleanprojectback.restapi.car.repository.CarRepository;
import aircleanprojectback.restapi.common.dto.Criteria;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
    
    private final CarAndDriverRepostiory carAndDriverRepostiory;
    private final ModelMapper modelMapper;

    public CarService(CarAndDriverRepostiory carAndDriverRepostiory, ModelMapper modelMapper){
        this.carAndDriverRepostiory=carAndDriverRepostiory;
        this.modelMapper=modelMapper;

        modelMapper.createTypeMap(CarAndDriver.class, CarAndDriverDTO.class)
                .addMapping(src->src.getDriverAndMember(),CarAndDriverDTO::setDriverAndMemberDTO);

        modelMapper.createTypeMap(DriverAndMember.class, DriverAndMemberDTO.class)
                .addMapping(src->src.getMembers(),DriverAndMemberDTO::setMemberDTO);
    }

    public Page<CarAndDriverDTO> findAllCarWithPage(Criteria criteria) {

        Pageable pageable = PageRequest.of(criteria.getPageNum()-1,criteria.getAmount());
        
        Page<CarAndDriver> result = carAndDriverRepostiory.findAll(pageable);
        
        Page<CarAndDriverDTO> carList = result.map(car->modelMapper.map(car,CarAndDriverDTO.class));

        System.out.println("result.getContent() = " + result.getContent());
        System.out.println("carList.getContent() = " + carList.getContent());
        return carList;
    }


//    private final CarAndDriverAndEmployeeRepository carAndDriverAndEmployeeRepository;
//    private final CarRepository carRepository;
//    private final DriverCarRepository driverCarRepository;

//    @Autowired
//    public CarService(CarAndDriverAndEmployeeRepository carAndDriverAndEmployeeRepository, CarRepository carRepository, DriverCarRepository driverCarRepository) {
//        this.carAndDriverAndEmployeeRepository = carAndDriverAndEmployeeRepository;
//        this.carRepository = carRepository;
//        this.driverCarRepository = driverCarRepository;
//    }

//    public List<CarAndDriverAndEmployeeDTO> getAllCars() {
//        System.out.println("ssss");
//
//        List<CarAndDriverAndEmployee> cars = carAndDriverAndEmployeeRepository.findAll();
//
//        cars.forEach(System.out::println);
//
//        return null;
//    }

//    private CarAndDriverAndEmployeeDTO convertToDto(Car car) {
//        DriverCar driverCar = car.getDriverCar();
//
//        return CarAndDriverAndEmployeeDTO.builder()
//                .carNumber(car.getCarNumber())
//                .carAssignedStatus(car.getCarAssignedStatus())
//                .carDate(car.getCarDate())
//                .carPhoto(car.getCarPhoto())
//                .carEtc(car.getCarEtc())
//                .branchRegion(car.getBranchRegion())
//                .driverCarDTO(driverCar != null ? new DriverCarDTO(
//                        driverCar.getDriverLicenseNumber(),
//                        driverCar.getDriverRegion(),
//                        driverCar.getMembers() != null ? new MemberDTO(
//                                driverCar.getMembers().getMemberId(),
//                                driverCar.getMembers().getMemberPassword(),
//                                driverCar.getMembers().getMemberName(),
//                                driverCar.getMembers().getMemberEmail(),
//                                driverCar.getMembers().getMemberPhoneNumber(),
//                                driverCar.getMembers().getMemberRole(),
//                                driverCar.getMembers().getMemberBirthDate(),
//                                driverCar.getMembers().getMemberGender(),
//                                driverCar.getMembers().getMemberAddress(),
//                                driverCar.getMembers().getMemberStatus(),
//                                driverCar.getMembers().getMemberHireDate(),
//                                driverCar.getMembers().getBranchOwnership(),
//                                driverCar.getMembers().getMemberImage()
//                        ) : null
//                ) : null)
//                .build();
//    }
}
