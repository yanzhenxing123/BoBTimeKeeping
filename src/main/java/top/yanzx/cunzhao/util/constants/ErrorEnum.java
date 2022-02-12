package top.yanzx.cunzhao.util.constants;

/**
 * @author: heeexy
 * @date: 2017/10/24 10:16
 */
public enum ErrorEnum {
    /*
     * 错误信息
     * */
    E_400("400", "请求处理异常，请稍后再试"),
    E_500("500", "请求方式有误,请检查 GET/POST"),
    E_501("501", "请求路径不存在"),
    E_502("502", "权限不足"),

    // 用户信息
    E_10008("10008", "角色删除失败,尚有用户属于此角色"),
    E_10009("10009", "账户已存在"),
    E_10010("10010", "账号/密码错误"),
    E_10011("10011", "更新用户信息失败"),
    E_10012("10012", "日期格式不正确"),



    E_20011("20011", "登陆已过期,请重新登陆"),


    // 短信信息
    E_40001("40001", "短信发送失败"),
    E_40002("40002", "验证码不正确"),

    // 签到
    E_50001("50001", "用户已签到，请明天再来~"),

    E_90003("90003", "缺少必填参数");

    private final String errorCode;

    private final String errorMsg;

    ErrorEnum(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;

    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
