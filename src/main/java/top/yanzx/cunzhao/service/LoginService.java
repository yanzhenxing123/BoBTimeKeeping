package top.yanzx.cunzhao.service;

import com.alibaba.fastjson.JSONObject;
import top.yanzx.cunzhao.config.exception.CommonJsonException;
import top.yanzx.cunzhao.controller.UserController;
import top.yanzx.cunzhao.dao.LoginDao;
import top.yanzx.cunzhao.dto.session.SessionUserInfo;
import top.yanzx.cunzhao.util.CommonUtil;
import top.yanzx.cunzhao.util.constants.ErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: heeexy
 * @description: 登录service实现类
 * @date: 2017/10/24 11:53
 */
@Service
@Slf4j
public class LoginService {

    @Autowired
    private LoginDao loginDao;
    @Autowired
    private TokenService tokenService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private UserController userController;

    /**
     * 登录表单提交
     */
    public JSONObject authLogin(JSONObject jsonObject) {
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        JSONObject info = new JSONObject();
        JSONObject user = loginDao.checkUser(username, password);
        if (user == null) {
            throw new CommonJsonException(ErrorEnum.E_10010);
        }
        String token = tokenService.generateToken(username);
        info.put("token", token);



        return CommonUtil.successJson(info);

    }


    /**
     * 使用短信验证码进行登录
     */
    public JSONObject login(JSONObject jsonObject) {
        String phone_number = jsonObject.getString("phone_number");
        JSONObject info = new JSONObject();
        JSONObject user = loginDao.checkUserByPhone(phone_number);
        if (user == null) {
            jsonObject.put("password", "209243");
            // 如果没有用户 那么就注册
            userController.register(jsonObject);
            user = jsonObject;

            profileService.setDefaultProfile(user);
            //            throw new CommonJsonException(ErrorEnum.E_10010);

        }

        String token = tokenService.generateToken(user.getString("username"));


        info.put("token", token);
        return CommonUtil.successJson(info);
    }


    /**
     * 查询当前登录用户的权限等信息
     */
    public JSONObject getInfo() {
        //从session获取用户信息
        SessionUserInfo userInfo = tokenService.getUserInfo();
        log.info(userInfo.toString());
        return CommonUtil.successJson(userInfo);
    }

    /**
     * 退出登录
     */
    public JSONObject logout() {
        tokenService.invalidateToken();
        return CommonUtil.successJson();
    }
}
