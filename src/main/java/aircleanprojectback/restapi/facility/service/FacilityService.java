package aircleanprojectback.restapi.facility.service;
//
//import aircleanprojectback.restapi.branch.dto.FacilityDetailDTO;
//import aircleanprojectback.restapi.branch.entity.FacilityDetail;
//import aircleanprojectback.restapi.facility.repository.BranchFacilityDetailRepository;
//import aircleanprojectback.restapi.facility.repository.FindBranchInfoRepository;
import aircleanprojectback.restapi.branch.dto.FacilityDetailDTO;
import aircleanprojectback.restapi.branch.entity.FacilityDetail;
import aircleanprojectback.restapi.branchOrigin.dao.FacilityDetailRepository;
import aircleanprojectback.restapi.facility.dto.FacilityDetailOnlyDTO;
import aircleanprojectback.restapi.facility.dto.LaundryAndLaundryWayDTO;
import aircleanprojectback.restapi.facility.entity.FacilityDetailOnly;
import aircleanprojectback.restapi.facility.repository.FacilityDetailOnlyRepository;
import aircleanprojectback.restapi.facility.repository.FacilityLaundryRepository;
import aircleanprojectback.restapi.facility.repository.FacilityLaundryWayRepository;
import aircleanprojectback.restapi.facility.repository.UpdatateWaterTanckRepository;
import aircleanprojectback.restapi.laundry.dto.LaundryDTO;
import aircleanprojectback.restapi.laundry.dto.LaundryWayDTO;
import aircleanprojectback.restapi.laundry.dto.WaterTankDTO;
import aircleanprojectback.restapi.laundry.entity.Laundry;
import aircleanprojectback.restapi.laundry.entity.LaundryWay;
import aircleanprojectback.restapi.laundry.entity.WaterTank;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import org.springframework.ui.Model;
//
import java.util.List;
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

    @Autowired
    public FacilityService(FacilityLaundryRepository facilityLaundryRepository, FacilityLaundryWayRepository facilityLaundryWayRepository ,FacilityDetailOnlyRepository facilityDetailOnlyRepository ,ModelMapper modelMapper, FacilityDetailRepository facilityDetailRepository, UpdatateWaterTanckRepository updatateWaterTanckRepository){
        this.modelMapper = modelMapper;
        this.facilityDetailRepository = facilityDetailRepository;
        this.updatateWaterTanckRepository = updatateWaterTanckRepository;
        this.facilityDetailOnlyRepository = facilityDetailOnlyRepository;
        this.facilityLaundryRepository = facilityLaundryRepository;
        this.facilityLaundryWayRepository = facilityLaundryWayRepository;

    }

    public List<FacilityDetailDTO> findFacilityByBranchCode(String branchCode) {

        List<FacilityDetail> result = facilityDetailRepository.findAllByBranchBranchCode(branchCode);

        result.forEach(System.out::println);

        List<FacilityDetailDTO> facilityList = result.stream().map(facility -> modelMapper.map(facility, FacilityDetailDTO.class)).collect(Collectors.toList());

        facilityList.forEach(System.out::println);

        return facilityList;
    }

    @Transactional
    public void saveUpdaterWaterCapacity(WaterTankDTO waterTankDTO) {

        WaterTank waterTank = updatateWaterTanckRepository.findByBranchCode(waterTankDTO.getBranchCode());

        waterTank = waterTank.toBuilder()
                .waterCurCapacity(waterTankDTO.getWaterCurCapacity())
                .build();

        updatateWaterTanckRepository.save(waterTank);

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
