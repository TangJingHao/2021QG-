package www.tangan.com.service;

import www.tangan.com.bean.Comment;

import java.util.LinkedList;

/**
 * @author tangan
 */
public interface CommentService {
    /**
     * 发布成功就返回true
     *
     * @param comment 评论
     * @return 发布成功就返回true
     */

    boolean releaseComment(Comment comment);

    /**
     * 显示所有评论
     *
     * @param eventId 哪个瓜瓜的评论
     * @return comment LIST
     */

    LinkedList<Comment> showComment(int eventId);

    /**
     * 找到对应comment的方法
     *
     * @param commentId commentId comment的标识
     * @return 返回找到的comment对象
     */

    Comment findComment(int commentId);

    /**
     * 删除相应的comment
     *
     * @param commentId 通过commentId找到相应的comment
     * @return 成功了就true
     */

    boolean deleteComment(int commentId);
}
