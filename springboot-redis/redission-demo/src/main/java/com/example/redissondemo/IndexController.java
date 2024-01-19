package com.example.redissondemo;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RequestMapping("/index")
@RestController
public class IndexController {

    @Resource
    private RedissonClient redissonClient;

    @GetMapping("/test")
    public String method() {
        String lockKey = "anthony2";
        RLock lock = redissonClient.getLock(lockKey);
        try {
            boolean b = lock.tryLock(9999, TimeUnit.DAYS);
            System.out.println("加锁状态:"+b);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }
}
