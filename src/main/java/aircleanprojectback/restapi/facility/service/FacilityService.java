package aircleanprojectback.restapi.facility.service;
//
//import aircleanprojectback.restapi.branch.dto.FacilityDetailDTO;
//import aircleanprojectback.restapi.branch.entity.FacilityDetail;
//import aircleanprojectback.restapi.facility.repository.BranchFacilityDetailRepository;
//import aircleanprojectback.restapi.facility.repository.FindBranchInfoRepository;
import aircleanprojectback.restapi.branch.dto.FacilityDetailDTO;
import aircleanprojectback.restapi.branch.entity.FacilityDetail;
import aircleanprojectback.restapi.branchOrigin.dao.FacilityDetailRepository;
import aircleanprojectback.restapi.facility.repository.UpdatateWaterTanckRepository;
import aircleanprojectback.restapi.laundry.dto.WaterTankDTO;
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

    @Autowired
    public FacilityService(ModelMapper modelMapper, FacilityDetailRepository facilityDetailRepository, UpdatateWaterTanckRepository updatateWaterTanckRepository){
        this.modelMapper = modelMapper;
        this.facilityDetailRepository = facilityDetailRepository;
        this.updatateWaterTanckRepository = updatateWaterTanckRepository;
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
}
