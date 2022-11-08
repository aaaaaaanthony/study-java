package com.mmzcg.security.service;

import com.mmzcg.security.pojo.RedisUser;
import com.mmzcg.security.pojo.SysUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TestService implements UserDetailsService {

    static String user = "anthony";
    static String password = "$2a$10$yyR9WuT9JY/bpe1VPU0yguqlv0lWpgzTD9NEetf2.n8y7NXIa1rfm";

    /**
     * DaoAuthenticationProvier 会调用这个方法查询用户,并且返回UserDetails对象
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 从数据库查询用户信息
        SysUser sysUser = new SysUser();
        if (username.equals(user)) {
            sysUser.setUsername(user);
            sysUser.setPassword(password);
        } else {
            // 没有查到用户
            throw new RuntimeException("没有查到用户信息");
        }

        // 查询对应的权限信息
        ArrayList<String> strings = new ArrayList<>();
//        strings.add("test");
        strings.add("admin");

        // 返回封装的信息
        return new RedisUser(sysUser, strings);
    }
}
