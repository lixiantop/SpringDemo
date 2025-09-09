package org.example.springlearndemo.controller;

import org.example.springlearndemo.util.RedisService;
import org.example.springlearndemo.util.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private RedisService redisService;

    @GetMapping("/hello")
    public RestResult hello(@RequestParam String key){
        redisService.redisOperation();
        return RestResult.success(redisService.getRedisValue(key));
    }
}
