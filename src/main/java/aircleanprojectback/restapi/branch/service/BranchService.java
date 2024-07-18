package aircleanprojectback.restapi.branch.service;

import aircleanprojectback.restapi.branch.repository.BranchRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService {

    private final BranchRepository branchRepository;
    private final ModelMapper modelMapper;

    public BranchService(BranchRepository branchRepository, ModelMapper modelMapper) {
        this.branchRepository = branchRepository;
        this.modelMapper = modelMapper;
    }

    // 전체조회
    public List<String> branchList() {
        List<String> branchNames = branchRepository.findAllBranchNames();

        // 문자열 리스트를 BranchDTO 리스트로 변환
        return branchNames;
    }




//    public List<BranchDTO> branchinformation(String memberId) {
//        List<BranchEntity> branchList = branchRepository.findAllByMemberId(memberId);
//
//        // 결과가 없을 경우 기본 branch_code로 조회
//        if (branchList.isEmpty()) {
//            Optional<BranchEntity> defaultBranch = branchRepository.findByBranchCode("BR009");
//            if (defaultBranch.isPresent()) {
//                branchList.add(defaultBranch.get());
//            }
//        }
//
//        return branchList.stream()
//                .map(branch -> modelMapper.map(branch, BranchDTO.class))
//                .collect(Collectors.toList());
//    }
}
