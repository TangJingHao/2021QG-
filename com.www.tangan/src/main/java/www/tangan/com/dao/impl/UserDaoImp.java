package www.tangan.com.dao.impl;

import www.tangan.com.bean.Event;
import www.tangan.com.bean.User;
import www.tangan.com.dao.UserDao;
import www.tangan.com.util.JdbcUtils;

import java.sql.*;
import java.util.LinkedList;

import static www.tangan.com.util.CommonUtil.setObject;


/**
 * @author 谭淦
 */
public class UserDaoImp implements UserDao {

    private static UserDaoImp instance;

    private UserDaoImp() {
    }

    public static synchronized UserDaoImp getInstance() {
        if (instance == null) {
            instance = new UserDaoImp();
        }
        return instance;
    }


    @Override
    public LinkedList<Event> findAllEvent() {
        String sql = "SELECT  event.event_id, event.user_id, event.title,event.content,event.collection_number,event.praise_number,user.`user_name`,event.release_time FROM EVENT CROSS JOIN USER WHERE \n" +
                "user.`user_id` = event.`user_id` ORDER BY event_id DESC ";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        LinkedList<Event> list = new LinkedList<>();
        try {
            conn = JdbcUtils.getConnection();
            ps = conn.prepareStatement(sql);
            if (ps != null) {
                rs = ps.executeQuery();
                while (rs.next()) {
                    Event event = new Event();
                    event.setTitle(rs.getString("title"));
                    event.setContent(rs.getString("content"));
                    event.setCollectionNum(rs.getInt("collection_number"));
                    event.setPraiseNum(rs.getInt("praise_number"));
                    event.setWriter(rs.getString("user_name"));
                    event.setReleaseTime(rs.getDate("release_time"));
                    event.setUserId(rs.getInt("user_id"));
                    event.setEventId(rs.getInt("event_id"));
                    list.add(event);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(rs, ps, conn);
        }
        return list;
    }


    @Override
    public boolean addEvent(Event event) {
        String sql = "insert into event(user_id,title,content,release_time,praise_number,collection_number)" +
                "values (?,?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JdbcUtils.getConnection();
            ps = conn.prepareStatement(sql);
            if (ps != null) {

                setObject(ps, event.getUserId(), event.getTitle(), event.getContent(), event.getReleaseTime(), event.getPraiseNum(), event.getCollectionNum());

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
    public User findUserByAccount(String userAccount) {
        String sql = "SELECT * FROM USER WHERE user_account = ? LIMIT 1 ;";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = new User();
        try {
            conn = JdbcUtils.getConnection();
            ps = conn.prepareStatement(sql);
            if (ps != null) {
                ps.setString(1, userAccount);
                rs = ps.executeQuery();
                if (rs.next()) {
                    user.setName(rs.getString("user_name"));
                    user.setAccount(rs.getString("user_account"));
                    user.setPassword(rs.getString("password"));
                    user.setPhone(rs.getString("phone"));
                    user.setPosition(rs.getInt("position"));
                    user.setSchool(rs.getString("school"));
                    user.setSex(rs.getString("sex"));
                    user.setSign(rs.getString("sign"));
                    user.setUserId(rs.getInt("user_id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(rs, ps, conn);
        }
        return user;
    }

    @Override
    public LinkedList<Event> findAllMines(int userId) {
        String sql = "SELECT  event.event_id, event.user_id, event.title,event.content,event.collection_number,event.praise_number,user.`user_name`,event.release_time FROM EVENT CROSS JOIN USER WHERE \n" +
                "user.`user_id` = event.`user_id` and event.user_id = ? ORDER BY event_id DESC ";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        LinkedList<Event> list = new LinkedList<>();
        try {
            conn = JdbcUtils.getConnection();
            ps = conn.prepareStatement(sql);
            if (ps != null) {
                ps.setInt(1, userId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Event event = new Event();
                    event.setTitle(rs.getString("title"));
                    event.setContent(rs.getString("content"));
                    event.setCollectionNum(rs.getInt("collection_number"));
                    event.setPraiseNum(rs.getInt("praise_number"));
                    event.setWriter(rs.getString("user_name"));
                    event.setReleaseTime(rs.getDate("release_time"));
                    event.setUserId(rs.getInt("user_id"));
                    event.setEventId(rs.getInt("event_id"));
                    list.add(event);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(rs, ps, conn);
        }
        return list;
    }


    @Override
    public boolean updateUserResume(User user, int userId) {
        String sql = "update user set user_name =? ,password = ?,sex = ?,sign = ?,phone =?,school = ? where user_id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        boolean judge = false;
        try {
            conn = JdbcUtils.getConnection();
            ps = conn.prepareStatement(sql);
            if (ps != null) {
                ps.setString(1, user.getName());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getSex());
                ps.setString(4, user.getSign());
                ps.setString(5, user.getPhone());
                ps.setString(6, user.getSchool());
                ps.setInt(7, userId);
                int i = ps.executeUpdate();
                if (i != 0) {
                    judge = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ps, conn);
        }
        return judge;
    }

    @Override
    public User findUserById(int userId) {
        String sql = "SELECT * FROM USER WHERE user_id = ? LIMIT 1 ;";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = new User();
        try {
            conn = JdbcUtils.getConnection();
            ps = conn.prepareStatement(sql);
            if (ps != null) {
                ps.setInt(1, userId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    user.setName(rs.getString("user_name"));
                    user.setAccount(rs.getString("user_account"));
                    user.setPassword(rs.getString("password"));
                    user.setPhone(rs.getString("phone"));
                    user.setPosition(rs.getInt("position"));
                    user.setSchool(rs.getString("school"));
                    user.setSex(rs.getString("sex"));
                    user.setSign(rs.getString("sign"));
                    user.setUserId(rs.getInt("user_id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(rs, ps, conn);
        }
        return user;
    }


}
