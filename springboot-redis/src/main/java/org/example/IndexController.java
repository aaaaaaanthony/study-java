package org.example;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/index")
public class IndexController {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping("/list")
    public void list() throws JsonProcessingException {
        redisTemplate.opsForList().rightPush("list_test1", "anthony");
        redisTemplate.opsForList().rightPush("list_test1", "nick");
        redisTemplate.opsForList().rightPush("list_test1", "anthony");

        UserVO userVO = new UserVO();
        userVO.setName("anthony");
        userVO.setAge(11);

        UserVO userVO2 = new UserVO();
        userVO2.setName("anthony");
        userVO2.setAge(12);

        String s = objectMapper.writeValueAsString(userVO);
        redisTemplate.opsForList().rightPush("list_test2", s);

        String s2 = objectMapper.writeValueAsString(userVO2);
        redisTemplate.opsForList().rightPush("list_test2", s2);

        redisTemplate.opsForList().rightPush("list_test2", s);

        redisTemplate.opsForList().rightPush("list_test2", s2);
    }

    @RequestMapping("/set")
    public void set() throws JsonProcessingException {
        redisTemplate.opsForSet().add("set_test1", "anthony");
        redisTemplate.opsForSet().add("set_test1", "nick");
        redisTemplate.opsForSet().add("set_test1", "anthony");

        UserVO userVO = new UserVO();
        userVO.setName("anthony");
        userVO.setAge(11);

        UserVO userVO2 = new UserVO();
        userVO2.setName("anthony");
        userVO2.setAge(12);

        String s = objectMapper.writeValueAsString(userVO);
        redisTemplate.opsForSet().add("set_test2", s);

        String s2 = objectMapper.writeValueAsString(userVO2);
        redisTemplate.opsForSet().add("set_test2", s2);

        redisTemplate.opsForSet().add("set_test2", s);

        redisTemplate.opsForSet().add("set_test2", s2);
    }

    @RequestMapping("/zset")
    public void zset() throws JsonProcessingException {
        redisTemplate.opsForZSet().add("zset_test1", "anthony", 1);
        redisTemplate.opsForZSet().add("zset_test1", "nick", 4);
        redisTemplate.opsForZSet().add("zset_test1", "steven", 2);
        redisTemplate.opsForZSet().add("zset_test1", "tiam", 3);
        System.out.println("结束");

        UserVO userVO = new UserVO();
        userVO.setName("anthony");
        userVO.setAge(11);

        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(userVO);
        redisTemplate.opsForZSet().add("zset_test2", s, 1);

        UserVO userVO2 = new UserVO();
        userVO2.setName("anthony");
        userVO2.setAge(12);
        String s2 = objectMapper.writeValueAsString(userVO2);
        redisTemplate.opsForZSet().add("zset_test2", s2, 2);
    }
}
