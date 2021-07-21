package www.tangan.com.dao.impl;

import www.tangan.com.dao.LoginDao;
import www.tangan.com.util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author 谭淦
 */
public class LoginDaoImp implements LoginDao {

    /**
     * 单例模式
     */
    private static LoginDaoImp instance;

    private LoginDaoImp() {
    }

    public static synchronized LoginDaoImp getInstance() {
        if (instance == null) {
            instance = new LoginDaoImp();
        }
        return instance;
    }

    @Override
    public String findPassword(String account) {
        String sql = "SELECT password FROM user WHERE user_account = ? ;";
        String password1 = null;
        Connection conn = null;
        PreparedStatement ps1 = null;
        ResultSet rs1 = null;
        try {
            conn = JdbcUtils.getConnection();
            ps1 = conn.prepareStatement(sql);
            if (ps1 != null) {
                ps1.setString(1, account);
                rs1 = ps1.executeQuery();
                if (rs1.next()) {
                    password1 = rs1.getString("password");
                }
                return password1;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            JdbcUtils.close(rs1, ps1, conn);
        }
        return null;
    }

}
