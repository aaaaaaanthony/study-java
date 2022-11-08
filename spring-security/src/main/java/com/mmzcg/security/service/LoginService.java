package com.mmzcg.security.service;

import com.mmzcg.security.pojo.RedisUser;
import com.mmzcg.security.pojo.SysUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.mmzcg.security.util.JWTUtil.createJWT;

@Service
public class LoginService {

    public static Map<String, RedisUser> map = new HashMap<>();

    @Resource
    AuthenticationManager authenticationManager;

    public String login(SysUser sysUser) {

        // AuthenticationManager 进行用户认证
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(sysUser.getUsername(), sysUser.getPassword());
        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        // 如果认证不通过,给出对应的提示
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登录失败");
        }

        // 如果认证通过,使用username给jwt生成一个
        RedisUser principal = (RedisUser) authenticate.getPrincipal();
        map.put(principal.getUsername(), principal);


        return createJWT("1234567", 9990000, principal.getUsername());
    }

    public void logout() {

        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        RedisUser principal = (RedisUser) authentication.getPrincipal();

        String username = principal.getUsername();

        // 从redis里删除
        map.remove(username);

    }
}
