package com.mmzcg.security.handler;

import cn.hutool.json.JSONUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthException implements AuthenticationEntryPoint {

    private static void extracted(HttpServletResponse response, String string) throws IOException {
        response.setStatus(200);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().println(string);
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        ResponseEntity<String> responseEntity = ResponseEntity.ok().body("认证失败,请重新登录");
        String str = JSONUtil.toJsonStr(responseEntity);
        /// 处理异常
        extracted(response, str);

    }
}
