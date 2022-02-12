package top.yanzx.cunzhao.dao;

import com.alibaba.fastjson.JSONObject;
import top.yanzx.cunzhao.dto.session.SessionUserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * @author: heeexy
 * @description: 登录相关dao
 * @date: 2017/10/24 11:02
 */
public interface LoginDao {
    /**
     * 根据用户名和密码查询对应的用户
     */
    JSONObject checkUser(@Param("username") String username, @Param("password") String password);

    JSONObject checkUserByPhone(@Param("phone_number") String phone_number);

    SessionUserInfo getUserInfo(String username);

    Set<String> getAllMenu();

    Set<String> getAllPermissionCode();
}
