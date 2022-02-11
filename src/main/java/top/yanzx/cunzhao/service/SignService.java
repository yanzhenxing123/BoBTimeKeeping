package top.yanzx.cunzhao.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import top.yanzx.cunzhao.config.annotation.RequiresPermissions;
import top.yanzx.cunzhao.dao.SignDao;
import top.yanzx.cunzhao.dto.session.SessionUserInfo;
import top.yanzx.cunzhao.util.CommonUtil;
import top.yanzx.cunzhao.util.constants.ErrorEnum;

import java.util.HashMap;

/**
 * @Author: yanzx
 * @Date: 2022/2/11 22:18
 * @Description:
 */


@Service
public class SignService {

    @Autowired
    private SignDao signDao;

    public JSONObject signNums(JSONObject jsonObject) {
        /**
         * 签到数量
         * @author yanzx
         * @date 2022/2/11
         * @param []
         * @return com.alibaba.fastjson.JSONObject
         *
         */

        int nums = signDao.signNums(jsonObject);
        HashMap<String, Integer> info = new HashMap<>();
        info.put("nums", nums);
        return CommonUtil.successJson(info);
    }


    /**
     * 签到
     */
    public JSONObject createSign(JSONObject jsonObject) {
        int isExist = signDao.queryExist(jsonObject);
        if (isExist == 1) {
            return CommonUtil.errorJson(ErrorEnum.E_50001);
        }

        int sign = signDao.createSign(jsonObject);
        return CommonUtil.successJson();
    }

}
