package org.example.controller;


import org.example.entity.Test;
import org.example.service.TestService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("demo2")
public class TransactionalController {

    @Resource
    private TestService testService;

    @Resource
    private TransactionalController transactionalController;


    // ---------------------------------------------------
    // 简单测试 A调用B

//    /**
//     *
//     * test1 和 updateParentId 全部回滚
//     */
//    @Transactional
//    @PostMapping("/test1")
//    public void test1(){
//        Test byId = testService.getById(1);
//        byId.setUsername("anthony2");
//        byId.updateById();
//
//        updateParentId();
//    }
//
//    /**
//     * 简单的测试事务能不能用
//     * 改姓名不成功
//     */
//    public void updateParentId(){
//        Test byId = testService.getById(1);
//        byId.setParentId(1);
//        byId.updateById();
//        int i = 1 / 0;
//    }

    // -------------------也是全部回滚--------------------------------


//    @PostMapping("/test1")
//    @Transactional
//    public void test1(){
//        Test byId = testService.getById(1);
//        byId.setUsername("anthony2");
//        byId.updateById();
//
//        updateParentId();
//        int i = 1 / 0;
//    }
//
//    @Transactional
//    public void updateParentId(){
//        Test byId = testService.getById(1);
//        byId.setParentId(1);
//        byId.updateById();
//
//    }


    // -------------------REQUIRED--------------------------------

//    @PostMapping("/test1")
//    public void methodA(){
//        Test byId = testService.getById(1);
//        byId.setUsername("anthony2");
//        byId.updateById();
//
//        transactionalController.methodB();
//        int i = 1 / 0;
//    }
//
//    @Transactional(propagation= Propagation.REQUIRED)
//    public void methodB(){
//        Test byId = testService.getById(1);
//        byId.setParentId(1);
//        byId.updateById();
//        int i = 1 / 0;
//    }


    // -------------------SUPPORTS--------------------------------

//    @PostMapping("/test1")
//    @Transactional
//    public void methodA(){
//        Test byId = testService.getById(1);
//        byId.setUsername("anthony2");
//        byId.updateById();
//
//        transactionalController.methodB();
//        int i = 1 / 0;
//    }
//
//    @Transactional(propagation= Propagation.SUPPORTS)
//    public void methodB(){
//        Test byId = testService.getById(1);
//        byId.setParentId(1);
//        byId.updateById();
//        int i = 1 / 0;
//    }


    // -------------------MANDATORY--------------------------------

//    @PostMapping("/test1")
//    public void methodA(){
//        Test byId = testService.getById(1);
//        byId.setUsername("anthony2");
//        byId.updateById();
//
//        transactionalController.methodB();
//        int i = 1 / 0;
//    }
//
//    @Transactional(propagation= Propagation.MANDATORY)
//    public void methodB(){
//        Test byId = testService.getById(1);
//        byId.setParentId(1);
//        byId.updateById();
//    }


    // -------------------REQUIRES_NEW--------------------------------

//    @PostMapping("/test1")
//    public void methodA(){
//        Test byId = testService.getById(1);
//        byId.setUsername("anthony2");
//        byId.updateById();
//
//        transactionalController.methodB();
//
//    }
//
//    @Transactional(propagation= Propagation.REQUIRES_NEW)
//    public void methodB(){
//        Test byId = testService.getById(2);
//        byId.setParentId(1);
//        byId.updateById();
//        int i = 1 / 0;
//
//    }

    // -------------------REQUIRES_NEW--------------------------------

//    @PostMapping("/test1")
//    @Transactional
//    public void methodA(){
//        Test byId = testService.getById(1);
//        byId.setUsername("anthony2");
//        byId.updateById();
//
//        transactionalController.methodB();
//        int i = 1 / 0;
//
//    }
//
//    @Transactional(propagation= Propagation.NOT_SUPPORTED)
//    public void methodB(){
//        Test byId = testService.getById(2);
//        byId.setParentId(1);
//        byId.updateById();
//        int i = 1 / 0;
//
//    }

    // -------------------NEVER--------------------------------

//    @PostMapping("/test1")
//    @Transactional
//    public void methodA(){
//        Test byId = testService.getById(1);
//        byId.setUsername("anthony2");
//        byId.updateById();
//        transactionalController.methodB();
//    }
//
//    @Transactional(propagation= Propagation.NEVER)
//    public void methodB(){
//        Test byId = testService.getById(2);
//        byId.setParentId(1);
//        byId.updateById();
//    }

    // -------------------NESTED--------------------------------

    @PostMapping("/test1")
    public void methodA(){
        Test byId = testService.getById(1);
        byId.setUsername("anthony2");
        byId.updateById();
        transactionalController.methodB();
    }

    @Transactional(propagation= Propagation.NESTED)
    public void methodB(){
        Test byId = testService.getById(2);
        byId.setParentId(1);
        byId.updateById();
        int i = 1 / 0;
    }




}
