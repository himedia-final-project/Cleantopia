package aircleanprojectback.restapi.report.service;

import aircleanprojectback.restapi.report.dto.VehicleRepairDTO;
import aircleanprojectback.restapi.report.entity.VehicleRepair;
import aircleanprojectback.restapi.report.repository.VehicleRepairRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VehicleRepairService {
    private final VehicleRepairRepository vehicleRepairRepository;
    private final ModelMapper modelMapper;

    public VehicleRepairService(VehicleRepairRepository vehicleRepairRepository, ModelMapper modelMapper) {
        this.vehicleRepairRepository = vehicleRepairRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public String insertVehicleRepair(VehicleRepairDTO vehicleRepairDTO) {

        VehicleRepair insertVehicleRepair = modelMapper.map(vehicleRepairDTO, VehicleRepair.class);
        vehicleRepairRepository.save(insertVehicleRepair);

        return "차량보고서 등록 성공";
    }

    public List<VehicleRepairDTO> getAllVehicleRepair() {

        List<VehicleRepair> vehicleRepairs = vehicleRepairRepository.findAll();
        List<VehicleRepairDTO> vehicleRepairDTOList = vehicleRepairs.stream()
                .map(vehicleRepair -> modelMapper.map(vehicleRepair, VehicleRepairDTO.class))
                .collect(Collectors.toList());

        return vehicleRepairDTOList;
    }

    public VehicleRepairDTO detailVehicleRepair(int vehicleReportCode) {

        VehicleRepair vehicleRepair = vehicleRepairRepository.findById(String.valueOf(vehicleReportCode)).get();
        VehicleRepairDTO vehicleRepairDTO = modelMapper.map(vehicleRepair, VehicleRepairDTO.class);
        return vehicleRepairDTO;

    }
}
