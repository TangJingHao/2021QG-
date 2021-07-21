package www.tangan.com.bean;

import java.util.Date;

/**
 * @author 谭淦
 */
public class Report {
    private int id;
    private int eventId;
    private String content;
    private Date reportTime;
    private int userId;
    private String writer;
    private String reporter;
    private String reportReason;
    private String title;

    public Report() {
    }

    public Report(int id, int eventId, String content, Date reportTime, int userId, String writer, String reporter, String reportReason, String title) {
        this.id = id;
        this.eventId = eventId;
        this.content = content;
        this.reportTime = reportTime;
        this.userId = userId;
        this.writer = writer;
        this.reporter = reporter;
        this.reportReason = reportReason;
        this.title = title;
    }

    public Report(int eventId, String content, Date reportTime, int userId) {

        this.eventId = eventId;
        this.content = content;
        this.reportTime = reportTime;
        this.userId = userId;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", eventId=" + eventId +
                ", content='" + content + '\'' +
                ", reportTime=" + reportTime +
                ", userId=" + userId +
                '}';
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public String getReportReason() {
        return reportReason;
    }

    public void setReportReason(String reportReason) {
        this.reportReason = reportReason;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
