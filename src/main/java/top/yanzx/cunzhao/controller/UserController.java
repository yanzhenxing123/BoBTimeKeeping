package top.yanzx.cunzhao.controller;


import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yanzx.cunzhao.service.UserService;
import top.yanzx.cunzhao.util.CommonUtil;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    public JSONObject getUser() {
        return CommonUtil.successJson();
    }

    @GetMapping("/nums")
    public int getUserNums() {
        return userService.countUser();
    }

//    @GetMapping("/list")
//    public JSONObject listUser(HttpServletRequest request) {
//        return userService.listUser(CommonUtil.request2Json(request));
//    }


    // 这个是在ipad上写的da

}
