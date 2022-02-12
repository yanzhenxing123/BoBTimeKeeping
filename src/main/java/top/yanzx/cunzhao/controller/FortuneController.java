package top.yanzx.cunzhao.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yanzx.cunzhao.service.FortuneService;

/**
 * @Author: yanzx
 * @Date: 2022/2/12 20:36
 * @Description: 星座和运势 constellation and Fortune
 */

@RestController
@RequestMapping("/fortune")
public class FortuneController {
    @Autowired
    FortuneService fortuneService;

    /**
     * @Author: yanzx
     * @Date: 2022/2/12 21:10
     * @Description: 随机获取一条运势信息
     */
    @GetMapping
    public JSONObject getFortune(){
        return fortuneService.getFortune();
    }
}
