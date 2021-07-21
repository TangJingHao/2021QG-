package www.tangan.com.service;

import www.tangan.com.bean.Event;

import java.util.LinkedList;

/**
 * @author 谭淦
 */
public interface PraiseService {
    /**
     * 加入点赞表的方法
     *
     * @param userId  谁点赞
     * @param eventId 点赞了啥
     * @return 成就true
     */
    boolean addPraise(int userId, int eventId);

    /**
     * 使得进入瓜瓜页面时候显示本来有多少赞
     *
     * @param userId  谁点赞
     * @param eventId 点了谁
     * @return 成了就true
     */
    boolean initAddPraise(int userId, int eventId);

    /**
     * 取消点赞的方法 实际是在点赞表删除了该点赞信息
     *
     * @param userId  谁点赞
     * @param eventId 哪个事件
     * @return 成了就true
     */
    boolean cancelPraise(int userId, int eventId);

    /**
     * 找到点赞的表
     *
     * @param userId 谁点赞
     * @return 返回找到list表
     */
    LinkedList<Event> selectPraiseMelon(int userId);

    /**
     * 找到7天点赞的瓜瓜表
     *
     * @param useId 谁的点赞比表
     * @return 返回正确的7点赞的表
     */
    LinkedList<Event> select7DayPraise(int useId);
}
