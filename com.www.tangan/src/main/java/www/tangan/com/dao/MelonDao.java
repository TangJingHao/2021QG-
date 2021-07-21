package www.tangan.com.dao;

import www.tangan.com.bean.Event;
import www.tangan.com.bean.User;

import java.util.LinkedList;

/**
 * @author 谭淦
 */
public interface MelonDao {
    /**
     * 通过userid来找user
     *
     * @param id userId
     * @return 返回找到的user
     */
    User findUserById(int id);

    /**
     * 通过userid 来找event
     *
     * @param id event_id
     * @return 返回找到的event
     */
    Event findEventById(int id);

    /**
     * 通过 userid 来删除 event
     *
     * @param id eventId
     * @return 返回是否删除正确的布尔值
     */
    boolean deleteEventById(int id);

    /**
     * 通过 eventId 来使collectionNum加一
     *
     * @param eventId 传来的eventId
     * @return 返回布尔
     */
    boolean addCollectionNum(int eventId);

    /**
     * 通过 eventId 来删除collection
     *
     * @param eventId 传入的eventId
     * @return 返回
     */
    boolean reduceCollectionNum(int eventId);

    /**
     * 通过 event 来增加praise数
     *
     * @param eventId eventId 传入的eventId event的唯一标识符
     * @return 添加成功就返回true
     */
    boolean addPraiseNum(int eventId);

    /**
     * 通过eventId 来减少event表中 praise数
     *
     * @param eventId 传入的 eventId
     * @return 减少成功就返回true
     */
    boolean reducePraiseNum(int eventId);

    /**
     * 通过传入的关键字来找到所有包含这关键字的event
     *
     * @param str 关键字
     * @return 返回含有该关键字的所有集合
     */
    LinkedList<Event> findAllEventLike(String str);
}
