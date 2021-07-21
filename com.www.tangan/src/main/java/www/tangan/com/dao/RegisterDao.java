package www.tangan.com.dao;

import www.tangan.com.bean.User;

import java.util.LinkedList;

/**
 * @author 谭淦
 */
public interface RegisterDao {
    /**
     * 向数据库里面新添加一个用户
     *
     * @param user 准备添加的user
     * @return 返回布尔  便于判断添加是否成功
     */
    boolean addUser(User user);

    /**
     * 找到所有用户的账号
     *
     * @return 返回一个装有所有账号的集合
     */
    LinkedList<String> findAllUserAccount();
}
