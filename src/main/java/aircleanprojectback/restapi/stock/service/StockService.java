package aircleanprojectback.restapi.stock.service;

import aircleanprojectback.restapi.stock.dto.LaundryPartmanagementDTO;
import aircleanprojectback.restapi.stock.dto.LaundrySupplyManagementDTO;
import aircleanprojectback.restapi.stock.entity.LaundryPartManagement;
import aircleanprojectback.restapi.stock.entity.LaundrySupplyManagement;
import aircleanprojectback.restapi.stock.repository.LaundryPartManagementRepository;
import aircleanprojectback.restapi.stock.repository.LaundrySupplyManagementRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StockService {

    private final LaundryPartManagementRepository laundryPartManagementRepository;

    private final LaundrySupplyManagementRepository laundrySupplyManagementRepository;

    private final ModelMapper modelMapper;

    public StockService(LaundryPartManagementRepository laundryPartManagementRepository, LaundrySupplyManagementRepository laundrySupplyManagementRepository, ModelMapper modelMapper) {
        this.laundryPartManagementRepository = laundryPartManagementRepository;
        this.laundrySupplyManagementRepository = laundrySupplyManagementRepository;
        this.modelMapper = modelMapper;
    }

    public List<LaundrySupplyManagementDTO> selectAllHDetergent() {

        log.info("[StockService] selectAllHDetergent Start ===================");

        List<LaundrySupplyManagement> hDetergentInfo = laundrySupplyManagementRepository.findByHeadquartersCode("HQ001");

        List<LaundrySupplyManagementDTO> hDetergentInfoDTOList = hDetergentInfo.stream()
                .map(stock -> {
                    LaundrySupplyManagementDTO laundrySupplyManagementDTO = modelMapper.map(stock, LaundrySupplyManagementDTO.class);
                    return laundrySupplyManagementDTO;
                })
                .collect(Collectors.toList());

        log.info("[StockService] selectAllHDetergent End =======================");
        return hDetergentInfoDTOList;
    }

}
