package www.tangan.com.service;

import www.tangan.com.bean.Event;

import java.util.LinkedList;

/**
 * @author tangan
 */
public interface CollectionService {
    /**
     * 添加到收藏
     *
     * @param userId  谁要添加
     * @param eventId 添加的事件
     * @return 添加成功了就返回true
     */
    boolean addCollection(int userId, int eventId);

    /**
     * 点进瓜瓜界面让它显示本来有多少个收藏的方法
     *
     * @param userId  谁的收藏
     * @param eventId 哪个事件
     * @return 成功了就true
     */

    boolean initAddCollect(int userId, int eventId);

    /**
     * 取消收藏 收藏数减少1 的方法
     *
     * @param userId  谁的收藏
     * @param eventId 哪个事件
     * @return 成功了就true
     */

    boolean cancelCollect(int userId, int eventId);

    /**
     * 查到指定用户的收藏瓜瓜
     *
     * @param userId 谁的收藏瓜
     * @return 返回瓜集合
     */

    LinkedList<Event> selectCollectMelon(int userId);
}
