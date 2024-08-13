package aircleanprojectback.restapi.laundry.service;

import aircleanprojectback.restapi.branch.repository.BranchRepository;
import aircleanprojectback.restapi.car.entity.Driver;
import aircleanprojectback.restapi.car.repository.DriverRepository;
import aircleanprojectback.restapi.laundry.dto.LaundryDTO;
import aircleanprojectback.restapi.laundry.dto.WaterTankDTO;
import aircleanprojectback.restapi.laundry.entity.Laundry;
import aircleanprojectback.restapi.laundry.entity.WaterTank;
import aircleanprojectback.restapi.laundry.repository.LandryRepository;
import aircleanprojectback.restapi.laundry.repository.LaundryRepository;
import aircleanprojectback.restapi.member.entity.Branch;
import aircleanprojectback.restapi.water.dao.WaterSupplyRepository;
import aircleanprojectback.restapi.water.dao.WaterTankRepository;
import aircleanprojectback.restapi.water.dto.WaterSupplyDTO;
import aircleanprojectback.restapi.water.entity.WaterSupply;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ManagementService {

    private final LandryRepository landryRepository;
    private final WaterTankRepository waterTankRepository;
    private final ModelMapper modelMapper;
    private final WaterSupplyRepository waterSupplyRepository;
    private final LaundryRepository laundryRepository;
    private final BranchRepository branchRepository;
    private final DriverRepository driverRepository;


    public ManagementService(LandryRepository landryRepository, WaterTankRepository waterTankRepository, ModelMapper modelMapper, WaterSupplyRepository waterSupplyRepository, LaundryRepository laundryRepository, BranchRepository branchRepository, DriverRepository driverRepository) {
        this.landryRepository = landryRepository;
        this.waterTankRepository = waterTankRepository;
        this.modelMapper = modelMapper;
        this.waterSupplyRepository = waterSupplyRepository;
        this.laundryRepository = laundryRepository;
        this.branchRepository = branchRepository;
        this.driverRepository = driverRepository;
    }

    public List<WaterTankDTO> waterTankList() {
        List<WaterTank> waterTankList = landryRepository.findAll();

//        System.out.println("여기");
//        System.out.println(waterTankList);
        return waterTankList.stream()
                .map(waterTank -> modelMapper.map(waterTank, WaterTankDTO.class))
                .collect(Collectors.toList());
    }

    public List<WaterSupplyDTO> SelectWaterSupply(String branchCode) {

        // 브런치 코드로 물탱크 번호 조회
        String waterTankNo = waterTankRepository.selectWaterTankNo(branchCode);

        System.out.println(waterTankNo);
        System.out.println("물탱크 번호" + waterTankNo);

        List<WaterSupply> waterSupplyList = waterSupplyRepository.findByWaterTankNo(waterTankNo);

        return waterSupplyList.stream()
                .map(waterSupply -> modelMapper.map(waterSupply, WaterSupplyDTO.class))
                .collect(Collectors.toList());
    }

    public List<LaundryDTO> selectLaundry(String branchCode) {

//        List<Laundry> selectLaundry = laundryRepository.findByBranchCode(branchCode);
        List<Laundry> selectLaundry = laundryRepository.findByBranchCodeAndLaundryCollectionStatusOrderByLaundryCustomerRegistDate(branchCode,"N");

//        selectLaundry.forEach(laundry -> {
//            laundry.toBuilder().laundryArriveStatus("Y");
//        });


        return selectLaundry.stream()
                .map(laundry -> modelMapper.map(laundry, LaundryDTO.class))
                .collect(Collectors.toList());
    }


    @Transactional
    public boolean updateLaundryStatus(int laundryCode, String statusType, String statusValue) {
        Optional<Laundry> optionalLaundry = laundryRepository.findById((long) laundryCode);


        Optional<Branch> branchForRegion = branchRepository.findByBranchCode(optionalLaundry.get().getBranchCode());

        String branchRegion = branchForRegion.get().getBranchRegion();

        List<Driver> drivers = driverRepository.findAllByDriverRegionAndAssignCar(branchRegion,"Y");

        Random random= new Random();

        int randomIndex = random.nextInt(drivers.size());



        if (optionalLaundry.isPresent()) {
            Laundry laundry = optionalLaundry.get();

            Laundry updatedLaundry = null;

            switch (statusType) {
                case "laundryCollectionStatus":
                    updatedLaundry = laundry.toBuilder()
                            .laundryCollectionStatus(statusValue)
                            .laundryApprovalDate(Date.valueOf(LocalDate.now()))
                            .laundryArriveStatus("N")
                            .pickupDriver(drivers.get(randomIndex).getDriverLicenseNumber())
                            .build();
                    break;
//                case "laundryArriveStatus":
//                    updatedLaundry = laundry.toBuilder()
//                            .laundryArriveStatus(statusValue)
//                            .build();
//                    break;
                default:
                    throw new IllegalArgumentException("Invalid status type: " + statusType);
            }

            laundryRepository.save(updatedLaundry);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public void updateLaundryArrivedStatus() {
        List<Laundry> result = laundryRepository.findAllByLaundryApprovalDate(LocalDate.now().minusDays(1));
        for (Laundry laundry : result) {
            laundry.laundryArriveStatus("Y");
        }
        laundryRepository.saveAll(result); // 변경된 상태를 저장합니다.
    }

    @Transactional
    public void updateLaundryBringCustomerStatus(){
        List<Laundry> result = laundryRepository.findAllByAllCompleteDate(LocalDate.now().minusDays(1));
        for (Laundry laundry :result){
            laundry.bringCustomerStatus("Y");
        }
    }



    @Transactional
    public List<LaundryDTO> getLaundryArrived(String branchCode) {

        List<Laundry> result = laundryRepository.findAllByLaundryApprovalDate(LocalDate.now().minusDays(1));

        for(Laundry laundry : result){
            laundry.laundryArriveStatus("Y");
        }

        List<Laundry> selectLaundry = laundryRepository.findByBranchCode(branchCode);

        return selectLaundry.stream()
                .map(laundry -> modelMapper.map(laundry, LaundryDTO.class))
                .collect(Collectors.toList());
    }

}
