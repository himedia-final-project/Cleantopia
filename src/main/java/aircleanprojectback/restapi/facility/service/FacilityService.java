package aircleanprojectback.restapi.facility.service;
//
//import aircleanprojectback.restapi.branch.dto.FacilityDetailDTO;
//import aircleanprojectback.restapi.branch.entity.FacilityDetail;
//import aircleanprojectback.restapi.facility.repository.BranchFacilityDetailRepository;
//import aircleanprojectback.restapi.facility.repository.FindBranchInfoRepository;
import aircleanprojectback.restapi.branch.dto.FacilityDetailDTO;
import aircleanprojectback.restapi.branch.entity.FacilityDetail;
import aircleanprojectback.restapi.branchOrigin.dao.FacilityDetailRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.ui.Model;
//
import java.util.List;
import java.util.stream.Collectors;
//
@Service
@Slf4j
public class FacilityService {
//
//    private final BranchFacilityDetailRepository branchFacilityDetailRepository;
//
//    private final FindBranchInfoRepository findBranchInfoRepository;
    private final ModelMapper modelMapper;
    private final FacilityDetailRepository facilityDetailRepository;
//
    @Autowired
    public FacilityService(ModelMapper modelMapper, FacilityDetailRepository facilityDetailRepository){
        this.modelMapper = modelMapper;
        this.facilityDetailRepository = facilityDetailRepository;
    }

    public List<FacilityDetailDTO> findFacilityByBranchCode(String branchCode) {

        List<FacilityDetail> result = facilityDetailRepository.findAllByBranchBranchCode(branchCode);

        result.forEach(System.out::println);

        List<FacilityDetailDTO> facilityList = result.stream().map(facility -> modelMapper.map(facility, FacilityDetailDTO.class)).collect(Collectors.toList());

        facilityList.forEach(System.out::println);

        return facilityList;
    }
//    public FacilityService(ModelMapper modelMapper, BranchFacilityDetailRepository branchFacilityDetailRepository, FindBranchInfoRepository findBranchInfoRepository) {
//        this.branchFacilityDetailRepository = branchFacilityDetailRepository;
//        this.findBranchInfoRepository = findBranchInfoRepository;
//        this.modelMapper = modelMapper;
//    }
//    public List<FacilityDetailDTO> getFacilityDetailInfo(String sub) {
//
//        List<String> branchCodesListEntity = findBranchInfoRepository.findBranchCodesByMemberId(sub);
//
//        List<FacilityDetail> facilityDetails = branchFacilityDetailRepository.findByBranchCodes(branchCodesListEntity);
//
//        return facilityDetails.stream()
//                .map(facilityDetail -> modelMapper.map(facilityDetail, FacilityDetailDTO.class))
//                .collect(Collectors.toList());
//    }
}
