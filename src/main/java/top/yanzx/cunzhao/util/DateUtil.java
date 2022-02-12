package top.yanzx.cunzhao.util;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: yanzx
 * @Date: 2022/2/12 21:39
 * @Description:
 */
public class DateUtil {
    /***
     * 判断字符串是否是yyyy-MM-dd格式
     * @param mes 字符串
     * @return boolean 是否是日期格式
     */
    public static boolean isRqFormat(String mes) {
        String format = "([0-9]{4})-([1-9]|0[1-9]|1[012])-([1-9]|0[1-9]|[12][0-9]|3[01])";
        Pattern pattern = Pattern.compile(format);
        Matcher matcher = pattern.matcher(mes);
        if (matcher.matches()) {
            pattern = Pattern.compile("(\\d{4})-(\\d{2}|\\d{1})-(\\d{2}|\\d{1})");
            matcher = pattern.matcher(mes);
            if (matcher.matches()) {
                int y = Integer.parseInt(matcher.group(1));
                int m = Integer.parseInt(matcher.group(2));
                int d = Integer.parseInt(matcher.group(3));
                if (d > 28) {
                    Calendar c = Calendar.getInstance();
                    c.set(y, m - 1, 1);  //每个月的最大天数
                    int lastDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
                    return (lastDay >= d);
                }
            }
            return true;
        }
        return false;
    }
}
