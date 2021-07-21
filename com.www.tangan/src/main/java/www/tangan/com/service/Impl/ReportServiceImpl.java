package www.tangan.com.service.Impl;

import www.tangan.com.bean.Report;
import www.tangan.com.dao.ReportDao;
import www.tangan.com.dao.impl.ReportDaoImp;
import www.tangan.com.adapter.ServiceAdapter;

import java.util.LinkedList;

/**
 * @author 谭淦
 */
public class ReportServiceImpl extends ServiceAdapter {
    private static ReportServiceImpl instance;

    private ReportServiceImpl() {
    }

    /**
     * 单例模式
     *
     * @return 返回对应的对象
     */
    public static synchronized ReportServiceImpl getInstance() {
        if (instance == null) {
            instance = new ReportServiceImpl();
        }
        return instance;
    }

    ReportDao reportDao = ReportDaoImp.getInstance();

    /**
     * 添加一个report 进表
     *
     * @param report 举报信息
     * @return 加入成功就返回true
     */
    @Override
    public boolean addToReport(Report report) {
        return reportDao.addToReport(report);
    }

    /**
     * 找到所有report 把他封装到一个集合里面
     *
     * @return 返回一个装着
     */
    @Override
    public LinkedList<Report> findAllReport() {
        return reportDao.findAllReport();
    }

    /**
     * 通过reportId找到一个report对象
     *
     * @param reportId 传入的reportId
     * @return 如果找到了就返回找到的report
     */
    @Override
    public Report findReport(int reportId) {
        return reportDao.findReportById(reportId);
    }

    /**
     * 通过reportId去删除指定的report
     *
     * @param reportId report 的标识 主键
     * @return 删除成功就返回true
     */
    @Override
    public boolean deleteReport(int reportId) {
        return reportDao.deleteReportById(reportId);
    }
}

