package www.tangan.com.service.Impl;

import www.tangan.com.bean.User;
import www.tangan.com.dao.RegisterDao;
import www.tangan.com.dao.impl.RegisterDaoImp;
import www.tangan.com.adapter.ServiceAdapter;

import java.util.LinkedList;

/**
 * @author 谭淦
 */
public class RegisterServiceImpl extends ServiceAdapter {

    private final RegisterDao registerDao = RegisterDaoImp.getInstance();

    private static RegisterServiceImpl instance;

    private RegisterServiceImpl() {
    }

    /**
     * 单例模式
     *
     * @return 返回对应的对象
     */
    public static synchronized RegisterServiceImpl getInstance() {
        if (instance == null) {
            instance = new RegisterServiceImpl();
        }
        return instance;
    }


    /**
     * @param user 传参传想添加了用户进来
     * @return 注册成功就返回true
     */
    @Override
    public boolean register(User user) {
        boolean judge = false;
        if (user != null) {
            judge = registerDao.addUser(user);
        }
        return judge;
    }

    /**
     * 创建一个list 装着所有账号
     *
     * @return 返回一个装着所有账号的链表
     */
    @Override
    public LinkedList<String> checkAccount() {
        return registerDao.findAllUserAccount();
    }
}
