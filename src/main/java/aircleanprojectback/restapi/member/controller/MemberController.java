package aircleanprojectback.restapi.member.controller;

import aircleanprojectback.restapi.common.dto.ResponseDTO;
import aircleanprojectback.restapi.member.dto.AskDTO;
import aircleanprojectback.restapi.member.dto.MemberDTO;
import aircleanprojectback.restapi.member.entity.Ask;
import aircleanprojectback.restapi.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping("message")
    public ResponseEntity<ResponseDTO> getAllMessage(){

        Map<String,Object> messageList = memberService.getAllMessage();

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"메시지 제공",messageList));
    }

    @PutMapping("message")
    public ResponseEntity<ResponseDTO> changePassword(@RequestBody String memberId){
        System.out.println("changePassword 동작함");
        System.out.println("memberId = " + memberId);

        memberService.changePassword(memberId);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK,"비밀번호 변경 완료",memberId));
    }


}
