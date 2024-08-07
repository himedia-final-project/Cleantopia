package aircleanprojectback.restapi.mainpage.model.service;

import aircleanprojectback.restapi.branch.repository.BranchRepository;
import aircleanprojectback.restapi.member.dto.BranchDTO;
import aircleanprojectback.restapi.member.entity.Branch;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MainService {

    private final BranchRepository branchRepository;
    private final ModelMapper modelMapper;

    public MainService(BranchRepository branchRepository, ModelMapper modelMapper){
        this.branchRepository =branchRepository;
        this.modelMapper = modelMapper;
    }


    public List<BranchDTO> getBranch() {

        List<Branch> result = branchRepository.findAll();

        List<BranchDTO> branchList = result.stream().map(branch -> modelMapper.map(branch,BranchDTO.class)).collect(Collectors.toList());

        return branchList;
    }
}
