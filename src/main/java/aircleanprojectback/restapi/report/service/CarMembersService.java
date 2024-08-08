package aircleanprojectback.restapi.report.service;

import aircleanprojectback.restapi.report.dto.CarMembersDTO;
import aircleanprojectback.restapi.report.entity.CarMembers;
import aircleanprojectback.restapi.report.repository.CarMembersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarMembersService {

    private final CarMembersRepository repository;
    private  final ModelMapper modelMapper;

    public CarMembersService(CarMembersRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }


    public List<CarMembersDTO> findCarMembers() {

        List<CarMembers> result = repository.findDriver();

        List<CarMembersDTO> carMembersList = result.stream().map(res->modelMapper.map(res, CarMembersDTO.class)).collect(Collectors.toList());
        return carMembersList;
    }
}