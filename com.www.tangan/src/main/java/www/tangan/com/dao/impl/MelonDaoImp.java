package www.tangan.com.dao.impl;

import www.tangan.com.bean.Event;
import www.tangan.com.bean.User;
import www.tangan.com.dao.MelonDao;
import www.tangan.com.util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * @author 谭淦
 */
public class MelonDaoImp implements MelonDao {

    private static MelonDaoImp instance;

    private MelonDaoImp() {
    }

    public static synchronized MelonDaoImp getInstance() {
        if (instance == null) {
            instance = new MelonDaoImp();
        }
        return instance;
    }

    @Override
    public User findUserById(int id) {
        String sql = "select * from user where user_id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = new User();
        try {
            conn = JdbcUtils.getConnection();
            ps = conn.prepareStatement(sql);
            if (ps != null) {
                ps.setInt(1, id);
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
    public Event findEventById(int id) {
        String sql = "select * from event where event_id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Event event = new Event();
        try {
            conn = JdbcUtils.getConnection();
            ps = conn.prepareStatement(sql);
            if (ps != null) {
                ps.setInt(1, id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    event.setTitle(rs.getString("title"));
                    event.setContent(rs.getString("content"));
                    event.setCollectionNum(rs.getInt("collection_number"));
                    event.setPraiseNum(rs.getInt("praise_number"));
                    event.setReleaseTime(rs.getDate("release_time"));
                    event.setUserId(rs.getInt("user_id"));
                    event.setEventId(rs.getInt("event_id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(rs, ps, conn);

        }
        return event;
    }

    @Override
    public boolean deleteEventById(int id) {
        String sql = "delete from event where event.event_id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        boolean judge = false;
        try {
            conn = JdbcUtils.getConnection();
            ps = conn.prepareStatement(sql);
            if (ps != null) {
                ps.setInt(1, id);
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
    public boolean addCollectionNum(int eventId) {
        String sql = "update event set event.collection_number = collection_number+1 where event_id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        boolean judge = false;
        int i;
        try {
            conn = JdbcUtils.getConnection();
            ps = conn.prepareStatement(sql);
            if (ps != null) {
                ps.setInt(1, eventId);
                i = ps.executeUpdate();
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
    public boolean reduceCollectionNum(int eventId) {
        String sql = "update event set event.collection_number = collection_number-1 where event_id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        boolean judge = false;
        int i;
        try {
            conn = JdbcUtils.getConnection();
            ps = conn.prepareStatement(sql);
            if (ps != null) {
                ps.setInt(1, eventId);
                i = ps.executeUpdate();
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
    public boolean addPraiseNum(int eventId) {
        String sql = "update event set event.praise_number = praise_number+1 where event_id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        boolean judge = false;
        int i;
        try {
            conn = JdbcUtils.getConnection();
            ps = conn.prepareStatement(sql);
            if (ps != null) {
                ps.setInt(1, eventId);
                i = ps.executeUpdate();
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
    public boolean reducePraiseNum(int eventId) {
        String sql = "update event set event.praise_number = praise_number-1 where event_id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        boolean judge = false;
        int i;
        try {
            conn = JdbcUtils.getConnection();
            ps = conn.prepareStatement(sql);
            if (ps != null) {
                ps.setInt(1, eventId);
                i = ps.executeUpdate();
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
    public LinkedList<Event> findAllEventLike(String str) {
        String sql = "SELECT  event.event_id, event.user_id, event.title,event.content,event.collection_number,event.praise_number,user.`user_name`,event.release_time FROM EVENT CROSS JOIN USER WHERE \n" +
                "user.`user_id` = event.`user_id` and (event.content like  ? or event.title like ? )ORDER BY event_id DESC ";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        LinkedList<Event> list = new LinkedList<>();
        try {
            conn = JdbcUtils.getConnection();
            ps = conn.prepareStatement(sql);
            if (ps != null) {
                ps.setString(1, "%" + str + "%");
                ps.setString(2, "%" + str + "%");
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


}
