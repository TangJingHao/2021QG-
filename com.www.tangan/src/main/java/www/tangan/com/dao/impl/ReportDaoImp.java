package www.tangan.com.dao.impl;

import www.tangan.com.bean.Event;
import www.tangan.com.bean.Report;
import www.tangan.com.dao.ReportDao;
import www.tangan.com.service.factory.ServiceFactory;
import www.tangan.com.util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import static www.tangan.com.util.CommonUtil.setObject;
import static www.tangan.com.util.Constant.MELON_SERVICE;
import static www.tangan.com.util.Constant.USER_SERVICE;

/**
 * @author 谭淦
 */
public class ReportDaoImp implements ReportDao {
    private static ReportDaoImp instance;

    private ReportDaoImp() {
    }

    public static synchronized ReportDaoImp getInstance() {
        if (instance == null) {
            instance = new ReportDaoImp();
        }
        return instance;
    }

    @Override
    public boolean addToReport(Report report) {
        String sql = "insert into report (event_id,user_id,report_content,report_time) values (?,?,?,?)";
        Connection conn = null;
        PreparedStatement ps = null;
        boolean judge = false;
        try {
            conn = JdbcUtils.getConnection();
            ps = conn.prepareStatement(sql);
            if (ps != null) {
                setObject(ps, report.getEventId(), report.getUserId(), report.getContent(), new java.sql.Date(report.getReportTime().getTime()));
                int i = ps.executeUpdate();
                if (i != 0) {
                    judge = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ps, conn);
        }
        return judge;
    }

    @Override
    public LinkedList<Report> findAllReport() {
        String sql = "SELECT  * FROM EVENT CROSS JOIN REPORT WHERE \n" +
                "report.`event_id` = event.`event_id` ORDER BY report_id DESC LIMIT 16";
        LinkedList<Report> list = new LinkedList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            ps = conn.prepareStatement(sql);
            if (ps != null) {
                rs = ps.executeQuery();
                while (rs.next()) {
                    int userid = rs.getInt("user_id");
                    int eventId = rs.getInt("event_id");
                    String reporter = (ServiceFactory.getService(USER_SERVICE).findUser(userid)).getName();
                    Event event = (ServiceFactory.getService(MELON_SERVICE).showEvent(eventId));
                    String writer = event.getWriter();
                    String content = event.getContent();
                    String eventTitle = event.getTitle();
                    Report report = new Report(rs.getInt("report_id"), eventId, content, rs.getDate("report_time"), userid, writer, reporter, rs.getString("report_content"), eventTitle);
                    list.add(report);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(rs, ps, conn);
        }
        return list;
    }

    @Override
    public Report findReportById(int reportId) {
        String sql = "select * from report where report_id = ?";
        Report report = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            ps = conn.prepareStatement(sql);
            if (ps != null) {
                ps.setInt(1, reportId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    int userid = rs.getInt("user_id");
                    int eventId = rs.getInt("event_id");
                    String reporter = (ServiceFactory.getService(USER_SERVICE).findUser(userid)).getName();
                    Event event = (ServiceFactory.getService(MELON_SERVICE).showEvent(eventId));
                    String writer = event.getWriter();
                    String content = event.getContent();
                    String eventTitle = event.getTitle();
                    report = new Report(rs.getInt("report_id"), eventId, content, rs.getDate("report_time"), userid, writer, reporter, rs.getString("report_content"), eventTitle);

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(rs, ps, conn);
        }
        return report;
    }

    @Override
    public boolean deleteReportById(int reportId) {
        String sql = "delete from report where report_id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        boolean judge = false;
        try {
            conn = JdbcUtils.getConnection();
            ps = conn.prepareStatement(sql);
            if (ps != null) {
                ps.setInt(1, reportId);
                int i = ps.executeUpdate();
                if (i != 0) {
                    judge = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(ps, conn);
        }
        return judge;
    }
}
