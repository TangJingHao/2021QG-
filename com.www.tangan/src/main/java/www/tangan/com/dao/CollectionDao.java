package www.tangan.com.dao;

import www.tangan.com.bean.Event;

import java.util.LinkedList;

/**
 * @author 谭淦
 */
public interface CollectionDao {
    /**
     * 通过用户id和瓜瓜id加入收藏表，以此来绑定收藏的内容
     *
     * @param userId  用户id
     * @param eventId 瓜瓜id
     * @return 加入了就返回true
     */
    boolean addToCollection(int userId, int eventId);


    /**
     * 通过userId 和 eventId 来找到 对应收藏的collect
     *
     * @param userId  用户id
     * @param eventId 瓜瓜id
     * @return 找到就true
     */
    boolean findCollect(int userId, int eventId);

    /**
     * 通过userId 和 eventId 来对应的collection
     *
     * @param userId  用户id
     * @param eventId 瓜瓜id
     * @return 删除成功就返回true
     */
    boolean deleteCollect(int userId, int eventId);

    /**
     * 同过userId，找到他所有收藏的瓜瓜
     *
     * @param userId userId
     * @return 返回一个装着Event的双向链表
     */
    LinkedList<Event> findAllCollection(int userId);

}
