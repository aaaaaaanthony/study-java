package org.example;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/index")
public class IndexController {

    @RequestMapping("/index")
    public void index(@Validated @RequestBody UserVO userVO){
        System.out.println(userVO);
    }
    @RequestMapping("/index2")
    public void index2(@Validated   @RequestParam("name") @NotBlank(message = "姓名是必须的") String name){
        System.out.println(name);
    }

}
