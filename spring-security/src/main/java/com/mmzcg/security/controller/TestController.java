package com.mmzcg.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello")
    @PreAuthorize("hasAnyAuthority('test')")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello server");
    }

    /**
     * 自定义校验权限
     *
     * @return
     */
    @GetMapping("/hello2")
    @PreAuthorize("@anthony.hasAnyAuthority('test')")
    public ResponseEntity<String> hello2() {
        return ResponseEntity.ok("hello server2");
    }
}
