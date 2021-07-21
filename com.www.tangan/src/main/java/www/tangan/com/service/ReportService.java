package www.tangan.com.service;

import www.tangan.com.bean.Report;

import java.util.LinkedList;

/**
 * @author tangan
 */
public interface ReportService {
    /**
     * 添加一个report 进表
     *
     * @param report 举报信息
     * @return 加入成功就返回true
     */
    boolean addToReport(Report report);

    /**
     * 找到所有report 把他封装到一个集合里面
     *
     * @return 返回一个装着
     */
    LinkedList<Report> findAllReport();

    /**
     * 通过reportId找到一个report对象
     *
     * @param reportId 传入的reportId
     * @return 如果找到了就返回找到的report
     */
    Report findReport(int reportId);

    /**
     * 通过reportId去删除指定的report
     *
     * @param reportId report 的标识 主键
     * @return 删除成功就返回true
     */
    boolean deleteReport(int reportId);
}
