package org.example;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class RedisDao {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private static final Long SUCCESS = 1L;

    public boolean set(String key, String string) {
        try {
            redisTemplate.opsForValue().set(key, string);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    // 存一个对象
    public boolean setString(String key, String string) {
        try {
            redisTemplate.opsForValue().set(key, string,1,TimeUnit.DAYS);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    // 存一个对象,并设置生命周期
    public boolean setString(String key, String string, long time, TimeUnit timeUnit) {
        try {
            redisTemplate.opsForValue().set(key, string, time, timeUnit);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    // 存一个对象
    public boolean setBean(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(value),1,TimeUnit.DAYS);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    // 存一个对象
    public boolean setBean(String key, Object value, boolean flage) {
        try {
            redisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(value));
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    // 存一个对象
    public boolean setBean(String key, Object value, long time, TimeUnit timeUnit) {
        try {
            redisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(value), time, timeUnit);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    // 返回指定对象
    public String getStr(String key) {
        // 判断key是不是空
        if (StringUtils.isNotBlank(key)) {
            // 判断是否能从redis取出值
            Object obj = redisTemplate.opsForValue().get(key);
            if (obj != null) {
                return (String) obj;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    // 返回指定对象
    public <T> T getBean(String key, Class<T> beanClass) {
        // 判断key是不是空
        if (StringUtils.isNotBlank(key)) {
            // 判断是否能从redis取出值
            Object obj = redisTemplate.opsForValue().get(key);
            if (obj != null && obj != "") {
                if (beanClass.isInstance(String.class)) {
                    return (T) obj;
                } else {
                    return JSONUtil.toBean(JSONUtil.toJsonStr(obj), beanClass);
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    // 返回集合对象
    public <T> List<T> getBeanList(String key, Class<T> beanClass) {
        String s = getStr(key);
        List<T> ts = JSONUtil.toList(s, beanClass);
        if (ts.size() == 0) {
            return null;
        }else {
            return ts;
        }
    }

    // 删除缓存
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(Arrays.asList(key));
            }
        }
    }

    // 模糊查询key
    public Set<String> keys(String s) {
        Set<String> keys = redisTemplate.keys(s);
        return keys;
    }

    // 设置生命周期
    public void expire(String key ,long time ,TimeUnit timeUnit){
        redisTemplate.expire(key, time, timeUnit);
    }

    // 获取分布式锁
    public boolean tryLock(String lockKey, String requestId, int expireTime, long waitTimeout) {
        long nanoTime = System.nanoTime(); // 当前时间
        try {
            String script = "if redis.call('setNx',KEYS[1],ARGV[1]) then if redis.call('get',KEYS[1])==ARGV[1] then return redis.call('expire',KEYS[1],ARGV[2]) else return 0 end end";
            do {
                if (SUCCESS.equals(redisTemplate.execute(new DefaultRedisScript<>(script, Long.class),
                        Collections.singletonList(lockKey),
                        requestId,
                        expireTime))) {
                    // 获得分布式锁
                    return true;
                }
                Thread.sleep(500L);//休眠500毫秒
            } while ((System.nanoTime() - nanoTime) < TimeUnit.MILLISECONDS.toNanos(waitTimeout));
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    // 释放锁
    public boolean releaseLock(String lockKey, String requestId) {
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = redisTemplate.execute(new DefaultRedisScript<>(script, Long.class), Collections.singletonList(lockKey), requestId);
        return SUCCESS.equals(result);
    }


}
