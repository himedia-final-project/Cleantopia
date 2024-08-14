
package aircleanprojectback.restapi.car.service;

import aircleanprojectback.restapi.car.dto.*;
import aircleanprojectback.restapi.car.entity.*;


import aircleanprojectback.restapi.car.repository.*;
import aircleanprojectback.restapi.common.dto.Criteria;
import aircleanprojectback.restapi.util.FileUploadUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final LaundryAndDriverRepository laundryAndDriverRepository;
    @Value("${image.image-dir}")
    private String IMAGE_DIR;

    @Value("${image.image-url}")
    private String IMAGE_URL;


    private final CarAndDriverRepostiory carAndDriverRepostiory;
    private final ModelMapper modelMapper;
    private final DriverRepository driverRepository;
    private final MemberNameAndDriverRepository memberNameAndDriverRepository;
    private final CarRepository carRepository;

    public CarService(CarAndDriverRepostiory carAndDriverRepostiory, ModelMapper modelMapper, DriverRepository driverRepository
            , MemberNameAndDriverRepository memberNameAndDriverRepository, CarRepository carRepository, LaundryAndDriverRepository laundryAndDriverRepository) {
        this.carAndDriverRepostiory = carAndDriverRepostiory;
        this.modelMapper = modelMapper;
        this.memberNameAndDriverRepository = memberNameAndDriverRepository;
        this.driverRepository = driverRepository;
        this.carRepository = carRepository;
        this.laundryAndDriverRepository = laundryAndDriverRepository;

        modelMapper.createTypeMap(CarAndDriver.class, CarAndDriverDTO.class)
                .addMapping(src -> src.getDriverAndMember(), CarAndDriverDTO::setDriverAndMemberDTO);

        modelMapper.createTypeMap(DriverAndMember.class, DriverAndMemberDTO.class)
                .addMapping(src -> src.getMembers(), DriverAndMemberDTO::setMemberDTO);



    }

    public Page<CarAndDriverDTO> findAllCarWithPage(Criteria criteria) {

        Pageable pageable = PageRequest.of(criteria.getPageNum() - 1, criteria.getAmount());

        Page<CarAndDriver> result = carAndDriverRepostiory.findAll(pageable);

        Page<CarAndDriverDTO> carList = result.map(car -> modelMapper.map(car, CarAndDriverDTO.class));

        System.out.println("result.getContent() = " + result.getContent());
        System.out.println("carList.getContent() = " + carList.getContent());
        return carList;
    }


    public List<MemberNameAndDriverDTO> getDriverWithN() {

        List<MemberNameAndDriver> result = memberNameAndDriverRepository.findAllByAssignCar("N", "Y");

        result.forEach(System.out::println);

        List<MemberNameAndDriverDTO> driverList = result.stream().map(driver -> modelMapper.map(driver, MemberNameAndDriverDTO.class)).collect(Collectors.toList());

        driverList.forEach(System.out::println);
        return driverList;
    }

    @Transactional
    public CarDTO insertCar(CarDTO carDTO, MultipartFile photo1, MultipartFile photo2) {

        carDTO.setCarAssignedStatus("N");

        System.out.println("carDTO = " + carDTO);

        String imageName1 = UUID.randomUUID().toString().replace("-","");
        String imageName2 = UUID.randomUUID().toString().replace("-","");
        String replaceFileName1=null;
        String replaceFileName2=null;
        if(photo1 !=null && photo2 !=null){
            try{
                replaceFileName1 = FileUploadUtils.saveFile(IMAGE_DIR,imageName1,photo1);
                replaceFileName2 = FileUploadUtils.saveFile(IMAGE_DIR,imageName2,photo2);

                carDTO.setCarFrontImage(replaceFileName1);
                carDTO.setCarRearImage(replaceFileName2);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        Car newCar = modelMapper.map(carDTO,Car.class);
        newCar.carAssignedStatus("N");


        System.out.println("newCar = " + newCar);

        newCar = carRepository.save(newCar);


        return modelMapper.map(newCar,CarDTO.class);
    }


    @Transactional
    public void deleteAll(List<String> selectedCars) {

        List<Car> carList = carRepository.findAllByCarNumberIn(selectedCars);
        List<String> licenses = new ArrayList<>();

        carList.forEach(car->licenses.add(car.getDriverLicenseNumber()));
        List<Driver> drivers = driverRepository.findAllById(licenses);

        drivers.forEach(driver->driver.assignCar("N"));

        carRepository.deleteAllByIdInBatch(selectedCars);


    }

    @Transactional
    public DriverDTO assignDriver(String selectedDriver, String selectedCar) {


        Driver driver = driverRepository.findByMemberId(selectedDriver);

        Car car = carRepository.findByCarNumber(selectedCar);

        car.driverLicenseNumber(driver.getDriverLicenseNumber());
        car.carAssignedStatus("Y");
        car.branchRegion(driver.getDriverRegion());

        System.out.println("car = " + car);

        driver.assignCar("Y");
        System.out.println("driver = " + driver);

        return modelMapper.map(driver,DriverDTO.class);
    }

    @Transactional
    public DriverDTO unAssignDriver(String selectedCar, String memberId) {

        Car car = carRepository.findByCarNumber(selectedCar);

        car.driverLicenseNumber(null);
        car.carAssignedStatus("N");
        car.branchRegion(null);

        Driver driver = driverRepository.findByMemberId(memberId);

        driver.assignCar("N");

        return modelMapper.map(driver,DriverDTO.class);
    }

    public Page<LaundryAndDriverDTO> getCarWithLaundry(Criteria cri,  String branchCode ,String isFoward) {


        Pageable pageable = PageRequest.of(cri.getPageNum()-1,cri.getAmount());

        Page<LaundryAndDriver> result =null;
        if(isFoward.equals("false")){
            result = laundryAndDriverRepository.findAllByBranchCodeAndLaundryCollectionStatusAndLaundryCompleted(branchCode,"Y","N",pageable);
            System.out.println("false resultToBranch.getContent() = " + result.getContent());
        }else{
            result  = laundryAndDriverRepository.findAllByBranchCodeAndLaundryCompleted(branchCode,"Y",pageable);
            System.out.println("true result.getContent() = " + result.getContent());
        }


        return result.map(laundry->modelMapper.map(laundry, LaundryAndDriverDTO.class));
    }
}

