package org.example.controller;


import org.example.entity.Test;
import org.example.service.TestService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class TestController {

    @Resource
    private TestService testService;

    @PostMapping("/hello")
    public void method(){
        List<Test> list = testService.list();
        System.out.println(list);
    }
}
