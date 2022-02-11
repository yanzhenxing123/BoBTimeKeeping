package top.yanzx.cunzhao.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.yanzx.cunzhao.config.annotation.RequiresPermissions;
import top.yanzx.cunzhao.dao.SignDao;
import top.yanzx.cunzhao.dto.session.SessionUserInfo;
import top.yanzx.cunzhao.service.LoginService;
import top.yanzx.cunzhao.service.SmsService;
import top.yanzx.cunzhao.service.TokenService;
import top.yanzx.cunzhao.util.CommonUtil;
import top.yanzx.cunzhao.util.constants.ErrorEnum;

import java.util.Date;
import java.util.HashMap;

/**
 * @Author: yanzx
 * @Date: 2022/2/11 20:27
 * @Description: 签到
 */

@RestController
@RequestMapping("/sign")
public class SignController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private SmsService smsService;

    @Autowired
    private TokenService tokenService;


    @Autowired
    private SignDao signDao;

    /**
     * 签到
     */
//    @RequiresPermissions("sign:add")
    @GetMapping("/create")
    public JSONObject authLogin() {
        SessionUserInfo userInfo = tokenService.getUserInfo();
        int userId = userInfo.getUserId();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", userId);

        int isExist = signDao.queryExist(jsonObject);
        if (isExist == 1) {
            return CommonUtil.errorJson(ErrorEnum.E_50001);
        }

        int sign = signDao.createSign(jsonObject);
        System.out.println(sign);
        return CommonUtil.successJson();
    }


    //    @RequiresPermissions("sign:add")
    @GetMapping("/nums")
    public JSONObject signNums() {
        SessionUserInfo userInfo = tokenService.getUserInfo();
        int userId = userInfo.getUserId();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", userId);

        int nums = signDao.signNums(jsonObject);

        HashMap<String, Integer> info = new HashMap<>();
        info.put("nums", nums);
        return CommonUtil.successJson(info);
    }

}
