package www.tangan.com.service.Impl;

import www.tangan.com.bean.Event;
import www.tangan.com.dao.CollectionDao;
import www.tangan.com.dao.impl.CollectionDaoImp;
import www.tangan.com.adapter.ServiceAdapter;


import java.util.LinkedList;

/**
 * @author 谭淦
 */
public class CollectionServiceImpl extends ServiceAdapter {
    private final CollectionDao collectionDao = CollectionDaoImp.getInstance();
    private static CollectionServiceImpl instance;

    private CollectionServiceImpl() {
    }

    /**
     * 单例模式
     *
     * @return CollectionService
     */
    public static synchronized CollectionServiceImpl getInstance() {
        if (instance == null) {
            instance = new CollectionServiceImpl();
        }
        return instance;
    }

    /**
     * 添加到收藏
     *
     * @param userId  谁要添加
     * @param eventId 添加的事件
     * @return 添加成功了就返回true
     */
    @Override
    public boolean addCollection(int userId, int eventId) {
        return collectionDao.addToCollection(userId, eventId);
    }

    /**
     * 点进瓜瓜界面让它显示本来有多少个收藏的方法
     *
     * @param userId  谁的收藏
     * @param eventId 哪个事件
     * @return 成功了就true
     */
    @Override
    public boolean initAddCollect(int userId, int eventId) {
        return collectionDao.findCollect(userId, eventId);
    }

    /**
     * 取消收藏 收藏数减少1 的方法
     *
     * @param userId  谁的收藏
     * @param eventId 哪个事件
     * @return 成功了就true
     */
    @Override
    public boolean cancelCollect(int userId, int eventId) {
        return collectionDao.deleteCollect(userId, eventId);
    }

    /**
     * 查到指定用户的收藏瓜瓜
     *
     * @param userId 谁的收藏瓜
     * @return 返回瓜集合
     */
    @Override
    public LinkedList<Event> selectCollectMelon(int userId) {
        return collectionDao.findAllCollection(userId);
    }


}
