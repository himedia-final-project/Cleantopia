package aircleanprojectback.restapi.member.service;

import aircleanprojectback.restapi.branch.repository.BranchRepository;
import aircleanprojectback.restapi.common.dto.Criteria;
import aircleanprojectback.restapi.member.dto.*;
import aircleanprojectback.restapi.member.entity.*;
import aircleanprojectback.restapi.member.repository.DriverRepository;
import aircleanprojectback.restapi.member.repository.MemberRepository;
import aircleanprojectback.restapi.member.repository.MembersAndEmployeeRepository;
import aircleanprojectback.restapi.member.repository.OwnerRepository;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HumanResourceService {
    private final MembersAndEmployeeRepository repository;
    private final MemberRepository memberRepository;
    private final BranchRepository branchRepository;
    private final OwnerRepository ownerRepository;
    private final DriverRepository driverRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    @Value("${image.image-url}")
    private String IMAGE_URL;

    @Value("${image.image-dir}")
    private String IMAGE_DIR;

    @Autowired
    public HumanResourceService(MembersAndEmployeeRepository repository, ModelMapper modelMapper,MemberRepository memberRepository,PasswordEncoder passwordEncoder
    ,BranchRepository branchRepository, OwnerRepository ownerRepository, DriverRepository driverRepository){
        this.memberRepository = memberRepository;
        this.repository =repository;
        this.ownerRepository = ownerRepository;
        this.modelMapper=modelMapper;
        this.passwordEncoder=passwordEncoder;
        this.branchRepository = branchRepository;
        this.driverRepository = driverRepository;

        modelMapper.createTypeMap(MembersAndEmployee.class, MembersAndEmployeeDTO.class)
                .addMapping(src -> src.getMembers(), MembersAndEmployeeDTO::setMemberDTO);

        modelMapper.createTypeMap(BranchOwner.class,BranchOwnerDTO.class)
                .addMapping(src -> src.getMembers(), BranchOwnerDTO::setMemberDTO)
                .addMapping(src -> src.getBranch(),BranchOwnerDTO::setBranchDTO);
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

    public Page<MembersAndEmployeeDTO> getEmployeeWhereN(Criteria cri) {

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

//    public Page<BranchAndMembersDTO> getBranchListWithPaging(Criteria cri) {
//        int index = cri.getPageNum()-1;
//        int amount = cri.getAmount();
//        Pageable pageable = PageRequest.of(index,amount);
//
//        Page<BranchAndMembers> result = branchRepository.findAllByMemberRoleAndMemberStatus("b","Y",pageable);
//
//        System.out.println("branch"+result.getContent());
//
//        Page<BranchAndMembersDTO> branchList =result.map(branch-> modelMapper.map(branch,BranchAndMembersDTO.class));
//
//
//        for(int i =0 ;i<branchList.toList().size() ;i++){
//            branchList.toList().get(i).setMemberImage(IMAGE_URL+branchList.toList().get(i).getMemberImage());
//        }
//
//
//        return branchList;
//    }

    @Transactional
    public Page<BranchOwnerDTO> getBranchAndMemberWithPage(Criteria cri) {

        System.out.println("getBranchAndMemberWithPage 동작");
        int index = cri.getPageNum()-1;
        int count = cri.getAmount();

        Pageable pageable = PageRequest.of(index,count);

        String role = "b";
        String status = "Y";

        Page<BranchOwner> result = ownerRepository.findAllLeftJoin(role,status,pageable);

        System.out.println("result.getContent() = " + result.getContent());

        Page<BranchOwnerDTO> list= result.map(branchOwner -> modelMapper.map(branchOwner,BranchOwnerDTO.class));



        for(int i =0 ;i<list.toList().size() ;i++){
            list.toList().get(i).getMemberDTO().setMemberImage(IMAGE_URL+list.toList().get(i).getMemberDTO().getMemberImage());
//            list.toList().get(i).getBranchDTO().setBranchImage(IMAGE_URL+list.toList().get(i).getBranchDTO().getBranchImage());
        }


        System.out.println("list dto 확인"+list.getContent());

        return list;
    }




    public List<BranchDTO> findAllBranchWithoutOwner() {

        List<Branch> result = branchRepository.findAllByOwnerStatus("N");

        return result.stream().map(branch -> modelMapper.map(branch , BranchDTO.class)).collect(Collectors.toList());


    }

    @Transactional
    public void registBranch(RegistBranchManagerDTO memberDTO, MultipartFile image) {

        String memberId = memberRepository.findLastMemberId(memberDTO.getMemberRole());

        memberId = MakeMemberId.incrementString(memberId);
        memberDTO.setMemberId(memberId);
        memberDTO.setMemberPassword(passwordEncoder.encode(RandomStringGenerator.getPassword()));



        Members members = modelMapper.map(memberDTO,Members.class);

        Members newMembers = memberRepository.save(members);

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

        BranchOwner branchOwner = new BranchOwner();

        branchOwner.members(newMembers);

        System.out.println("newMembers = " + newMembers);

        if(memberDTO.getBranchOwnership().equals("Y")){
            Optional<Branch> branch = branchRepository.findById(memberDTO.getBranchCode());

            branch.get().ownerStatus("Y");
            System.out.println("branch = " + branch);

            branchOwner.branch(branch.get());

        }

        BranchOwner newBranchOwner = ownerRepository.save(branchOwner);

        System.out.println("newBranchOwner = " + newBranchOwner);


    }

    @Transactional
    public void findBranchManagerByMemberId(List<String> deleteMember) {

        List<Members> members = memberRepository.findByMemberIdIn(deleteMember);

        members.forEach(member->member.memberStatus("N").builder());

        List<BranchOwner> branchOwners = new ArrayList<>();

        for(Members member : members){

            System.out.println("soft delete member 확인 = " + member);
            if(member.getBranchOwnerShip().equals("Y")){

                System.out.println("branch soft delete = " + member);

                branchOwners.add(ownerRepository.findByMemberId(member.getMemberId()));
            }

            member.branchOwnership("N");
        }



        for(BranchOwner branchOwner : branchOwners){
            branchOwner.members(null);
            branchOwner.getBranch().ownerStatus("N");

            System.out.println("branchOwner 삭제 되었는가 = " + branchOwner);
        }

    }

    public Page<MemberDTO> getBranchWithN(Criteria cri) {

        int index = cri.getPageNum()-1;
        int count = cri.getAmount();
        Pageable pageable = PageRequest.of(index,count);

        Page<Members> result = memberRepository.findAllByMemberRoleAndMemberStatus("b","N",pageable);

        return result.map(r->modelMapper.map(r,MemberDTO.class));
    }

    public BranchOwnerDTO findOwnBranchByMemberId(String memberId) {

        return modelMapper.map(ownerRepository.findByMemberId(memberId),BranchOwnerDTO.class);
    }

    public Page<DriverDTO> findAllDriver(Criteria cri) {

        Pageable pageable = PageRequest.of(cri.getPageNum()-1,cri.getAmount());

//        Page<Driver> result = driverRepository.findAllByMemberStatusAndMemberRole("Y","d",pageable);

        Page<Driver> result = driverRepository.findAllByMembersMemberStatusAndMembersMemberRole("Y","d",pageable);
        System.out.println("잘 들어 왔을가요 ? "+result.getContent());

        return null;
    }

//    public Page<BranchOwnerDTO> getBranchWithN(Criteria cri) {
//
//        int index = cri.getPageNum()-1;
//        int count = cri.getAmount();
//
//        Pageable pageable = PageRequest.of(index,count);
//
//        Page<BranchOwner> result = ownerRepository.findAllByMemberStatusAndMemberRole("N","b",pageable);
//    }
}
