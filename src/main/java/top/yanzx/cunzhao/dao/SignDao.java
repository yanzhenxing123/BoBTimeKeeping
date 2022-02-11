package top.yanzx.cunzhao.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @Author: yanzx
 * @Date: 2022/2/11 21:37
 * @Description:
 */
public interface SignDao {


    /**
     * 今天是否签到
     */
    int queryExist(JSONObject jsonObject);


    /**
     * 签到
     */
    int createSign(JSONObject jsonObject);

    /**
     * 签到数量
     */
    int signNums(JSONObject jsonObject);

}
