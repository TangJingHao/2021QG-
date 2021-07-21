package www.tangan.com.dao;

/**
 * @author 谭淦
 */
public interface LoginDao {
    /**
     * 通过用户账号去找到它的密码
     *
     * @param userAccount 传入主界面输入的用户名
     * @return 返回它的密码
     */
    String findPassword(String userAccount);
}
