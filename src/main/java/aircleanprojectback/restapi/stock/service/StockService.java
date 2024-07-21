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
//
//    public List<LaundrySupplyAndManagementDTO> selectAllHDetergent() {
//
//        log.info("[StockService] selectAllHDetergent Start ===================");
//
//        List<LaundrySupplyAndManagement> hDetergentInfo = laundrySupplyManagementRepository.findByHeadquartersCode("HQ001");
//
//        System.out.println("hDetergentInfo = " + hDetergentInfo);
//
//        List<LaundrySupplyAndManagementDTO> hDetergentInfoDTOList = hDetergentInfo.stream()
//                .map(stock -> {
//                    LaundrySupplyAndManagementDTO laundrySupplyAndManagementDTO = modelMapper.map(stock, LaundrySupplyAndManagementDTO.class);
//                    return laundrySupplyAndManagementDTO;
//                })
//                .collect(Collectors.toList());
//
//
//
//
//        log.info("[StockService] selectAllHDetergent End =======================");
//        return hDetergentInfoDTOList;
//    }

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

    public List<LaundrySupplyAndManagementDTO> getDetergentsStockInfo(String memberRole) {

        log.info("[StockService] getDetergentsStockInfo Start ===================");

        List<LaundrySupplyAndManagement> resultEntity;

        if("a".equals(memberRole) || "e".equals(memberRole)) {
            resultEntity = laundrySupplyManagementRepository.findByHeadquartersCode("HQ001");
        } else if ("b".equals(memberRole)) {
            resultEntity = laundrySupplyManagementRepository.findByBranchCodeStartingWith("BR");
        } else {
            resultEntity = List.of();
        }

        return resultEntity.stream()
                .map(entity -> modelMapper.map(entity, LaundrySupplyAndManagementDTO.class))
                .collect(Collectors.toList());

    }

    public List<LaundryPartAndManagementDTO> getPartsStockInfo(String memberRole) {

        log.info("[StockService] getPartsStockInfo Start ===================");

        List<LaundryPartAndManagement> resultEntity;

        if("a".equals(memberRole) || "e".equals(memberRole)) {
            resultEntity = laundryPartManagementRepository.findByHeadquartersCode("HQ001");
        } else if ("b".equals(memberRole)) {
            resultEntity = laundryPartManagementRepository.findByBranchCodeStartingWith("BR");
        } else {
            resultEntity = List.of();
        }

        return resultEntity.stream()
                .map(entity -> modelMapper.map(entity, LaundryPartAndManagementDTO.class))
                .collect(Collectors.toList());

    }
}
