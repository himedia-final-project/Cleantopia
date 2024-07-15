package aircleanprojectback.restapi.auth.handler;

import aircleanprojectback.restapi.common.AuthConstants;
import aircleanprojectback.restapi.member.dto.MemberDTO;
import aircleanprojectback.restapi.member.dto.TokenDTO;
import aircleanprojectback.restapi.util.ConvertUtil;
import aircleanprojectback.restapi.util.TokenUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@Configuration
public class CustomAuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {

        MemberDTO member = ((MemberDTO) authentication.getPrincipal());

        System.out.println("member 들들?"+member);

        HashMap<String,Object> responseMap = new HashMap<>();
        JSONObject jsonValue = null;
        JSONObject jsonObject;
        if(member.getMemberStatus().equals("N")){
            responseMap.put("userInfo", jsonValue);
            responseMap.put("status",500);
            responseMap.put("message","아이디가 삭제된 상태입니다 관리자에게 문의하세요");
        }else{
            String token = TokenUtils.generateJwtToken(member);

            TokenDTO tokenDTO = TokenDTO.builder()
                    .memberName(member.getMemberName())
                    .accessToken(token)
                    .grantType(AuthConstants.TOKEN_TYPE)
                    .build();

            jsonValue = (JSONObject) ConvertUtil.convertObjectToJsonObject(tokenDTO);

            responseMap.put("userInfo",jsonValue);
            responseMap.put("status",200);
            responseMap.put("message","로그인 성공");

        }

        jsonObject = new JSONObject(responseMap);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter printWriter = response.getWriter();
        printWriter.println(jsonObject);
        printWriter.flush();
        printWriter.close();

    }
}
