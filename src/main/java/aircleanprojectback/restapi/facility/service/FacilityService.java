package aircleanprojectback.restapi.facility.service;
//
//import aircleanprojectback.restapi.branch.dto.FacilityDetailDTO;
//import aircleanprojectback.restapi.branch.entity.FacilityDetail;
//import aircleanprojectback.restapi.facility.repository.BranchFacilityDetailRepository;
//import aircleanprojectback.restapi.facility.repository.FindBranchInfoRepository;
import aircleanprojectback.restapi.branch.dto.FacilityDetailDTO;
import aircleanprojectback.restapi.branch.entity.FacilityDetail;
import aircleanprojectback.restapi.branch.repository.BranchRepository;
import aircleanprojectback.restapi.branchOrigin.dao.FacilityDetailRepository;
import aircleanprojectback.restapi.car.entity.Driver;
import aircleanprojectback.restapi.car.repository.DriverRepository;
import aircleanprojectback.restapi.facility.dto.FacilityDetailOnlyDTO;
import aircleanprojectback.restapi.facility.dto.LaundryAndLaundryWayDTO;
import aircleanprojectback.restapi.facility.entity.FacilityDetailOnly;
import aircleanprojectback.restapi.facility.repository.FacilityDetailOnlyRepository;
import aircleanprojectback.restapi.facility.repository.FacilityLaundryRepository;
import aircleanprojectback.restapi.facility.repository.FacilityLaundryWayRepository;
import aircleanprojectback.restapi.facility.repository.UpdatateWaterTanckRepository;
import aircleanprojectback.restapi.laundry.dto.WaterTankDTO;
import aircleanprojectback.restapi.laundry.entity.Laundry;
import aircleanprojectback.restapi.laundry.entity.LaundryWay;
import aircleanprojectback.restapi.laundry.entity.WaterTank;
import aircleanprojectback.restapi.laundry.repository.LaundryRepository;
import aircleanprojectback.restapi.member.entity.Branch;
import aircleanprojectback.restapi.stock.entity.LaundrySupplyManagement;
import aircleanprojectback.restapi.stock.repository.OnlyLaundrySupplyManagementRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import org.springframework.ui.Model;
//
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
//
@Service
@Slf4j
public class FacilityService {

    private final ModelMapper modelMapper;
    private final FacilityDetailRepository facilityDetailRepository;
    private final UpdatateWaterTanckRepository updatateWaterTanckRepository;
    private final FacilityDetailOnlyRepository facilityDetailOnlyRepository;
    private final FacilityLaundryRepository facilityLaundryRepository;
    private final FacilityLaundryWayRepository facilityLaundryWayRepository;
    private final LaundryRepository laundryRepository;
    private final OnlyLaundrySupplyManagementRepository onlyLaundrySupplyManagementRepository;
    private final DriverRepository driverRepository;
    private final BranchRepository branchRepository;

    @Autowired
    public FacilityService(FacilityLaundryRepository facilityLaundryRepository, FacilityLaundryWayRepository facilityLaundryWayRepository , FacilityDetailOnlyRepository facilityDetailOnlyRepository , ModelMapper modelMapper, FacilityDetailRepository facilityDetailRepository, UpdatateWaterTanckRepository updatateWaterTanckRepository, LaundryRepository laundryRepository, OnlyLaundrySupplyManagementRepository onlyLaundrySupplyManagementRepository, DriverRepository driverRepository, BranchRepository branchRepository){
        this.modelMapper = modelMapper;
        this.facilityDetailRepository = facilityDetailRepository;
        this.updatateWaterTanckRepository = updatateWaterTanckRepository;
        this.facilityDetailOnlyRepository = facilityDetailOnlyRepository;
        this.facilityLaundryRepository = facilityLaundryRepository;
        this.facilityLaundryWayRepository = facilityLaundryWayRepository;
        this.laundryRepository = laundryRepository;
        this.onlyLaundrySupplyManagementRepository = onlyLaundrySupplyManagementRepository;
        this.driverRepository = driverRepository;
        this.branchRepository = branchRepository;
    }

    public List<FacilityDetailDTO> findFacilityByBranchCode(String branchCode) {

        List<FacilityDetail> result = facilityDetailRepository.findAllByBranchBranchCode(branchCode);

        result.forEach(System.out::println);

        List<FacilityDetailDTO> facilityList = result.stream().map(facility -> modelMapper.map(facility, FacilityDetailDTO.class)).collect(Collectors.toList());

        facilityList.forEach(System.out::println);

        return facilityList;
    }

    @Transactional
    public void saveUpdaterWaterCapacity(WaterTankDTO waterTankDTO, String laundryDetergentAmount, String laundryCode) {

        WaterTank waterTank = updatateWaterTanckRepository.findByBranchCode(waterTankDTO.getBranchCode());

        waterTank = waterTank.toBuilder()
                .waterCurCapacity(waterTankDTO.getWaterCurCapacity())
                .build();



        Laundry updateLaundry = laundryRepository.findByLaundryCode(Integer.parseInt(laundryCode));
        updateLaundry.laundryCompletedDate(Date.valueOf(LocalDate.now()));
        updateLaundry.laundryCompleted("Y");

        LaundrySupplyManagement updateDetergent = onlyLaundrySupplyManagementRepository.findByBranchCodeAndLaundrySupplyCode(waterTankDTO.getBranchCode(),"LS001");

        LaundrySupplyManagement updateSoftner = onlyLaundrySupplyManagementRepository.findByBranchCodeAndLaundrySupplyCode(waterTankDTO.getBranchCode(),"LS002");

        LaundrySupplyManagement updateTubCleaner = onlyLaundrySupplyManagementRepository.findByBranchCodeAndLaundrySupplyCode(waterTankDTO.getBranchCode(),"LS005");
        updateDetergent.laundrySupplyStock(updateDetergent.getLaundrySupplyStock()-Integer.parseInt(laundryDetergentAmount));
        updateSoftner.laundrySupplyStock((int) (updateSoftner.getLaundrySupplyStock()-Integer.parseInt(laundryDetergentAmount)*1.2));
        updateTubCleaner.laundrySupplyStock(updateTubCleaner.getLaundrySupplyStock()-1);

        onlyLaundrySupplyManagementRepository.save(updateDetergent);
        onlyLaundrySupplyManagementRepository.save(updateSoftner);
        onlyLaundrySupplyManagementRepository.save(updateTubCleaner);

        System.out.println("updateLaundry = " + updateLaundry);


        updatateWaterTanckRepository.save(waterTank);
        laundryRepository.save(updateLaundry);
    }

    @Transactional
    public void saveDryUpdate(String laundryCode, String branchCode) {

        LaundrySupplyManagement updateDryer = onlyLaundrySupplyManagementRepository.findByBranchCodeAndLaundrySupplyCode(branchCode,"LS006");

        updateDryer.laundrySupplyStock(updateDryer.getLaundrySupplyStock()-1);

        onlyLaundrySupplyManagementRepository.save(updateDryer);

        Laundry laundry = laundryRepository.findByLaundryCode(Integer.parseInt(laundryCode));

        laundry.dryStatus("Y");

        if(laundry.getCleaningStatus().equals("N")){
            laundry.allComplete("Y");
            laundry.allCompleteDate(Date.valueOf(LocalDate.now()));

            Optional<Branch> branchForRegion = branchRepository.findByBranchCode(laundry.getBranchCode());

            String branchRegion = branchForRegion.get().getBranchRegion();

            List<Driver> drivers = driverRepository.findAllByDriverRegionAndAssignCar(branchRegion,"Y");

            Random random = new Random();

            int randomIndex = random.nextInt(drivers.size());

            laundry.deliveryDriver(drivers.get(randomIndex).getDriverLicenseNumber());


        }

        laundryRepository.save(laundry);


    }

    @Transactional
    public void saveCleanerUpdate(String laundryCode, String branchCode) {

        Laundry laundry = laundryRepository.findByLaundryCode(Integer.parseInt(laundryCode));

        laundry.cleaningStatus("Y");

        laundry.allComplete("Y");
        laundry.allCompleteDate(Date.valueOf(LocalDate.now()));

        Optional<Branch> branchForRegion = branchRepository.findByBranchCode(laundry.getBranchCode());

        String branchRegion = branchForRegion.get().getBranchRegion();

        List<Driver> drivers = driverRepository.findAllByDriverRegionAndAssignCar(branchRegion,"Y");

        Random random = new Random();

        int randomIndex = random.nextInt(drivers.size());

        laundry.deliveryDriver(drivers.get(randomIndex).getDriverLicenseNumber());


        laundryRepository.save(laundry);


    }

    @Transactional
    public void saveRegisterFacility(FacilityDetailOnlyDTO request) {

        FacilityDetailOnly facilityDetail = modelMapper.map(request, FacilityDetailOnly.class);

        FacilityDetailOnly facilityDetailOnly = facilityDetailOnlyRepository.save(facilityDetail);

    }

    @Transactional
    public void updateFacilityStatus(FacilityDetailOnlyDTO request) {

        FacilityDetailOnly facilityDetailOnly = facilityDetailOnlyRepository.findByFacilityId(request.getFacilityId());

        facilityDetailOnly = facilityDetailOnly.toBuilder()
                .facilityStatus(request.getFacilityStatus())
                .build();

        facilityDetailOnlyRepository.save(facilityDetailOnly);

    }

    public List<LaundryAndLaundryWayDTO> findMyLaundryWay(String branchCode) {

        List<Laundry> result1 = facilityLaundryRepository.findAllByBranchCode(branchCode);

        List<Integer> laundryCodes = result1.stream()
                .map(dto -> dto.getLaundryCode())
                .collect(Collectors.toList());

        List<LaundryWay> result2 = facilityLaundryWayRepository.findAllByLaundryLaundryCodeIn(laundryCodes);


        return result2.stream().map(result -> modelMapper.map(result, LaundryAndLaundryWayDTO.class)).collect(Collectors.toList());


    }



}
