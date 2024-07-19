package aircleanprojectback.restapi.stock.service;

import aircleanprojectback.restapi.stock.dto.LaundryPartAndManagementDTO;
import aircleanprojectback.restapi.stock.dto.LaundrySupplyAndManagementDTO;
import aircleanprojectback.restapi.stock.entity.LaundryPartAndManagement;
import aircleanprojectback.restapi.stock.entity.LaundrySupplyAndManagement;
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

    public List<LaundrySupplyAndManagementDTO> selectAllHDetergent() {

        log.info("[StockService] selectAllHDetergent Start ===================");

        List<LaundrySupplyAndManagement> hDetergentInfo = laundrySupplyManagementRepository.findByHeadquartersCode("HQ001");

        System.out.println("hDetergentInfo = " + hDetergentInfo);

        List<LaundrySupplyAndManagementDTO> hDetergentInfoDTOList = hDetergentInfo.stream()
                .map(stock -> {
                    LaundrySupplyAndManagementDTO laundrySupplyAndManagementDTO = modelMapper.map(stock, LaundrySupplyAndManagementDTO.class);
                    return laundrySupplyAndManagementDTO;
                })
                .collect(Collectors.toList());
        
        


        log.info("[StockService] selectAllHDetergent End =======================");
        return hDetergentInfoDTOList;
    }

    public List<LaundryPartAndManagementDTO> selectAllHPart() {

        log.info("[StockService] selectAllHPart Start ===================");

        List<LaundryPartAndManagement> hPartInfo = laundryPartManagementRepository.findByHeadquartersCode("HQ001");

        List<LaundryPartAndManagementDTO> hPartInfoDTOList = hPartInfo.stream()
                .map(stock -> {
                    LaundryPartAndManagementDTO laundryPartAndManagementDTO = modelMapper.map(stock, LaundryPartAndManagementDTO.class);
                    return laundryPartAndManagementDTO;
                })
                .collect(Collectors.toList());

        log.info("[StockService] selectAllHPart End =======================");
        return hPartInfoDTOList;
    }
}
