package com.mmzcg.security.filter;

import com.mmzcg.security.pojo.RedisUser;
import com.mmzcg.security.service.LoginService;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import com.mmzcg.security.util.JWTUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 解析token
        String subject = "";
        try {
            Claims claims = JWTUtil.parseJWT("1234567", token);
            subject = claims.getSubject();
        } catch (Exception e) {
            throw new RuntimeException("token异常");
        }

        // 从redis中获取信息
        RedisUser anthony = LoginService.map.get(subject);
        if (anthony == null) {
            throw new RuntimeException("token错误");
        }

        // 存入SecurityContextHolder
        // AuthenticationManager 进行用户认证
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(anthony, null, anthony.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);


        // 放行
        filterChain.doFilter(request, response);
    }
}
