package top.yanzx.cunzhao.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author yanzx
 * @description: 用户dao
 * @date 2021/11/3 3:18 下午
 */
@Mapper
public interface UserDao {
    int countUser();

    /**
     * 查询用户列表
     */
    List<JSONObject> listUser(JSONObject jsonObject);

    
    


}
