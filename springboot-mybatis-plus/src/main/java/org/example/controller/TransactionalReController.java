package org.example.controller;


import org.example.entity.Test;
import org.example.service.Test2Service;
import org.example.service.TestService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.awt.image.renderable.RenderableImage;

@RestController
@RequestMapping("demo3")
public class TransactionalReController {

    @Resource
    private TestService testService;

    private Test2Service test2Service;


    // -------------------REQUIRED--------------------------------

    @PostMapping("/test")
    public void methodA(){
        testService.insert();
        test2Service.insert();
    }

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

//    @PostMapping("/test1")
//    public void methodA(){
//        Test byId = testService.getById(1);
//        byId.setUsername("anthony2");
//        byId.updateById();
//        transactionalController.methodB();
//    }
//
//    @Transactional(propagation= Propagation.NESTED)
//    public void methodB(){
//        Test byId = testService.getById(2);
//        byId.setParentId(1);
//        byId.updateById();
//        int i = 1 / 0;
//    }




}
