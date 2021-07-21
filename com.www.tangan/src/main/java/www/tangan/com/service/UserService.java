package www.tangan.com.service;

import www.tangan.com.bean.Event;
import www.tangan.com.bean.User;

import java.util.LinkedList;

/**
 * @author tangan
 */
public interface UserService {
    /**
     * 创建一个list 装着所有账号
     *
     * @return 返回一个装着所有账号的链表
     */
    LinkedList<String> checkAccount();

    /**
     * 保存瓜的方法
     *
     * @param event 哪瓜
     * @return 成就true
     */
    boolean saveMelon(Event event);

    /**
     * 找到所有瓜瓜
     *
     * @return 成就  event  list
     */
    LinkedList<Event> findAllMelon();

    /**
     * 找到所有自己event的表
     *
     * @param userId 谁的event
     * @return 返回找到event List
     */
    LinkedList<Event> selectAllMines(int userId);

    /**
     * 找user
     *
     * @param account 登录界面传来的用户账号
     * @return 返回找到的user对象
     */
    User findUser(String account);

    /**
     * 找user
     *
     * @param userid 通过唯一标识找到user
     * @return 返回找到userid对象
     */
    User findUser(int userid);

    /**
     * 更新user的方法
     *
     * @param user   更新的内容
     * @param userid 谁更新
     * @return 成了就true
     */
    boolean updateUser(User user, int userid);
}
