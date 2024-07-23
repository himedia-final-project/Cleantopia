package aircleanprojectback.restapi.member.service;

import aircleanprojectback.restapi.common.dto.Criteria;
import aircleanprojectback.restapi.member.dto.MembersAndBranchDTO;
import aircleanprojectback.restapi.member.dto.MembersAndEmployeeDTO;
import aircleanprojectback.restapi.member.entity.MembersAndBranch;
import aircleanprojectback.restapi.member.entity.MembersAndEmployee;
import aircleanprojectback.restapi.member.repository.MemberRepository;
import aircleanprojectback.restapi.member.repository.MembersAndBranchRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BranchResourceService {

    @Value("${image.image-url}")
    private String IMAGE_URL;

    @Value("${image.image-dir}")
    private String IMAGE_DIR;

    private final MemberRepository memberRepository;
    private final MembersAndBranchRepository repository;
    private final ModelMapper modelMapper;

    @Autowired
    public BranchResourceService(MemberRepository memberRepository,MembersAndBranchRepository repository,ModelMapper modelMapper){
        this.repository=repository;
        this.memberRepository = memberRepository;
        this.modelMapper = modelMapper;

        modelMapper.createTypeMap(MembersAndBranch.class, MembersAndBranchDTO.class)
                .addMapping(src -> src.getMembers(), MembersAndBranchDTO::setMemberDTO);
    }

    @Transactional
    public Page<MembersAndBranchDTO> getBranchListWithPaging(Criteria cri){

        int index = cri.getPageNum()-1;
        int count = cri.getAmount();

        Pageable paging = PageRequest.of(index,count, Sort.by("branch_region"));

        Page<MembersAndBranch> result = repository.findByMemberRoleAndMemberStatus("b","Y",paging);

        Page<MembersAndBranchDTO> branchList = result.map(branch->modelMapper.map(branch,MembersAndBranchDTO.class));

        for(int i = 0 ; i < branchList.toList().size() ; i++){
            branchList.toList().get(i).getMemberDTO().setMemberImage(IMAGE_URL+branchList.toList().get(i).getMemberDTO().getMemberImage());
        }

        return branchList;

    }
}
