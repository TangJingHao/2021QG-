package www.tangan.com.service.Impl;

import www.tangan.com.dao.LoginDao;
import www.tangan.com.dao.impl.LoginDaoImp;
import www.tangan.com.adapter.ServiceAdapter;


/**
 * @author 谭淦
 */
public class LoginServiceImpl extends ServiceAdapter {
    private final LoginDao loginDao = LoginDaoImp.getInstance();
    private static LoginServiceImpl instance;

    private LoginServiceImpl() {
    }

    public static synchronized LoginServiceImpl getInstance() {
        if (instance == null) {
            instance = new LoginServiceImpl();
        }
        return instance;
    }

    /**
     * 得到用户密码
     *
     * @param account 输入的account
     * @return 返回相应的密码
     */
    @Override
    public String getPassword(String account) {
        return loginDao.findPassword(account);

    }
}
