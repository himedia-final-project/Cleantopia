package aircleanprojectback.restapi.member.service;

import aircleanprojectback.restapi.common.dto.ResponseDTO;
import aircleanprojectback.restapi.member.dto.AskDTO;
import aircleanprojectback.restapi.member.dto.MemberDTO;
import aircleanprojectback.restapi.member.entity.Ask;
import aircleanprojectback.restapi.member.entity.Members;
import aircleanprojectback.restapi.member.repository.AskRepository;
import aircleanprojectback.restapi.member.repository.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository repository;
    private final AskRepository askRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MemberService(MemberRepository repository,AskRepository askRepository,ModelMapper modelMapper){
        this.repository= repository;
        this.askRepository =askRepository;
        this.modelMapper = modelMapper;
    }

    public ResponseDTO findByMemberNameAndMemberEmailAndMemberPhoneNumberAndMemberRole(AskDTO askDTO) {

        List<Members> member = repository.findByMemberNameAndMemberEmailAndMemberPhoneNumberAndMemberRole(
                askDTO.getMemberName(),askDTO.getMemberEmail(),askDTO.getMemberPhone(),askDTO.getMemberRole()
        );

        ResponseDTO responseDTO = new ResponseDTO();
        if(member.isEmpty()){
            responseDTO.setStatus(404);
            responseDTO.setMessage("해당 회원은 존재하지 않습니다");
            responseDTO.setData(null);
        }
        else{
            responseDTO.setStatus(200);
            responseDTO.setMessage(askDTO.getAskDescription());

            MemberDTO memberDTO = modelMapper.map(member.get(0),MemberDTO.class);

            System.out.println("멤버 존재"+memberDTO);

            responseDTO.setData(memberDTO);
        }

        return responseDTO;
    }

    public void save(Ask ask) {

        askRepository.save(ask);

    }
}
