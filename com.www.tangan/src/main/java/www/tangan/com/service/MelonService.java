package www.tangan.com.service;

import www.tangan.com.bean.Event;
import www.tangan.com.bean.User;

import java.util.LinkedList;

/**
 * @author 谭淦
 */
public interface MelonService {


    /**
     * 显示event的方法
     *
     * @param id 通过id找到event
     * @return 返回找到event
     */
    Event showEvent(int id);

    /**
     * 删除event
     *
     * @param eventId 哪个
     * @return 删了就true
     */
    boolean deleteEvent(int eventId);

    /**
     * 增加收藏数
     *
     * @param eventId 哪个event
     * @return 成功就true
     */
    boolean addCollectNum(int eventId);

    /**
     * 减少收藏数
     *
     * @param eventId 确定哪个event
     * @return 成功了就true
     */
    boolean reduceCollectNum(int eventId);

    /**
     * 增加点赞数的方法
     *
     * @param eventId 确定哪个瓜
     * @return 成功了就true
     */
    boolean addPraise(int eventId);

    /**
     * 减少点赞数
     *
     * @param eventId 确定哪个瓜
     * @return 成功了就true
     */
    boolean reducePraise(int eventId);

    /**
     * 找到所有模糊查询的list
     *
     * @param str 模糊查询的关键字
     * @return event list
     */
    LinkedList<Event> findEventLike(String str);
}
