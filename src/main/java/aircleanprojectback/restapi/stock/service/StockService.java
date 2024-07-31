package aircleanprojectback.restapi.stock.service;

import aircleanprojectback.restapi.stock.dto.*;
import aircleanprojectback.restapi.stock.entity.*;
import aircleanprojectback.restapi.stock.repository.HeadBranchStockApplicationRepository;
import aircleanprojectback.restapi.stock.repository.HeadStockApplicationRepository;
import aircleanprojectback.restapi.stock.repository.LaundryPartManagementRepository;
import aircleanprojectback.restapi.stock.repository.LaundrySupplyManagementRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StockService {

    private final LaundryPartManagementRepository laundryPartManagementRepository;

    private final LaundrySupplyManagementRepository laundrySupplyManagementRepository;
    private final HeadStockApplicationRepository headStockApplicationRepository;

    private final HeadBranchStockApplicationRepository headBranchStockApplicationRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public StockService(LaundryPartManagementRepository laundryPartManagementRepository, LaundrySupplyManagementRepository laundrySupplyManagementRepository, ModelMapper modelMapper, HeadStockApplicationRepository headStockApplicationRepository, HeadBranchStockApplicationRepository headBranchStockApplicationRepository) {
        this.laundryPartManagementRepository = laundryPartManagementRepository;
        this.laundrySupplyManagementRepository = laundrySupplyManagementRepository;
        this.modelMapper = modelMapper;
        this.headStockApplicationRepository = headStockApplicationRepository;
        this.headBranchStockApplicationRepository = headBranchStockApplicationRepository;
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

    @Transactional
    public HeadStockApplicationDTO saveHeadStockApplication(HeadStockApplicationDTO headStockApplicationDTO) {

        HeadStockApplication headStockApplication = modelMapper.map(headStockApplicationDTO, HeadStockApplication.class);

        System.out.println("headStockApplication = " + headStockApplication);
        HeadStockApplication headStockApplication1 =headStockApplicationRepository.save(headStockApplication);

        System.out.println("headStockApplication1 = " + headStockApplication1);
        return modelMapper.map(headStockApplication1, HeadStockApplicationDTO.class);
    }

    @Transactional
    public void updateStock(HeadStockUpdate request) {

        for (LaundrySupplyAndManagementDTO detergentDTO : request.getDetergents()) {
            LaundrySupplyAndManagement detergentsEntity = laundrySupplyManagementRepository.findByLaundrySupplyManagementCode(detergentDTO.getLaundrySupplyManagementCode());
            if(detergentsEntity != null) {
                detergentsEntity = detergentsEntity.toBuilder().laundrySupplyStock(detergentDTO.getLaundrySupplyStock()).build();
            }
            laundrySupplyManagementRepository.save(detergentsEntity);
        }

        for (LaundryPartAndManagementDTO partDTO : request.getParts()) {
            LaundryPartAndManagement partsEntity = laundryPartManagementRepository.findByLaundryPartManagementCode(partDTO.getLaundryPartManagementCode());
            if(partsEntity != null) {
                partsEntity = partsEntity.toBuilder().laundryPartStock(partDTO.getLaundryPartStock()).build();
            }
            laundryPartManagementRepository.save(partsEntity);
        }

    }

    public List<HeadStockApplicationDTO> getHeadStockApplicationHistory() {

        List<HeadStockApplication> headStockApplicationEntity = headStockApplicationRepository.findByMemberIdStartingWithEOrA();

        List<HeadStockApplicationDTO> headStockApplicationDTO = headStockApplicationEntity.stream()
                .map(entity -> modelMapper.map(entity, HeadStockApplicationDTO.class))
                .collect(Collectors.toList());

        return headStockApplicationDTO;

    }

    public List<BranchStockApplicationDTO> getHeadBranchStockApplication() {

        List<BranchStockApplication> branchStockApplicationEntity = headBranchStockApplicationRepository.findByBranchCodeStartingWith("BR");

        List<BranchStockApplicationDTO> branchStockApplicationDTO = branchStockApplicationEntity.stream()
                .map(entity -> modelMapper.map(entity, BranchStockApplicationDTO.class))
                .collect(Collectors.toList());

        return  branchStockApplicationDTO;

    }

    @Transactional
    public void updateHeadBranchStock(HeadStockUpdate request) {

        for(LaundrySupplyAndManagementDTO detergentDTO : request.getDetergents()) {

            LaundrySupplyAndManagement detergentsEntity = laundrySupplyManagementRepository.findByLaundrySupplyManagementCode(detergentDTO.getLaundrySupplyManagementCode());

            if (detergentsEntity != null) {

                detergentsEntity = detergentsEntity.toBuilder()
                        .laundrySupplyStock(detergentDTO.getLaundrySupplyStock())
                        .build();

                laundrySupplyManagementRepository.save(detergentsEntity);
            }
        }

        for (LaundryPartAndManagementDTO partDTO : request.getParts()) {

            LaundryPartAndManagement partsEntity = laundryPartManagementRepository.findByLaundryPartManagementCode(partDTO.getLaundryPartManagementCode());

            if (partsEntity != null) {

                partsEntity = partsEntity.toBuilder()
                        .laundryPartStock(partDTO.getLaundryPartStock())
                        .build();

                laundryPartManagementRepository.save(partsEntity);
            }
        }

    }

    @Transactional
    public void updateHeadBranchStockApplication(int bApplicationCode, BranchStockApplicationDTO request) {

        BranchStockApplication branchStockApplication = headBranchStockApplicationRepository.findByBApplicationCode(bApplicationCode);

        branchStockApplication = branchStockApplication.toBuilder()
                .bApplicationStatus(request.getbApplicationStatus())
                .bApprovalDate(request.getbApprovalDate())
                .bApproverName(request.getbApproverName())
                .build();

        headBranchStockApplicationRepository.save(branchStockApplication);

    }
}