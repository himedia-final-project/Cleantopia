package aircleanprojectback.restapi.branch.service;

import aircleanprojectback.restapi.branch.dto.BranchDTO;
import aircleanprojectback.restapi.branch.repository.BranchRepository;
import aircleanprojectback.restapi.member.entity.Branch;
import aircleanprojectback.restapi.util.FileUploadUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class BranchService {

    private final BranchRepository branchRepository;
    private final ModelMapper modelMapper;
    private static final String IMAGE_DIR = "uploads/";

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

    @Transactional
    public void deleteBranchs(List<String> branches) {
        for (String branchName : branches) {
            if ("영등포구 1호점".equals(branchName)) {
                continue; // '영등포구 1호점'은 삭제하지 않음
            }
            branchRepository.deleteByBranchName(branchName);
        }
    }

    public void saveBranch(BranchDTO branchDTO) throws IOException {
        String branchImage = null;
        MultipartFile branchImageFile = branchDTO.getBranchImageFile();
        if (branchImageFile != null && !branchImageFile.isEmpty()) {
            String imageName = UUID.randomUUID().toString().replace("-", "");
            branchImage = FileUploadUtils.saveFile(IMAGE_DIR, imageName, branchImageFile);
        }

        // 문자열 형식의 날짜를 java.sql.Date로 변환
        String branchOpenDateStr = branchDTO.getBranchOpenDate();
        java.sql.Date branchOpenDate = null;
        if (branchOpenDateStr != null && !branchOpenDateStr.isEmpty()) {
            branchOpenDate = Date.valueOf(branchOpenDateStr); // 문자열을 java.sql.Date로 변환
        }

        Branch branch = Branch.builder()
                .branchCode(branchDTO.getBranchCode())
                .branchRegion(branchDTO.getBranchRegion())
                .branchName(branchDTO.getBranchName())
                .branchPhone(branchDTO.getBranchPhone())
                .branchAddress(branchDTO.getBranchAddress())
                .branchImage(branchImage)
                .branchOpenDate(branchOpenDate) // 변환된 날짜 설정
                .memberId(branchDTO.getMemberId())
                .build();

        branchRepository.save(branch);
    }
    
}
