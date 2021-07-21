package www.tangan.com.service.Impl;

import www.tangan.com.bean.Event;

import www.tangan.com.dao.PraiseDao;
import www.tangan.com.dao.impl.PraiseDaoImp;
import www.tangan.com.adapter.ServiceAdapter;


import java.util.LinkedList;

/**
 * @author 谭淦
 */
public class PraiseServiceImpl extends ServiceAdapter {

    private static PraiseServiceImpl instance;
    private final PraiseDao praiseDao = PraiseDaoImp.getInstance();

    private PraiseServiceImpl() {
    }

    /**
     * 单例模式
     *
     * @return 返回对应的对象
     */
    public static synchronized PraiseServiceImpl getInstance() {
        if (instance == null) {
            instance = new PraiseServiceImpl();
        }
        return instance;
    }


    /**
     * 加入点赞表的方法
     *
     * @param userId  谁点赞
     * @param eventId 点赞了啥
     * @return 成就true
     */
    @Override
    public boolean addPraise(int userId, int eventId) {

        return praiseDao.addToPraise(userId, eventId);
    }

    /**
     * 使得进入瓜瓜页面时候显示本来有多少赞
     *
     * @param userId  谁点赞
     * @param eventId 点了谁
     * @return 成了就true
     */
    @Override
    public boolean initAddPraise(int userId, int eventId) {

        return praiseDao.findPraise(userId, eventId);
    }

    /**
     * 取消点赞的方法 实际是在点赞表删除了该点赞信息
     *
     * @param userId  谁点赞
     * @param eventId 哪个事件
     * @return 成了就true
     */
    @Override
    public boolean cancelPraise(int userId, int eventId) {
        return praiseDao.deletePraise(userId, eventId);
    }

    /**
     * 找到点赞的表
     *
     * @param userId 谁点赞
     * @return 返回找到list表
     */
    @Override
    public LinkedList<Event> selectPraiseMelon(int userId) {
        return (praiseDao.findAllPraise(userId));
    }

    /**
     * 找到7天点赞的瓜瓜表
     *
     * @param useId 谁的点赞比表
     * @return 返回正确的7点赞的表
     */
    @Override
    public LinkedList<Event> select7DayPraise(int useId) {
        return (praiseDao.find7dayPraise(useId));
    }
}
