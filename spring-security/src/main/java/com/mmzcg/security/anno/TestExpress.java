package com.mmzcg.security.anno;

import com.mmzcg.security.pojo.RedisUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("anthony")
public class TestExpress {

    public boolean hasAnyAuthority(String authority) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        RedisUser principal = (RedisUser) authentication.getPrincipal();
//        List<String> list = (List<String>)principal.getAuthorities();
        List<SimpleGrantedAuthority> authorities = (List<SimpleGrantedAuthority>) principal.getAuthorities();
        for (SimpleGrantedAuthority simpleGrantedAuthority : authorities) {
            if (simpleGrantedAuthority.getAuthority().equals(authority)) {
                return true;
            }
        }
        return false;

    }
}
