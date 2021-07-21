package www.tangan.com.service.factory;

import www.tangan.com.service.Impl.*;
import www.tangan.com.adapter.ServiceAdapter;

import static www.tangan.com.util.Constant.*;

/**
 * @author 谭淦
 */
public class ServiceFactory {
    public static ServiceAdapter getService(String serviceType) {
        if (serviceType == null) {
            return null;
        }
        if (serviceType.equalsIgnoreCase(LOGIN_SERVICE)) {
            return LoginServiceImpl.getInstance();
        }
        if (serviceType.equalsIgnoreCase(REGISTER_SERVICE)) {
            return RegisterServiceImpl.getInstance();
        }
        if (serviceType.equalsIgnoreCase(USER_SERVICE)) {
            return UserServiceImpl.getInstance();
        }
        if (serviceType.equalsIgnoreCase(MELON_SERVICE)) {
            return MelonServiceImpl.getInstance();
        }
        if (serviceType.equalsIgnoreCase(COLLECTION_SERVICE)) {
            return CollectionServiceImpl.getInstance();
        }
        if (serviceType.equalsIgnoreCase(PRAISE_SERVICE)) {
            return PraiseServiceImpl.getInstance();
        }
        if (serviceType.equalsIgnoreCase(COMMENT_SERVICE)) {
            return CommentServiceImpl.getInstance();
        }
        if (serviceType.equalsIgnoreCase(REPORT_SERVICE)) {
            return ReportServiceImpl.getInstance();
        }
        return null;
    }
}
