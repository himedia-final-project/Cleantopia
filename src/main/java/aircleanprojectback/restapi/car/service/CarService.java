
package aircleanprojectback.restapi.car.service;

import aircleanprojectback.restapi.car.dto.*;
import aircleanprojectback.restapi.car.entity.*;


import aircleanprojectback.restapi.car.repository.CarAndDriverRepostiory;
import aircleanprojectback.restapi.car.repository.CarRepository;
import aircleanprojectback.restapi.car.repository.DriverRepository;
import aircleanprojectback.restapi.car.repository.MemberNameAndDriverRepository;
import aircleanprojectback.restapi.common.dto.Criteria;
import aircleanprojectback.restapi.member.dto.MemberAndDriverDTO;
import aircleanprojectback.restapi.util.FileUploadUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CarService {

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
            , MemberNameAndDriverRepository memberNameAndDriverRepository, CarRepository carRepository) {
        this.carAndDriverRepostiory = carAndDriverRepostiory;
        this.modelMapper = modelMapper;
        this.memberNameAndDriverRepository = memberNameAndDriverRepository;
        this.driverRepository = driverRepository;
        this.carRepository = carRepository;

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

        carRepository.deleteAllByIdInBatch(selectedCars);

    }
}

