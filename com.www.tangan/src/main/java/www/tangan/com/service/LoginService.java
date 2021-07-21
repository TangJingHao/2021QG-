package www.tangan.com.service;

/**
 * @author tangan
 */
public interface LoginService {
    /**
     * 得到用户密码
     *
     * @param account 输入的account
     * @return 返回相应的密码
     */
    String getPassword(String account);
}
