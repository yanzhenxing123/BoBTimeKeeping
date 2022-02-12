package top.yanzx.cunzhao.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.yanzx.cunzhao.config.annotation.RequiresPermissions;
import top.yanzx.cunzhao.dao.ProfileDao;
import top.yanzx.cunzhao.dto.session.SessionUserInfo;
import top.yanzx.cunzhao.service.ProfileService;
import top.yanzx.cunzhao.service.SignService;
import top.yanzx.cunzhao.service.TokenService;
import top.yanzx.cunzhao.util.CommonUtil;

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
        JSONObject jsonObject = tokenService.getUserIdParams();
        return profileService.info(jsonObject);
    }

    /**
     * 添加金币接口
     */
    @RequiresPermissions("sign:add")
    @PostMapping("/addCoin")
    public JSONObject addCoin(@RequestBody JSONObject requestJson) {
        CommonUtil.hasAllRequired(requestJson, "num");
        requestJson.put("userId", tokenService.getUserId());
        System.out.println(requestJson);
        return profileService.addCoin(requestJson);
    }


////    @RequiresPermissions("user:add")
//    @PostMapping("/addCoin")
//    public JSONObject addCoin(@RequestBody JSONObject requestJson) {
//        CommonUtil.hasAllRequired(requestJson, "username, password, nickname, roleIds");
//        return CommonUtil.successJson();
//    }


}
