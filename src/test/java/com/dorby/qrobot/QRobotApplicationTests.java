package com.dorby.qrobot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class QRobotApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        ValueOperations valueOperations=redisTemplate.opsForValue();
        String value= UUID.randomUUID().toString();
        valueOperations.set("k1",value,5, TimeUnit.SECONDS);

            valueOperations.set("name","xxxx");
            String name=(String) valueOperations.get("name");
            System.out.println("name="+name);
            System.out.println(valueOperations.get("k1"));

    }

}
