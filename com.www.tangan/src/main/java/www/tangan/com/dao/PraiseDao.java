package www.tangan.com.dao;

import www.tangan.com.bean.Event;

import java.util.LinkedList;

/**
 * @author 谭淦
 */
public interface PraiseDao {
    /**
     * 通过用户id和瓜瓜id加入点赞表，以此来绑定点赞的内容
     *
     * @param userId  用户id
     * @param eventId 瓜瓜id
     * @return 成功加入就返回true
     */
    boolean addToPraise(int userId, int eventId);

    /**
     * 通过userId 和 eventId 来找到 对应点赞
     *
     * @param userId  用户id
     * @param eventId 瓜瓜id
     * @return 找到就true
     */
    boolean findPraise(int userId, int eventId);

    /**
     * 通过userId 和 eventId 来对应的点赞列表
     *
     * @param userId  用户id
     * @param eventId 瓜瓜id
     * @return 删除成功就返回true
     */
    boolean deletePraise(int userId, int eventId);

    /**
     * 同过userId，找到他所有点赞的内容
     *
     * @param userId userId
     * @return 返回一个装着Event的双向链表
     */
    LinkedList<Event> findAllPraise(int userId);

    /**
     * 通过id 找到7天内点赞的瓜
     *
     * @param userId user的主键
     * @return 返回一个装着event的list
     */
    LinkedList<Event> find7dayPraise(int userId);
}
