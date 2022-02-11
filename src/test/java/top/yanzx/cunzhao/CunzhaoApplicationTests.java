package top.yanzx.cunzhao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import top.yanzx.cunzhao.util.RedisUtil;

@SpringBootTest
 class SpringbootRedisApplicationTests {
     @Autowired
     private RedisUtil redisUtil;

     @Test
     void contextLoads(){
         redisUtil.set("a", "b");
     }
 }