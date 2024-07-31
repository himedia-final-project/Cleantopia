package aircleanprojectback.restapi.laundry.service;

import aircleanprojectback.restapi.laundry.dto.WaterTankDTO;
import aircleanprojectback.restapi.laundry.entity.WaterTank;
import aircleanprojectback.restapi.laundry.repository.LandryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ManagementService {

    private final LandryRepository landryRepository;
    private final ModelMapper modelMapper;

    public ManagementService(LandryRepository landryRepository, ModelMapper modelMapper) {
        this.landryRepository = landryRepository;
        this.modelMapper = modelMapper;
    }

    public List<WaterTankDTO> waterTankList() {
        List<WaterTank> waterTankList = landryRepository.findAll();

        System.out.println("여기");
        System.out.println(waterTankList);
        return waterTankList.stream()
                .map(waterTank -> modelMapper.map(waterTank, WaterTankDTO.class))
                .collect(Collectors.toList());
    }

}
