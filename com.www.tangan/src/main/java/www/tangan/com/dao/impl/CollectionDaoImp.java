package www.tangan.com.dao.impl;

import www.tangan.com.bean.Event;
import www.tangan.com.dao.CollectionDao;
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

public class CollectionDaoImp implements CollectionDao {


    private static CollectionDaoImp instance;

    private CollectionDaoImp() {
    }

    public static synchronized CollectionDaoImp getInstance() {
        if (instance == null) {
            instance = new CollectionDaoImp();
        }
        return instance;
    }


    @Override
    public boolean addToCollection(int userId, int eventId) {
        String sql = "insert into collection (user_id,event_id) values(?,?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean judge = false;
        int i;
        try {
            connection = JdbcUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            //用可变参数的方法简化了preparedStatement的set值
            if (preparedStatement != null) {
                setObject(preparedStatement, userId, eventId);
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
    public boolean findCollect(int userId, int eventId) {
        String sql = "select * from collection where user_id = ? and event_id =?";
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
    public boolean deleteCollect(int userId, int eventId) {
        String sql = "delete from collection where user_id =? and event_id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        boolean judge = false;
        try {
            conn = JdbcUtils.getConnection();
            ps = conn.prepareStatement(sql);
            if (ps != null) {
                setObject(ps, userId, eventId);
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
    public LinkedList<Event> findAllCollection(int userId) {
        String sql = "SELECT * FROM\t (SELECT  event.event_id, event.user_id, event.title,event.content,\n" +
                "\tevent.collection_number,event.praise_number,user.`user_name`,event.release_time FROM EVENT CROSS JOIN USER WHERE \n" +
                "\t\t\tuser.`user_id` = event.`user_id` ORDER BY event_id DESC) table1 CROSS JOIN  collection  WHERE table1.event_id = collection.`event_id`\n" +
                "\t\t\tAND collection.`user_id` = ? ";
        return JdbcUtils.findLinkedList(sql, userId);
    }


}
