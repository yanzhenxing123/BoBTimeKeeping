package top.yanzx.cunzhao.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.yanzx.cunzhao.config.exception.CommonJsonException;
import top.yanzx.cunzhao.util.CommonUtil;
import top.yanzx.cunzhao.util.RedisUtil;
import top.yanzx.cunzhao.util.constants.ErrorEnum;

/**
 * @Author: yanzx
 * @Date: 2022/2/11 0:30
 * @Description:
 */

@Service
public class SmsService {


    @Autowired
    private RedisUtil redisUtil;

    public boolean authCode(JSONObject jsonObject) {
        /**
         * @author yanzx
         * @date 2022/2/11
         * @param [jsonObject]
         * @desc 验证 message_id 和 验证码
         */
        String message_id = jsonObject.getString("message_id");
        String code = jsonObject.getString("code");
        if (!code.equals(redisUtil.get(message_id))) {
            throw new CommonJsonException(ErrorEnum.E_40002);
        }
        return true;
    }
}
