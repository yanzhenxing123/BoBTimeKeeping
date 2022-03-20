package top.yanzx.cunzhao.dao;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author: yanzx
 * @Date: 2022/2/12 10:28
 * @Description:
 */


public interface ProfileDao {
    /**
     * 查询用户信息
     */
    JSONObject getProfileInfo(JSONObject jsonObject);

    /**
     * 添加萝卜币
     */
    int addCoin(JSONObject jsonObject);

    /**
     * 设置默认用户信息
     */
    int setDefaultProfile(JSONObject jsonObject);

    /**
     * 修改用户信息
     */
    int updateProfile(JSONObject jsonObject);


    /**
     * 修改用户信息
     */
    int updateNickname(JSONObject jsonObject);

    /**
     * 修改用户头像
     */
    int updateAvatar(JSONObject jsonObject);
}
