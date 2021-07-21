package www.tangan.com.util;

import java.sql.PreparedStatement;

import java.sql.SQLException;


/**
 * @author 常用工具类
 */
public class CommonUtil {
    /**
     * 判断字符是否为整数
     *
     * @param str 传入的字符串，准备拿去检测有没有
     * @return 成就true
     */

    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static void setObject(PreparedStatement ps, Object... params) throws SQLException {
        int index = 1;
        for (Object object : params) {
            ps.setObject(index, object);
            index++;
        }
    }

}
