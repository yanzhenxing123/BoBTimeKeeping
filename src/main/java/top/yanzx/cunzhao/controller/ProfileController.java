package top.yanzx.cunzhao.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yanzx.cunzhao.config.annotation.RequiresPermissions;
import top.yanzx.cunzhao.dao.ProfileDao;
import top.yanzx.cunzhao.dto.session.SessionUserInfo;
import top.yanzx.cunzhao.service.ProfileService;
import top.yanzx.cunzhao.service.SignService;
import top.yanzx.cunzhao.service.TokenService;

/**
 * @Author: yanzx
 * @Date: 2022/2/11 20:27
 * @Description: 用户信息
 */

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private SignService signService;

    @Autowired
    private ProfileService profileService;

    /**
     * 用户信息
     */
    @RequiresPermissions("sign:add")
    @GetMapping("/info")
    public JSONObject getProfileInfo() {
        SessionUserInfo userInfo = tokenService.getUserInfo();
        int userId = userInfo.getUserId();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", userId);
        return profileService.info(jsonObject);
    }


}
