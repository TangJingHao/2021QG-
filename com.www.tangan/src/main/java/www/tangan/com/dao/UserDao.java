package www.tangan.com.dao;

import www.tangan.com.bean.Event;
import www.tangan.com.bean.User;

import java.util.LinkedList;

/**
 * @author 谭淦
 */
public interface UserDao {
    /**
     * 找到所有事件
     *
     * @return 返回找到的所有事件 以集合形式存起来
     */
    LinkedList<Event> findAllEvent();

    /**
     * 添加 瓜   的方法
     *
     * @param event 要添加的事件
     * @return 返回布尔值，便于判断是否插入成功
     */
    boolean addEvent(Event event);

    /**
     * 找user
     *
     * @param userAccount 传入登录界面时候的账号
     * @return 返回找到的user
     */
    User findUserByAccount(String userAccount);

    /**
     * 查询自己发布的瓜瓜
     *
     * @param userId 自己的id
     * @return 返回自己查询到的瓜瓜集合
     */
    LinkedList<Event> findAllMines(int userId);

    /**
     * 修改个人信息
     *
     * @param userId 自己的id
     * @return 修改正确了就返回true
     */
    boolean updateUserResume(User user, int userId);

    /**
     * 找user
     *
     * @param userId userid标识user
     * @return 返回找到的user
     */
    User findUserById(int userId);


}
