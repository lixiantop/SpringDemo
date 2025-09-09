package org.example.springlearndemo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;


@Component
public class RedisService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public void redisOperation() {
        stringRedisTemplate.opsForValue().set("hello", "world");
    }

    public String getRedisValue(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }
}
