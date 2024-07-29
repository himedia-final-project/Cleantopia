package aircleanprojectback.restapi.member.service;

import aircleanprojectback.restapi.common.dto.Criteria;
import aircleanprojectback.restapi.member.dto.*;
import aircleanprojectback.restapi.member.entity.BranchAndMembers;
import aircleanprojectback.restapi.member.entity.BranchOwner;
import aircleanprojectback.restapi.member.entity.Members;
import aircleanprojectback.restapi.member.entity.MembersAndEmployee;
import aircleanprojectback.restapi.member.repository.BranchAndMembersRepository;
import aircleanprojectback.restapi.member.repository.MemberRepository;
import aircleanprojectback.restapi.member.repository.MembersAndEmployeeRepository;
//import aircleanprojectback.restapi.member.repository.OwnerRepository;
import aircleanprojectback.restapi.util.FileUploadUtils;
import aircleanprojectback.restapi.util.MakeMemberId;
import aircleanprojectback.restapi.util.RandomStringGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HumanResourceService {
    private final MembersAndEmployeeRepository repository;
    private final MemberRepository memberRepository;
    private final BranchAndMembersRepository branchRepository;
//    private final OwnerRepository ownerRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    @Value("${image.image-url}")
    private String IMAGE_URL;

    @Value("${image.image-dir}")
    private String IMAGE_DIR;

    @Autowired
    public HumanResourceService(MembersAndEmployeeRepository repository, ModelMapper modelMapper,MemberRepository memberRepository,PasswordEncoder passwordEncoder
    ,BranchAndMembersRepository branchRepository){
        this.memberRepository = memberRepository;
        this.repository =repository;
//        this.ownerRepository = ownerRepository;
        this.modelMapper=modelMapper;
        this.passwordEncoder=passwordEncoder;
        this.branchRepository = branchRepository;

        modelMapper.createTypeMap(MembersAndEmployee.class, MembersAndEmployeeDTO.class)
                .addMapping(src -> src.getMembers(), MembersAndEmployeeDTO::setMemberDTO);
    }
    @Transactional
    public Page<MembersAndEmployeeDTO> getEmployeeListWithPaging(Criteria cri) {
        System.out.println("getEmployeeListWithPaging 동작");



        int index = cri.getPageNum() -1 ;
        int count= cri.getAmount();

        Pageable paging = PageRequest.of(index,count, Sort.by("employee_dept"));

        Page<MembersAndEmployee> result = repository.findByMemberRoleAndMemberStatus("e","Y",paging);

        System.out.println("result = " + result.getContent());

        System.out.println("==============000=======================");
        // modelMapper 매핑 규칙 추가 Mebers 를 MemberDTO 에 매핑 처리



        System.out.println("======================111================");
        Page<MembersAndEmployeeDTO> employeeList = result.map(member->modelMapper.map(member,MembersAndEmployeeDTO.class));

        for(int i =0 ;i<employeeList.toList().size() ;i++){
            employeeList.toList().get(i).getMemberDTO().setMemberImage(IMAGE_URL+employeeList.toList().get(i).getMemberDTO().getMemberImage());
        }
        System.out.println("======================222================");

        System.out.println("employeeList = " + employeeList);
        System.out.println("직원 조회 성공");

        return employeeList;
    }

    @Transactional
    public void findEmployeeById(List<String> deleteMember) {

        List<Members> members = memberRepository.findByMemberIdIn(deleteMember);

        members.forEach(member->member.memberStatus("N").builder());

        members.forEach(System.out::println);

    }

    @Transactional
    public void modifyEmployeeInfo(int employeeCode, MemberModifyDTO memberModifyDTO, MultipartFile image) {

        System.out.println("modifyEmployeeInfo 동작");

        Optional<MembersAndEmployee> modifyEmployee = repository.findById(employeeCode);

        String imageName = UUID.randomUUID().toString().replace("-","");
        String replaceFileName = null;
        int result = 0;
        if(image!=null){
            try{

                System.out.println("이미지 있는가?");
                replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR,imageName,image);

                modifyEmployee.get().getMembers().memberImage(replaceFileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        MemberDTO memberDTO = new MemberDTO();
        if(memberModifyDTO.getIsPass()){
            String newPass = RandomStringGenerator.getPassword();
            System.out.println("newPass = " + newPass);
            modifyEmployee.get().getMembers().memberPassword(passwordEncoder.encode(newPass)).builder();
        }
        if(memberModifyDTO.getMemberName()!=null){
            modifyEmployee.get().getMembers().memberName(memberModifyDTO.getMemberName()).builder();
        }
        if(memberModifyDTO.getEmail()!=null){
            modifyEmployee.get().getMembers().memberEmail(memberModifyDTO.getEmail()).builder();
        }
        if(memberModifyDTO.getPhone()!=null){
            modifyEmployee.get().getMembers().memberPhoneNumber(memberModifyDTO.getPhone()).builder();
        }
        if(memberModifyDTO.getAddress()!=null){
            modifyEmployee.get().getMembers().memberAddress(memberModifyDTO.getAddress()).builder();
        }
        if(memberModifyDTO.getDept()!=null){
            modifyEmployee.get().employeeDept(memberModifyDTO.getDept()).builder();
        }
        if(memberModifyDTO.getPosition()!=null){
            modifyEmployee.get().employeePosition(memberModifyDTO.getPosition()).builder();
        }





    }

//    이미지 관련
    @Transactional
    public void registEmployee(MemberDTO memberDTO, EmployeeDTO employeeDTO, MultipartFile image) {

        Members newMembers = modelMapper.map(memberDTO,Members.class);

        String memberId = memberRepository.findLastMemberId(memberDTO.getMemberRole());

        System.out.println("memberId = " + memberId);

        memberId = MakeMemberId.incrementString(memberId);

        newMembers.memberId(memberId);
        String newPass = RandomStringGenerator.getPassword();

        System.out.println("newPass = " + newPass);

        newMembers.memberPassword(passwordEncoder.encode(newPass));

        //이미지
        String imageName = UUID.randomUUID().toString().replace("-","");
        String replaceFileName = null;
        if(image!=null){
            try{

                System.out.println("이미지 있는가?");
                replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR,imageName,image);

                newMembers.memberImage(replaceFileName);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        memberRepository.save(newMembers);


        MembersAndEmployee registMember = new MembersAndEmployee();

        registMember.employeePosition(employeeDTO.getEmployeePosition());
        registMember.employeeDept(employeeDTO.getEmployeeDept());

        registMember.members(newMembers);


        repository.save(registMember);
    }

    public Page<MembersAndEmployeeDTO> getEmployeeWhereY(Criteria cri) {

        System.out.println("getEmployeeWhereY 동작");

        int index = cri.getPageNum() -1 ;
        int count= cri.getAmount();

        Pageable paging = PageRequest.of(index,count, Sort.by("employee_dept"));

        Page<MembersAndEmployee> result = repository.findByMemberRoleAndMemberStatus("e","N",paging);

        System.out.println("result = " + result.getContent());

        Page<MembersAndEmployeeDTO> employeeList = result.map(member->modelMapper.map(member,MembersAndEmployeeDTO.class));

        for(int i =0 ;i<employeeList.toList().size() ;i++){
            employeeList.toList().get(i).getMemberDTO().setMemberImage(IMAGE_URL+employeeList.toList().get(i).getMemberDTO().getMemberImage());
        }
        System.out.println("employeeList = " + employeeList);
        System.out.println("직원 조회 성공");

        return employeeList;

    }

    public Page<BranchAndMembersDTO> getBranchListWithPaging(Criteria cri) {
        int index = cri.getPageNum()-1;
        int amount = cri.getAmount();
        Pageable pageable = PageRequest.of(index,amount);

        Page<BranchAndMembers> result = branchRepository.findAllByMemberRoleAndMemberStatus("b","Y",pageable);

        System.out.println("branch"+result.getContent());

        Page<BranchAndMembersDTO> branchList =result.map(branch-> modelMapper.map(branch,BranchAndMembersDTO.class));


        for(int i =0 ;i<branchList.toList().size() ;i++){
            branchList.toList().get(i).setMemberImage(IMAGE_URL+branchList.toList().get(i).getMemberImage());
        }


        return branchList;
    }

//    @Transactional
//    public Page<BranchOwnerDTO> getBranchAndMemberWithPage(Criteria cri) {
//
//        Pageable pageable = PageRequest.of(cri.getPageNum()-1, cri.getAmount() );
//
//
//        Page<BranchOwner> result = ownerRepository.findAllOwner(pageable);
//
//
//        return result.map(owner -> modelMapper.map(owner,BranchOwnerDTO.class));
//    }
}
