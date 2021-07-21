package www.tangan.com.util;

import www.tangan.com.bean.Event;

import java.sql.*;
import java.util.LinkedList;

/**
 * @author 谭淦
 */

public class JdbcUtils {


    /**
     * //数据库连接池
     */
    private static  ConnectionPool connPool = new ConnectionPool();

    /*** 从池中获取一个连接
     * @return 返回一个connection对象
     * @throws SQLException SQL异常
     */
    public static Connection getConnection()  throws SQLException{
        return connPool.getConnection();
    }

    public static void close(ResultSet rs, PreparedStatement ps, Connection conn) {

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();//归还连接
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(PreparedStatement ps, Connection conn) {
        close(null, ps, conn);
    }

    public static LinkedList<Event> findLinkedList(String sql, int userId) {
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
                    event.setReleaseTime(rs.getDate("release_time"));
                    event.setUserId(rs.getInt("user_id"));
                    event.setEventId(rs.getInt("event_id"));
                    event.setWriter(rs.getString("user_name"));
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



