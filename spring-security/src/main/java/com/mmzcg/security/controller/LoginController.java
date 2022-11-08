package com.mmzcg.security.controller;

import com.mmzcg.security.pojo.SysUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.mmzcg.security.service.LoginService;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
@RequestMapping("/")
public class LoginController {

    @Resource
    LoginService loginService;

    @PostMapping("/user/login")
    public ResponseEntity<HashMap> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        sysUser.setPassword(password);

        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("token", loginService.login(sysUser));
        return ResponseEntity.status(200).body(stringObjectHashMap);
    }

    @PostMapping("/user/logout")
    public ResponseEntity<String> logout() {
        loginService.logout();
        return ResponseEntity.ok("success");
    }
}
