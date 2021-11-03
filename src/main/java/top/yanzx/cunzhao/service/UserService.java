package top.yanzx.cunzhao.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.yanzx.cunzhao.dao.UserDao;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public int countUser() {
        return userDao.countUser();
    }

}
