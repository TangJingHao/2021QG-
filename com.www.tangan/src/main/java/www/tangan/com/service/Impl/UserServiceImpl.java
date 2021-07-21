package www.tangan.com.service.Impl;

import www.tangan.com.bean.Event;
import www.tangan.com.bean.User;
import www.tangan.com.dao.UserDao;
import www.tangan.com.dao.impl.UserDaoImp;
import www.tangan.com.adapter.ServiceAdapter;

import java.util.LinkedList;

/**
 * @author 谭淦
 */
public class UserServiceImpl extends ServiceAdapter {
    UserDao userDao = UserDaoImp.getInstance();

    private static UserServiceImpl instance;

    private UserServiceImpl() {
    }

    /**
     * 单例模式
     *
     * @return 返回对应的对象
     */
    public static synchronized UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    /**
     * 保存瓜的方法
     *
     * @param event 哪瓜
     * @return 成就true
     */
    @Override
    public boolean saveMelon(Event event) {
        if (event != null) {
            return userDao.addEvent(event);
        }
        return false;
    }

    /**
     * 找到所有瓜瓜
     *
     * @return 成就  event  list
     */
    @Override
    public LinkedList<Event> findAllMelon() {
        LinkedList<Event> list;
        list = userDao.findAllEvent();
        return list;
    }

    /**
     * 找到所有自己event的表
     *
     * @param userId 谁的event
     * @return 返回找到event List
     */
    @Override
    public LinkedList<Event> selectAllMines(int userId) {
        return userDao.findAllMines(userId);
    }

    /**
     * @param account 登录界面传来的用户账号
     * @return 返回找到的user对象
     */
    @Override
    public User findUser(String account) {
        User user = userDao.findUserByAccount(account);
        return user;
    }

    /**
     * @param userid 通过唯一标识找到user
     * @return 返回找到userid对象
     */
    @Override
    public User findUser(int userid) {
        User user = userDao.findUserById(userid);
        return user;
    }

    /**
     * 更新user的方法
     *
     * @param user   更新的内容
     * @param userid 谁更新
     * @return 成了就true
     */
    @Override
    public boolean updateUser(User user, int userid) {
        return userDao.updateUserResume(user, userid);
    }
}
