package aircleanprojectback.restapi.stock.service;

import aircleanprojectback.restapi.stock.dto.*;
import aircleanprojectback.restapi.stock.entity.*;
import aircleanprojectback.restapi.stock.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.ManyToAny;
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

    private final BranchStockApplicationRepository branchStockApplicationRepository;
    private final OnlyLaundrySupplyManagementRepository onlyLaundrySupplyManagementRepository;
    private final OnlyLaundryPartManagementRepository onlyLaundryPartManagementRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public StockService(OnlyLaundrySupplyManagementRepository onlyLaundrySupplyManagementRepository, OnlyLaundryPartManagementRepository onlyLaundryPartManagementRepository ,BranchStockApplicationRepository branchStockApplicationRepository, LaundryPartManagementRepository laundryPartManagementRepository, LaundrySupplyManagementRepository laundrySupplyManagementRepository, ModelMapper modelMapper, HeadStockApplicationRepository headStockApplicationRepository, HeadBranchStockApplicationRepository headBranchStockApplicationRepository) {
        this.laundryPartManagementRepository = laundryPartManagementRepository;
        this.laundrySupplyManagementRepository = laundrySupplyManagementRepository;
        this.modelMapper = modelMapper;
        this.headStockApplicationRepository = headStockApplicationRepository;
        this.headBranchStockApplicationRepository = headBranchStockApplicationRepository;
        this.branchStockApplicationRepository = branchStockApplicationRepository;
        this.onlyLaundryPartManagementRepository = onlyLaundryPartManagementRepository;
        this.onlyLaundrySupplyManagementRepository = onlyLaundrySupplyManagementRepository;
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
    public BranchStockApplicationDTO saveBranchStockApplication(BranchStockApplicationDTO branchStockApplicationDTO) {

        BranchStockApplication branchStockApplication = modelMapper.map(branchStockApplicationDTO, BranchStockApplication.class);

        System.out.println("branchStockApplication = " + branchStockApplication);
        BranchStockApplication branchStockApplication1 = branchStockApplicationRepository.save(branchStockApplication);

        System.out.println("branchStockApplication1 = " + branchStockApplication1);
        return modelMapper.map(branchStockApplication1, BranchStockApplicationDTO.class);

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

    public List<BranchStockApplicationDTO> getBranchStockHistory(String branchCode) {

        List<BranchStockApplication> branchApplicationEntity = branchStockApplicationRepository.findAllByBranchCode(branchCode);

        List<BranchStockApplicationDTO> branchStockAPplicationDTO = branchApplicationEntity.stream()
                .map(entity -> modelMapper.map(entity, BranchStockApplicationDTO.class))
                .collect(Collectors.toList());

        return branchStockAPplicationDTO;

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


    @Transactional
    public void updateBranchAppDeliveryStatus(BranchStockApplicationDTO branchStockApplicationDTO) {

        BranchStockApplication branchStockApplication = branchStockApplicationRepository.findByBApplicationCode(branchStockApplicationDTO.getbApplicationCode());

        branchStockApplication = branchStockApplication.toBuilder()
                .bApplicationStatus(branchStockApplicationDTO.getbApplicationStatus())
                .build();

        branchStockApplicationRepository.save(branchStockApplication);

    }

    @Transactional
    public void updateBranchSupply(BranchStockUpdate request) {

        List<LaundrySupplyManagementDTO> detergents = request.getDetergents();

                for (LaundrySupplyManagementDTO detergentDTO : detergents) {
                    // DTO에서 laundrySupplyCode를 사용해 해당 엔티티를 조회
                    List<LaundrySupplyManagement> supplyManagements = onlyLaundrySupplyManagementRepository.findByLaundrySupplyCode(detergentDTO.getLaundrySupplyCode());

                    // 조회된 엔티티 리스트를 순회하며 재고 수량을 업데이트
                    for (LaundrySupplyManagement supplyManagement : supplyManagements) {
                        int updatedStock = supplyManagement.getLaundrySupplyStock() + detergentDTO.getLaundrySupplyStock();

                        // 빌더 패턴을 사용해 업데이트된 엔티티 생성
                        LaundrySupplyManagement updatedEntity = supplyManagement.toBuilder()
                                .laundrySupplyStock(updatedStock)
                                .build();

                        // 엔티티 저장
                        onlyLaundrySupplyManagementRepository.save(updatedEntity);
            }
        }
    }

    public void updateBranchPart(BranchStockUpdate request) {

        List<LaundryPartManagementDTO> parts = request.getParts();

        for (LaundryPartManagementDTO partDTO : parts) {
            // DTO에서 laundrySupplyCode를 사용해 해당 엔티티를 조회
            List<LaundryPartManagement> partManagements = onlyLaundryPartManagementRepository.findByLaundryPartCode(partDTO.getLaundryPartCode());

            // 조회된 엔티티 리스트를 순회하며 재고 수량을 업데이트
            for (LaundryPartManagement partManagement : partManagements) {
                int updatedStock = partManagement.getLaundryPartStock() + partDTO.getLaundryPartStock();

                // 빌더 패턴을 사용해 업데이트된 엔티티 생성
                LaundryPartManagement updatedEntity = partManagement.toBuilder()
                        .laundryPartStock(updatedStock)
                        .build();

                // 엔티티 저장
                onlyLaundryPartManagementRepository.save(updatedEntity);
            }
        }
    }
}