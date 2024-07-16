package aircleanprojectback.restapi.member.controller;

import aircleanprojectback.restapi.common.dto.ResponseDTO;
import aircleanprojectback.restapi.member.dto.AskDTO;
import aircleanprojectback.restapi.member.dto.MemberDTO;
import aircleanprojectback.restapi.member.entity.Ask;
import aircleanprojectback.restapi.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @PostMapping("/inquiry")
    public ResponseEntity<ResponseDTO> askMember(@RequestBody AskDTO askDTO){
        System.out.println("문의 한 값" + askDTO);

        ResponseDTO responseDTO =
                memberService.findByMemberNameAndMemberEmailAndMemberPhoneNumberAndMemberRole(askDTO);

        System.out.println("responseDTO = " + responseDTO);

        if(responseDTO.getStatus()==500){
            return ResponseEntity.badRequest().body(responseDTO);
        }

        MemberDTO memberDTO = (MemberDTO) responseDTO.getData();

        System.out.println("memberDTO = " + memberDTO);
        
        Ask ask = new Ask();

        ask.memberId(memberDTO.getMemberId())
                        .askStatus("Y")
                                .askDescription(askDTO.getAskDescription())
                                        .memberEmail(askDTO.getMemberEmail()).builder();

        memberService.save(ask);



        return ResponseEntity.ok()
                .body(responseDTO);
    }


}
