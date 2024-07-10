package aircleanprojectback.restapi.auth.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class CustomAuthFailUserHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        JSONObject jsonObject;
        String failMsg;

        if(exception instanceof AuthenticationServiceException) {
            failMsg = "존재하지 않는 사용자입니다";
        }else if(exception instanceof BadCredentialsException){
            failMsg = "아이디 또는 비밀번호가 일치하지 않습니다";
        }else if(exception instanceof LockedException) {
            failMsg = "잠긴 계정입니다";
        }else if(exception instanceof DisabledException){
            failMsg = "비활성 계정입니다";
        }else if(exception instanceof AccountExpiredException) {
            failMsg = "만료된 계정입니다";
        }else if(exception instanceof CredentialsExpiredException) {
            failMsg = "자격증명이 만료되었습니다";
        }else if(exception instanceof AuthenticationCredentialsNotFoundException) {
            failMsg = "인증요청이 거부되었습니다";
        }else if(exception instanceof UsernameNotFoundException){
            failMsg = "존재하지 않는 이메일입니다";
        }else {
            failMsg = "정의되어 있지 않는 케이스의 오류입니다";
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter printWriter = response.getWriter();
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("failType",failMsg);

        jsonObject = new JSONObject(resultMap);

        printWriter.println(jsonObject);
        printWriter.flush();
        printWriter.close();

    }
}
