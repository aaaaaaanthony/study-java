package org.example;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
@Slf4j
public class IndexController {

    /**
     * 测试logback的日志位置
     */
    @RequestMapping("/test1")
    public void test1()  {
        log.info("我打印的info日志");
        log.error("我打印的error日志");
        log.debug("我打印的debug日志");
    }

    /**
     * 直接打印堆栈信息
     */
    @RequestMapping("/test2")
    public void test2()  {
        int i = 1 / 0;
    }

    @RequestMapping("/test3")
    public void test3()  {
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping("/test4")
    public void test4()  {
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            throw new RuntimeException("我的错误信息",e);
        }
    }

    @RequestMapping("/test5")
    public void test5()  {
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            log.error("我的错误信息2",e);
            log.error("我的错误信息2:{}","这是自定义的消息",e);
        }
    }


    @RequestMapping("/test6")
    public void test6()  {
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            throw new RuntimeException("test6");
        }
    }
}
