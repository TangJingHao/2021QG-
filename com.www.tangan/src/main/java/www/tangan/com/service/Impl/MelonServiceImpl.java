package www.tangan.com.service.Impl;

import www.tangan.com.bean.Event;
import www.tangan.com.dao.MelonDao;
import www.tangan.com.dao.impl.MelonDaoImp;
import www.tangan.com.adapter.ServiceAdapter;

import java.util.LinkedList;

/**
 * @author 谭淦
 */
public class MelonServiceImpl extends ServiceAdapter {
    private static MelonServiceImpl instance;
    private final MelonDao melonDao = MelonDaoImp.getInstance();

    private MelonServiceImpl() {
    }

    /**
     * 单例模式
     *
     * @return 返回回melonService
     */
    public static synchronized MelonServiceImpl getInstance() {
        if (instance == null) {
            instance = new MelonServiceImpl();
        }
        return instance;
    }


    /**
     * 显示event的方法
     *
     * @param id 通过id找到event
     * @return 返回找到event
     */
    @Override
    public Event showEvent(int id) {
        return melonDao.findEventById(id);
    }

    /**
     * 删除event
     *
     * @param eventId 哪个
     * @return 删了就true
     */
    @Override
    public boolean deleteEvent(int eventId) {
        return melonDao.deleteEventById(eventId);
    }

    /**
     * 增加收藏数
     *
     * @param eventId 哪个event
     * @return 成功就true
     */
    @Override
    public boolean addCollectNum(int eventId) {
        return melonDao.addCollectionNum(eventId);
    }

    /**
     * 减少收藏数
     *
     * @param eventId 确定哪个event
     * @return 成功了就true
     */
    @Override
    public boolean reduceCollectNum(int eventId) {
        return melonDao.reduceCollectionNum(eventId);
    }

    /**
     * 增加点赞数的方法
     *
     * @param eventId 确定哪个瓜
     * @return 成功了就true
     */
    @Override
    public boolean addPraise(int eventId) {
        return melonDao.addPraiseNum(eventId);
    }

    /**
     * 减少点赞数
     *
     * @param eventId 确定哪个瓜
     * @return 成功了就true
     */
    @Override
    public boolean reducePraise(int eventId) {
        return melonDao.reducePraiseNum(eventId);
    }

    /**
     * 找到所有模糊查询的list
     *
     * @param str 模糊查询的关键字
     * @return event list
     */
    @Override
    public LinkedList<Event> findEventLike(String str) {
        return melonDao.findAllEventLike(str);
    }


}
