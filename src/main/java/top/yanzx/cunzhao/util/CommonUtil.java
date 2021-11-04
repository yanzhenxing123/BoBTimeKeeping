package top.yanzx.cunzhao.util;

import com.alibaba.fastjson.JSONObject;
import top.yanzx.cunzhao.util.constants.Constants;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;

public class CommonUtil {
    /**
     * 返回一个info为空对象的成功消息的json
     */
    public static JSONObject successJson() {
        return successJson(new JSONObject());
    }

    /**
     * 返回一个返回码为200的json
     */
    public static JSONObject successJson(Object info) {
        JSONObject resultJson = new JSONObject();
        resultJson.put("code", Constants.SUCCESS_CODE);
        resultJson.put("msg", Constants.SUCCESS_MSG);
        resultJson.put("data", info);
        return resultJson;
    }



}
