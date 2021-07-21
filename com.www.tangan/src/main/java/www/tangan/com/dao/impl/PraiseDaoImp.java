package www.tangan.com.dao.impl;

import www.tangan.com.bean.Event;
import www.tangan.com.dao.PraiseDao;
import www.tangan.com.util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;

import static www.tangan.com.util.CommonUtil.setObject;


/**
 * @author 谭淦
 */
public class PraiseDaoImp implements PraiseDao {

    private static PraiseDaoImp instance;

    private PraiseDaoImp() {
    }

    public static synchronized PraiseDaoImp getInstance() {
        if (instance == null) {
            instance = new PraiseDaoImp();
        }
        return instance;
    }


    @Override
    public boolean addToPraise(int userId, int eventId) {
        String sql = "insert into praise (user_id,event_id,praise_time) values(?,?,?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean judge = false;
        int i;
        try {
            connection = JdbcUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            if (preparedStatement != null) {
                setObject(preparedStatement, userId, eventId,new Date());

                i = preparedStatement.executeUpdate();
                if (i == 1) {
                    judge = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(preparedStatement, connection);
        }

        return judge;
    }


    @Override
    public boolean findPraise(int userId, int eventId) {
        String sql = "select * from praise where user_id = ? and event_id =?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        boolean judge = false;
        try {
            conn = JdbcUtils.getConnection();
            ps = conn.prepareStatement(sql);
            if (ps != null) {
                setObject(ps, userId, eventId);
                rs = ps.executeQuery();
                if (rs.next()) {
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
    public boolean deletePraise(int userId, int eventId) {
        String sql = "delete from praise where user_id =? and event_id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        boolean judge = false;
        try {
            conn = JdbcUtils.getConnection();
            ps = conn.prepareStatement(sql);
            if (ps != null) {
                ps.setInt(1, userId);
                ps.setInt(2, eventId);
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
    public LinkedList<Event> findAllPraise(int userId) {
/*        String sql = "SELECT DISTINCT event.event_id,event.title,event.content," +
                "event.release_time," +
                "event.praise_number,event.praise_number,event.user_id,event.collection_number" +
                " FROM EVENT  JOIN praise ON event.event_id = praise.event_id AND praise.`user_id` = ?";*/
        String sql = "SELECT * FROM (SELECT  event.event_id, event.user_id, event.title,event.content,\n" +
                "\tevent.collection_number,event.praise_number,user.`user_name`,event.release_time FROM EVENT CROSS JOIN USER WHERE \n" +
                "\t\t\tuser.`user_id` = event.`user_id` ORDER BY event_id DESC ) table1 CROSS JOIN  praise  WHERE table1.event_id = praise.`event_id`\n" +
                "\t\t\tAND praise.`user_id` = ? ";
        return JdbcUtils.findLinkedList(sql, userId);
    }


    @Override
    public LinkedList<Event> find7dayPraise(int userId) {
        String sql = " SELECT * FROM (SELECT  event.event_id, event.user_id, event.title,event.content,\n" +
                "                event.collection_number,event.praise_number,user.`user_name`,event.release_time FROM EVENT CROSS JOIN USER WHERE \n" +
                "  user.`user_id` = event.`user_id` ORDER BY event_id DESC LIMIT 16) table1 CROSS JOIN  praise  WHERE table1.event_id = praise.`event_id`\n" +
                "                AND praise.`user_id` = ? AND  DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= DATE(praise_time)\n";
        return JdbcUtils.findLinkedList(sql, userId);
    }


}
