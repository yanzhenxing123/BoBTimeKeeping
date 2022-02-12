package top.yanzx.cunzhao.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.yanzx.cunzhao.dao.FortuneDao;
import top.yanzx.cunzhao.util.CommonUtil;

/**
 * @Author: yanzx
 * @Date: 2022/2/12 20:39
 * @Description:
 */

@Service
public class FortuneService {
    @Autowired
    FortuneDao fortuneDao;


    public JSONObject getFortune(){
        JSONObject info = fortuneDao.getFortune();
        return CommonUtil.successJson(info);
    }
}
