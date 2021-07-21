package www.tangan.com.dao;

import www.tangan.com.bean.Comment;

import java.util.LinkedList;

/**
 * @author 谭淦
 */
public interface CommentDao {
    /**
     * 发布评论的方法
     *
     * @param comment 传入的评论
     * @return 添加成功就返回true
     */
    boolean addComment(Comment comment);

    /**
     * 把一个瓜的所有评论都展示出来
     *
     * @param eventId 瓜瓜的id
     * @return list
     */
    LinkedList<Comment> showAllComment(int eventId);

    /**
     * 通过commentId 把comment对象给找到
     *
     * @param commentId commentId
     * @return 返回一个comment对象
     */
    Comment findCommentById(int commentId);

    /**
     * 删除评论的功能
     *
     * @param commentId 传入的commentId
     * @return 删除对了就返回true
     */
    boolean deleteComment(int commentId);
}
