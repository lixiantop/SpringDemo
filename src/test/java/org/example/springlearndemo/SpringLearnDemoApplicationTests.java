package org.example.springlearndemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class SpringLearnDemoApplicationTests {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        record Demo(String name, int age){}
        Demo demo = new Demo("a",1);
        redisTemplate.opsForValue().set("a", String.valueOf(demo));
        System.out.println(redisTemplate.opsForValue().get("a"));

        //redis 分布式锁
        String lockKey = "lock";
        String lockValue = "lock_value";
        boolean lockResult = false;
        
        try {
            // 尝试获取锁，设置超时时间防止死锁
            lockResult = redisTemplate.opsForValue().setIfAbsent(lockKey, lockValue, 10, TimeUnit.SECONDS);
            
            if (lockResult) {
                System.out.println("获取锁成功");
                
                // 模拟业务处理
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                // 业务处理完成后，检查锁是否仍然是自己持有的，再释放
                if (lockValue.equals(redisTemplate.opsForValue().get(lockKey))) {
                    redisTemplate.delete(lockKey);
                    System.out.println("锁已释放");
                }
            } else {
                System.out.println("获取锁失败，锁已被其他客户端持有");
            }
        } catch (Exception e) {
            System.out.println("操作异常: " + e.getMessage());
            // 发生异常时尝试释放锁
            if (lockValue.equals(redisTemplate.opsForValue().get(lockKey))) {
                redisTemplate.delete(lockKey);
                System.out.println("异常时已释放锁");
            }
        }
    }

}
