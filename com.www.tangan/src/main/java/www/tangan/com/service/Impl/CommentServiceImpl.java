package www.tangan.com.service.Impl;

import www.tangan.com.bean.Comment;
import www.tangan.com.dao.CommentDao;
import www.tangan.com.dao.impl.CommentDaoImp;
import www.tangan.com.adapter.ServiceAdapter;

import java.util.LinkedList;


/**
 * @author 谭淦
 */
public class CommentServiceImpl extends ServiceAdapter {


    private static CommentServiceImpl instance;
    private final CommentDao commentDao = CommentDaoImp.getInstance();

    private CommentServiceImpl() {
    }

    /**
     * 单例模式
     *
     * @return 返回CommentService
     */
    public static synchronized CommentServiceImpl getInstance() {
        if (instance == null) {
            instance = new CommentServiceImpl();
        }
        return instance;
    }


    /**
     * 发布成功就返回true
     *
     * @param comment 评论
     * @return 发布成功就返回true
     */

    @Override
    public boolean releaseComment(Comment comment) {

        return commentDao.addComment(comment);
    }

    /**
     * 显示所有评论
     *
     * @param eventId 哪个瓜瓜的评论
     * @return comment LIST
     */
    @Override
    public LinkedList<Comment> showComment(int eventId) {

        return commentDao.showAllComment(eventId);
    }

    /**
     * 找到对应comment的方法
     *
     * @param commentId commentId comment的标识
     * @return 返回找到的comment对象
     */
    @Override
    public Comment findComment(int commentId) {

        return commentDao.findCommentById(commentId);
    }

    /**
     * 删除相应的comment
     *
     * @param commentId 通过commentId找到相应的comment
     * @return 成功了就true
     */
    @Override
    public boolean deleteComment(int commentId) {

        return commentDao.deleteComment(commentId);
    }
}
