package aircleanprojectback.restapi.branchOrigin.service;

import aircleanprojectback.restapi.branch.dto.FacilityDetailDTO;
import aircleanprojectback.restapi.branch.entity.FacilityDetail;
import aircleanprojectback.restapi.branch.repository.BranchRepository;
import aircleanprojectback.restapi.branchOrigin.dao.BranchPageRepository;
import aircleanprojectback.restapi.branchOrigin.dao.FacilityDetailRepository;
import aircleanprojectback.restapi.branchOrigin.dto.BranchOwnerPageDTO;
import aircleanprojectback.restapi.branchOrigin.dto.BranchPageDTO;
import aircleanprojectback.restapi.branchOrigin.dto.MembersPageDTO;
import aircleanprojectback.restapi.branchOrigin.entity.BranchOwnerPage;
import aircleanprojectback.restapi.branchOrigin.entity.BranchPage;
import aircleanprojectback.restapi.member.dto.BranchDTO;
import aircleanprojectback.restapi.member.dto.MemberDTO;
import aircleanprojectback.restapi.member.entity.Branch;
import aircleanprojectback.restapi.member.entity.BranchOwner;
import aircleanprojectback.restapi.member.entity.Members;
import aircleanprojectback.restapi.member.repository.MemberRepository;
import aircleanprojectback.restapi.member.repository.OwnerRepository;
import aircleanprojectback.restapi.util.FileUploadUtils;
import aircleanprojectback.restapi.util.MakeMemberId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BranchService {

    @Value("${image.image-url}")
    private String IMAGE_URL;

    @Value("${image.image-dir}")
    private String IMAGE_DIR;

    private final BranchPageRepository branchPageRepository;
    private final FacilityDetailRepository facilityDetailRepository;
    private final ModelMapper modelMapper;
    private final OwnerRepository ownerRepository;
    private final BranchRepository branchRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public BranchService(BranchPageRepository branchPageRepository, ModelMapper modelMapper, FacilityDetailRepository facilityDetailRepository,
    OwnerRepository ownerRepository,BranchRepository branchRepository, MemberRepository memberRepository){
        this.branchPageRepository =branchPageRepository;
        this.modelMapper = modelMapper;
        this.facilityDetailRepository = facilityDetailRepository;
        this.ownerRepository = ownerRepository;
        this.branchRepository =branchRepository;
        this.memberRepository = memberRepository;

        modelMapper.createTypeMap(BranchPage.class, BranchPageDTO.class)
                .addMapping(src -> src.getBranchOwnerPageList(), BranchPageDTO::setBranchOwnerPageDTOS);
        modelMapper.createTypeMap(BranchOwnerPage.class,BranchOwnerPageDTO.class)
                .addMapping(src-> src.getMembersPage(),BranchOwnerPageDTO::setMembersPageDTO);


        modelMapper.createTypeMap(FacilityDetail.class, FacilityDetailDTO.class)
                .addMapping(src-> src.getBranch(), FacilityDetailDTO::setBranchDTO)
                .addMapping(src-> src.getFacility(),FacilityDetailDTO::setFacilityDTO);
    }


    public List<BranchPageDTO> getBranchInfo() {

        List<BranchPage> result = branchPageRepository.findLeftJoin();

        result.forEach(System.out::println);

        List<BranchPageDTO> branchList = result.stream().map(r-> modelMapper.map(r,BranchPageDTO.class)).collect(Collectors.toList());

        for (BranchPageDTO branchPageDTO : branchList) {
            String branchImage = branchPageDTO.getBranchImage();
            if(!branchImage.startsWith("http")){
                branchPageDTO.setBranchImage(IMAGE_URL + branchPageDTO.getBranchImage());
            }
            if (!branchPageDTO.getBranchOwnerPageDTOS().isEmpty()) {
                MembersPageDTO membersPageDTO = branchPageDTO.getBranchOwnerPageDTOS().get(0).getMembersPageDTO();
                if (membersPageDTO != null) {
                    membersPageDTO.setMemberImage(IMAGE_URL + membersPageDTO.getMemberImage());
                }
            }
        }

        branchList.forEach(System.out::println);

        return branchList;
    }

    public List<FacilityDetailDTO> findFacilityByBranchCode(String branchCode) {

            List<FacilityDetail> result = facilityDetailRepository.findAllByBranchBranchCode(branchCode);
            result.forEach(System.out::println);

            List<FacilityDetailDTO> facilityDetailDTOS = result.stream().map(r->modelMapper.map(r,FacilityDetailDTO.class)).collect(Collectors.toList());
            facilityDetailDTOS.forEach(System.out::println);

            return facilityDetailDTOS;

    }

    @Transactional
    public void deleteBranch(List<String> branchCodeList) {

        List<BranchOwner> branchOwners = ownerRepository.findAllByBranchBranchCodeIn(branchCodeList);

        List<Members> members= new ArrayList<>();

        //지점장 지점 보유 여부 변경
        branchOwners.forEach(owner -> members.add(owner.getMembers()));

        members.forEach(member-> member.branchOwnership("N"));

        members.forEach(System.out::println);

        // 지점 OwnerBranch 에서 삭제
        branchOwners.forEach(owner -> owner.branch(null));

        List<FacilityDetail> facilityDetails = facilityDetailRepository.findAllByBranchBranchCodeIn(branchCodeList);


        memberRepository.saveAll(members);
        ownerRepository.saveAll(branchOwners);
        facilityDetailRepository.deleteAll(facilityDetails);
        memberRepository.flush();
        ownerRepository.flush();
        facilityDetailRepository.flush();

        //branch 삭제
        branchRepository.deleteAllByIdInBatch(branchCodeList);


    }

    public List<MemberDTO> getManager() {

        List<Members> result = memberRepository.findAllByMemberRoleAndBranchOwnership("b","N");

        result.forEach(System.out::println);

        return result.stream().map(r->modelMapper.map(r,MemberDTO.class)).collect(Collectors.toList());
    }

    @Transactional
    public String registBranch(BranchDTO branchDTO, String memberId, MultipartFile image) {

        branchDTO.setOwnerStatus("Y");

        String lastBranchCode = branchRepository.getLastBranchCode();

        System.out.println("lastBranchCode = " + lastBranchCode);

        lastBranchCode = MakeMemberId.incrementString(lastBranchCode);
        branchDTO.setBranchCode(lastBranchCode);

        String imageName = UUID.randomUUID().toString().replace("-","");
        String replaceFileName = null;
        if(image!=null){
            try{
                replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR,imageName,image);
                branchDTO.setBranchImage(replaceFileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        Branch newBranch = modelMapper.map(branchDTO,Branch.class);

        branchRepository.save(newBranch);
        branchRepository.flush();

        BranchOwner branchOwner = ownerRepository.findByMemberId(memberId);
        Members member = branchOwner.getMembers();
        member.branchOwnership("Y");
        memberRepository.save(member);
        memberRepository.flush();
        branchOwner.branch(newBranch);


        return lastBranchCode;
    }
}
