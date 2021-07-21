package www.tangan.com.dao.impl;

import www.tangan.com.bean.Comment;
import www.tangan.com.dao.CommentDao;
import www.tangan.com.util.JdbcUtils;

import java.sql.*;
import java.util.LinkedList;

import static www.tangan.com.util.CommonUtil.setObject;

/**
 * @author 谭淦
 */
public class CommentDaoImp implements CommentDao {

    private static CommentDaoImp instance;

    private CommentDaoImp() {
    }

    public static synchronized CommentDaoImp getInstance() {
        if (instance == null) {
            instance = new CommentDaoImp();
        }
        return instance;
    }

    @Override
    public boolean addComment(Comment comment) {
        String sql = "insert into comment (content,release_time,user_name,event_id,user_id) values (?,?,?,?,?)";
        Connection conn = null;
        PreparedStatement ps = null;
        boolean judge = false;
        try {
            conn = JdbcUtils.getConnection();
            ps = conn.prepareStatement(sql);
            if (ps != null) {
                setObject(ps, comment.getContent(), new java.sql.Date(comment.getReleaseTime().getTime()), comment.getWriter(), comment.getEventId(), comment.getUserId());
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
    public LinkedList<Comment> showAllComment(int eventId) {
        String sql = "SELECT  * FROM COMMENT  CROSS JOIN EVENT  WHERE  " +
                "comment.`event_id` = event.`event_id`   AND  event.`event_id` = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        LinkedList<Comment> list = new LinkedList<>();
        try {
            conn = JdbcUtils.getConnection();
            ps = conn.prepareStatement(sql);
            if (ps != null) {
                ps.setInt(1, eventId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Comment comment = new Comment(rs.getInt("comment_id"), eventId, rs.getInt("user_id"), rs.getString("content"),
                            rs.getString("user_name"), rs.getDate("release_time"));
                    list.add(comment);
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
    public Comment findCommentById(int commentId) {
        String sql = "SELECT  * FROM COMMENT  CROSS JOIN EVENT  WHERE  " +
                "comment.`event_id` = event.`event_id`   AND  comment.`comment_id` = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Comment comment = null;
        try {
            conn = JdbcUtils.getConnection();
            ps = conn.prepareStatement(sql);
            if (ps != null) {
                ps.setInt(1, commentId);
                rs = ps.executeQuery();
                while (rs.next()) {
                    comment = new Comment(rs.getInt("comment_id"), rs.getInt("event_id"),
                            rs.getInt("user_id"), rs.getString("content"), rs.getString("user_name"),
                            rs.getDate("release_time"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(rs, ps, conn);
        }
        return comment;
    }

    @Override
    public boolean deleteComment(int commentId) {
        String sql = "delete from comment where comment_id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        boolean judge = false;
        try {
            conn = JdbcUtils.getConnection();
            ps = conn.prepareStatement(sql);
            if (ps != null) {
                ps.setInt(1, commentId);
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


}
