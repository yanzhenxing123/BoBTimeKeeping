package top.yanzx.cunzhao;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import top.yanzx.cunzhao.dao.ProfileDao;
import top.yanzx.cunzhao.util.RedisUtil;

@SpringBootTest
class SpringbootRedisApplicationTests {
    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ProfileDao profileDao;

    @Test
    public void testDateDuration(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", 10009);
        JSONObject profileInfo = profileDao.getProfileInfo(jsonObject);
        Object birthday = profileInfo.get("birthday");
        System.out.println(profileInfo);
        System.out.println(profileInfo.get("birthday"));
    }




 }