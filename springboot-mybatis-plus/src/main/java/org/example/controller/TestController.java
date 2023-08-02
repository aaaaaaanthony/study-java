package org.example.controller;


import org.example.entity.Test;
import org.example.service.TestService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("demo1")
public class TestController {

    @Resource
    private TestService testService;

    /**
     * 简单的测试事务能不能用
     * 改姓名成功了
     */
    @PostMapping("/test1")
    public void test1(){
        Test byId = testService.getById(1);
        byId.setUsername("anthony2");
        byId.updateById();
        int i = 1 / 0;
    }

    /**
     * 简单的测试事务能不能用
     * 改姓名不成功
     */
    @PostMapping("/test2")
    @Transactional
    public void test2(){
        Test byId = testService.getById(1);
        byId.setUsername("anthony");
        byId.updateById();
        int i = 1 / 0;
    }
}
