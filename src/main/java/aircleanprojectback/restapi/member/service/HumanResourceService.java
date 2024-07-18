package aircleanprojectback.restapi.member.service;

import aircleanprojectback.restapi.common.dto.Criteria;
import aircleanprojectback.restapi.member.dto.MembersAndEmployeeDTO;
import aircleanprojectback.restapi.member.entity.MembersAndEmployee;
import aircleanprojectback.restapi.member.repository.MembersAndEmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HumanResourceService {
    private final MembersAndEmployeeRepository repository;
    private final ModelMapper modelMapper;

    @Autowired
    public HumanResourceService(MembersAndEmployeeRepository repository, ModelMapper modelMapper){
        this.repository =repository;
        this.modelMapper=modelMapper;
    }
    @Transactional
    public Page<MembersAndEmployeeDTO> getEmployeeListWithPaging(Criteria cri) {
        System.out.println("getEmployeeListWithPaging 동작");

        try {
            modelMapper.createTypeMap(MembersAndEmployee.class, MembersAndEmployeeDTO.class)
                    .addMapping(src -> src.getMembers(), MembersAndEmployeeDTO::setMemberDTO);
        } catch (Exception e) {
            System.out.println("ModelMapper 매핑 설정 오류: " + e.getMessage());
            e.printStackTrace();
        }

        int index = cri.getPageNum() -1 ;
        int count= cri.getAmount();

        Pageable paging = PageRequest.of(index,count, Sort.by("employee_dept"));

        Page<MembersAndEmployee> result = repository.findByMemberRoleAndMemberStatus("e","Y",paging);

        System.out.println("result = " + result.getContent());

        System.out.println("==============000=======================");
        // modelMapper 매핑 규칙 추가 Mebers 를 MemberDTO 에 매핑 처리



        System.out.println("======================111================");
        Page<MembersAndEmployeeDTO> employeeList = result.map(member->modelMapper.map(member,MembersAndEmployeeDTO.class));

        System.out.println("======================222================");

        System.out.println("employeeList = " + employeeList);
        System.out.println("직원 조회 성공");

        return employeeList;
    }
}
