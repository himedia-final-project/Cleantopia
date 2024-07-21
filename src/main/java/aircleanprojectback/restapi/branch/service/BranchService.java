package aircleanprojectback.restapi.branch.service;

import aircleanprojectback.restapi.branch.dto.BranchDTO;
import aircleanprojectback.restapi.branch.repository.BranchRepository;
import aircleanprojectback.restapi.member.entity.Branch;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BranchService {

    private final BranchRepository branchRepository;
    private final ModelMapper modelMapper;

    public BranchService(BranchRepository branchRepository, ModelMapper modelMapper) {
        this.branchRepository = branchRepository;
        this.modelMapper = modelMapper;
    }

    // 전체조회
    public List<String> branchList(String locationName) {


        if (locationName == null || locationName.isEmpty()) {
            return branchRepository.findAllBranchNames();
        } else {
            return branchRepository.findBranchNames(locationName);
        }

//        System.out.println("branchNames = " + branchNames);
//        // 문자열 리스트를 BranchDTO 리스트로 변환
//        return branchNames;
    }


    public Map<String, Integer> mapCounts() {
        List<Object[]> results = branchRepository.selectMapCounts();
        Map<String, Integer> branchCounts = new HashMap<>();

        for (Object[] result : results) {
            String region = (String) result[0];
            Long count = ((Number) result[1]).longValue();
            branchCounts.put(region, count.intValue());
        }

        // Debugging: Print the processed results
        branchCounts.forEach((region, count) -> {
            System.out.println("Region: " + region + ", Count: " + count);
        });

        return branchCounts;
    }

    
    public List<BranchDTO> defaltBranchinformation(String memberId) {
        List<Branch> branchList = branchRepository.findAllByMemberId(memberId);

        if (branchList.isEmpty()) {
            Optional<Branch> defaultBranch = branchRepository.findByBranchCode("BR009");
            defaultBranch.ifPresent(branchList::add);
        }

        return branchList.stream()
                .map(branch -> modelMapper.map(branch, BranchDTO.class))
                .collect(Collectors.toList());
    }





//    버튼 눌렀을때

    public List<BranchDTO> branchInformation(String branchName) {
        List<Branch> branchList = branchRepository.findAllBranchName(branchName);

        return branchList.stream()
                .map(branch -> modelMapper.map(branch, BranchDTO.class))
                .collect(Collectors.toList());
    }
}
