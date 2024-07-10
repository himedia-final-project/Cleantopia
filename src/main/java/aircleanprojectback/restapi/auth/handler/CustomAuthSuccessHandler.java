package aircleanprojectback.restapi.auth.handler;

import aircleanprojectback.restapi.member.dto.MemberDTO;
import aircleanprojectback.restapi.util.TokenUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import java.io.IOException;
import java.util.HashMap;

@Configuration
public class CustomAuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {

        MemberDTO member = ((MemberDTO) authentication.getPrincipal());

        HashMap<String,Object> responseMap = new HashMap<>();
        JSONObject jsonValue = null;
        JSONObject jsonObject;
        if(member.getMemberStatus().equals("N")){
            responseMap.put("userInfo", jsonValue);
            responseMap.put("status",500);
            responseMap.put("message","아이디가 삭제된 상태입니다 관리자에게 문의하세요");
        }else{
            String token = TokenUtils.generateJwtToken(member);
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
