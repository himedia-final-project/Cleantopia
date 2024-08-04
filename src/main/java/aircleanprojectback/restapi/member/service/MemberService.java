package aircleanprojectback.restapi.member.service;

import aircleanprojectback.restapi.common.dto.ResponseDTO;
import aircleanprojectback.restapi.member.dto.AlarmDTO;
import aircleanprojectback.restapi.member.dto.AskDTO;
import aircleanprojectback.restapi.member.dto.MemberDTO;
import aircleanprojectback.restapi.member.entity.Ask;
import aircleanprojectback.restapi.member.entity.Members;
import aircleanprojectback.restapi.member.repository.AskRepository;
import aircleanprojectback.restapi.member.repository.MemberRepository;
import aircleanprojectback.restapi.util.RandomStringGenerator;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MemberService {

    @Value("${image.image-dir}")
    private String IMAGE_DIR;
    @Value("${image.image-url}")
    private String IMAGE_URL;

    private final MemberRepository repository;
    private final AskRepository askRepository;
    private final ModelMapper modelMapper;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;

    @Autowired
    public MemberService(MemberRepository repository,AskRepository askRepository,ModelMapper modelMapper
            ,MemberRepository memberRepository, PasswordEncoder passwordEncoder ,JavaMailSender mailSender){
        this.mailSender = mailSender;
        this.memberRepository = memberRepository;
        this.repository= repository;
        this.askRepository =askRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public ResponseDTO findByMemberNameAndMemberEmailAndMemberPhoneNumberAndMemberRole(AskDTO askDTO) {

        List<Members> member = repository.findByMemberNameAndMemberEmailAndMemberPhoneNumberAndMemberRole(
                askDTO.getMemberName(),askDTO.getMemberEmail(),askDTO.getMemberPhone(),askDTO.getMemberRole()
        );

        ResponseDTO responseDTO = new ResponseDTO();
        if(member.isEmpty()){
            responseDTO.setStatus(500);
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

    @Transactional
    public void save(Ask ask) {

        askRepository.save(ask);

    }

    public Map<String,Object> getAllMessage() {

//        List<Object> messageList = new ArrayList<>();

        Map<String , Object> messageList = new HashMap<>();

        // 문의사항
        List<Ask> result = askRepository.findAllByAskStatus("Y");

        List<AlarmDTO> askList = result.stream().map(ask->modelMapper.map(ask,AlarmDTO.class)).collect(Collectors.toList());

        for(AlarmDTO ask : askList){
            Optional<Members> members = memberRepository.findById(ask.getMemberId());

            ask.setMemberName(members.get().getMemberName());
            ask.setMemberImage(IMAGE_URL+members.get().getMemberImage());
        }

        messageList.put("ask",askList);

        return messageList;
    }

    @Transactional
    public void changePassword(String memberId) {

        Members member = memberRepository.findByMemberIdAndMemberStatus(memberId,"Y");

        System.out.println("member = " + member);

        SimpleMailMessage message = new SimpleMailMessage();
        if(Objects.isNull(member)){

            
            message.setTo(member.getMemberEmail());
            message.setSubject("안녕하세요 Airclean 입니다");
            message.setText("귀하의 비밀번호 변경 요청을 받았습니다\n" +
                    "회원의 정보가 삭제 되었습니다. 추가적인 문의사항이 있는경우 \n" +
                    "010-1234-1234 로 문의해주세요");

        }else{
            String newPass = RandomStringGenerator.getPassword();

            List<Ask> ask = askRepository.findAllByMemberId(memberId);

            System.out.println("ask = " + ask);

            askRepository.deleteAll(ask);

            System.out.println("newPass = " + newPass);

            member.memberPassword(passwordEncoder.encode(newPass));

            System.out.println("member = " + member);


            message.setTo(member.getMemberEmail());
            message.setSubject("안녕하세요 Airclean 입니다");
            message.setText("귀하의 비밀번호 변경 요청을 받아 비밀번호를 변경했습니다\n" +
                    "변경된 비밀번호는 "+newPass+" 입니다 ");

        }



        mailSender.send(message);


    }
}
