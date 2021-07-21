package www.tangan.com.adapter;

import www.tangan.com.bean.Comment;
import www.tangan.com.bean.Event;
import www.tangan.com.bean.Report;
import www.tangan.com.bean.User;
import www.tangan.com.service.*;

import java.util.LinkedList;

/**
 * @author tangan
 */
public class ServiceAdapter implements LoginService, MelonService,
        PraiseService, RegisterService, CommentService,
        CollectionService, ReportService, UserService {

    @Override
    public String getPassword(String account) {

        return null;
    }

    @Override
    public Event showEvent(int id) {

        return null;
    }

    @Override
    public boolean deleteEvent(int eventId) {

        return false;
    }

    @Override
    public boolean addCollectNum(int eventId) {

        return false;
    }

    @Override
    public boolean reduceCollectNum(int eventId) {

        return false;
    }

    @Override
    public boolean addPraise(int eventId) {

        return false;
    }

    @Override
    public boolean reducePraise(int eventId) {

        return false;
    }

    @Override
    public LinkedList<Event> findEventLike(String str) {

        return null;
    }

    @Override
    public boolean register(User user) {

        return false;
    }

    @Override
    public LinkedList<String> checkAccount() {

        return null;
    }

    @Override
    public boolean saveMelon(Event event) {

        return false;
    }

    @Override
    public LinkedList<Event> findAllMelon() {

        return null;
    }

    @Override
    public LinkedList<Event> selectAllMines(int userId) {

        return null;
    }

    @Override
    public User findUser(String account) {

        return null;
    }

    @Override
    public User findUser(int userid) {

        return null;
    }

    @Override
    public boolean updateUser(User user, int userid) {

        return false;
    }

    @Override
    public boolean addCollection(int userId, int eventId) {

        return false;
    }

    @Override
    public boolean initAddCollect(int userId, int eventId) {

        return false;
    }

    @Override
    public boolean cancelCollect(int userId, int eventId) {

        return false;
    }

    @Override
    public LinkedList<Event> selectCollectMelon(int userId) {

        return null;
    }

    @Override
    public boolean releaseComment(Comment comment) {

        return false;
    }

    @Override
    public LinkedList<Comment> showComment(int eventId) {

        return null;
    }

    @Override
    public Comment findComment(int commentId) {

        return null;
    }

    @Override
    public boolean deleteComment(int commentId) {

        return false;
    }

    @Override
    public boolean addPraise(int userId, int eventId) {

        return false;
    }

    @Override
    public boolean initAddPraise(int userId, int eventId) {

        return false;
    }

    @Override
    public boolean cancelPraise(int userId, int eventId) {

        return false;
    }

    @Override
    public LinkedList<Event> selectPraiseMelon(int userId) {

        return null;
    }

    @Override
    public LinkedList<Event> select7DayPraise(int useId) {

        return null;
    }

    @Override
    public boolean addToReport(Report report) {

        return false;
    }

    @Override
    public LinkedList<Report> findAllReport() {

        return null;
    }

    @Override
    public Report findReport(int reportId) {

        return null;
    }

    @Override
    public boolean deleteReport(int reportId) {

        return false;
    }
}
