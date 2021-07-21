package www.tangan.com.service;

import www.tangan.com.bean.User;

import java.util.LinkedList;

/**
 * @author 谭淦
 */
public interface RegisterService {
    /**
     * 创建一个list 装着所有账号
     *
     * @return 返回一个装着所有账号的链表
     */

    LinkedList<String> checkAccount();

    /**
     * @param user 传参传想添加了用户进来
     * @return 注册成功就返回true
     */
    boolean register(User user);
}
