package www.tangan.com.dao;

import www.tangan.com.bean.Report;

import java.util.LinkedList;

/**
 * @author 谭淦
 */
public interface ReportDao {
    /**
     * 一个report举报信息加入 举报表格中
     *
     * @param report 举报对象
     * @return 加入成功则返回true
     */
    boolean addToReport(Report report);

    /**
     * 找到所有的举报信息
     *
     * @return 返回一个装有所有举报信息的集合
     */
    LinkedList<Report> findAllReport();

    /**
     * 通过reportId的对应的report信息找到
     *
     * @param reportId 传进来的reportId
     * @return 返回找到的report对象
     */
    Report findReportById(int reportId);

    /**
     * 通过reportId去删除指定的 举报信息
     *
     * @param reportId 举报信息的id
     * @return 删除成功就返回true
     */
    boolean deleteReportById(int reportId);

}
