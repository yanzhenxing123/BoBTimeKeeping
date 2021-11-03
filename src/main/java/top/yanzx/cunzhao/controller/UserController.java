package top.yanzx.cunzhao.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yanzx.cunzhao.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    public String getUser() {
        return "test";
    }

    @GetMapping("/nums")
    public int getUserNums() {
        return userService.countUser();
    }


}
