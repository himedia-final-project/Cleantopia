
package aircleanprojectback.restapi.car.service;

import aircleanprojectback.restapi.car.dto.*;
import aircleanprojectback.restapi.car.entity.*;


import aircleanprojectback.restapi.car.repository.CarAndDriverRepostiory;
import aircleanprojectback.restapi.car.repository.CarRepository;
import aircleanprojectback.restapi.car.repository.DriverRepository;
import aircleanprojectback.restapi.car.repository.MemberNameAndDriverRepository;
import aircleanprojectback.restapi.common.dto.Criteria;
import aircleanprojectback.restapi.member.dto.MemberAndDriverDTO;
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
    private final DriverRepository driverRepository;
    private final MemberNameAndDriverRepository memberNameAndDriverRepository;

    public CarService(CarAndDriverRepostiory carAndDriverRepostiory, ModelMapper modelMapper, DriverRepository driverRepository
            , MemberNameAndDriverRepository memberNameAndDriverRepository) {
        this.carAndDriverRepostiory = carAndDriverRepostiory;
        this.modelMapper = modelMapper;
        this.memberNameAndDriverRepository = memberNameAndDriverRepository;
        this.driverRepository = driverRepository;

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
}

