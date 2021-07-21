package www.tangan.com.dao.impl;

import www.tangan.com.bean.User;
import www.tangan.com.dao.RegisterDao;
import www.tangan.com.util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import static www.tangan.com.util.CommonUtil.setObject;

/**
 * @author 谭淦
 */
public class RegisterDaoImp implements RegisterDao {

    private static RegisterDaoImp instance;

    private RegisterDaoImp() {
    }

    public static synchronized RegisterDaoImp getInstance() {
        if (instance == null) {
            instance = new RegisterDaoImp();
        }
        return instance;
    }


    @Override
    public boolean addUser(User user) {
        String sql = "insert into user(user_name,password,sex,sign,phone,school,user_account,position)" +
                " values (?,?,?,?,?,?,?,1);";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JdbcUtils.getConnection();
            ps = conn.prepareStatement(sql);
            if (ps != null) {
                setObject(ps, user.getName(), user.getPassword(), user.getSex(), user.getSign(), user.getPhone(), user.getSchool(), user.getAccount());
                int i = ps.executeUpdate();
                if (i == 1) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            JdbcUtils.close(ps, conn);
        }
        return false;
    }

    @Override
    public LinkedList<String> findAllUserAccount() {
        String sql = "select user.user_account from user";
        LinkedList<String> list = new LinkedList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            ps = conn.prepareStatement(sql);
            if (ps != null) {
                rs = ps.executeQuery();
                while (rs.next()) {
                    String account = rs.getString("user_account");
                    list.add(account);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(rs, ps, conn);
        }
        return list;
    }
}
