package www.tangan.com.bean;


import java.util.Date;

/**
 * @author 谭淦
 */
public class Event {
    private int eventId;
    private String title;
    private String content;
    private int collectionNum;
    private int praiseNum;
    private int userId;
    private Date releaseTime;
    private String writer;

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public Event(String title, String content, int collectionNum, int praiseNum, int userId, Date releaseTime, String writer) {
        this.title = title;
        this.content = content;
        this.collectionNum = collectionNum;
        this.praiseNum = praiseNum;
        this.userId = userId;
        this.releaseTime = releaseTime;
        this.writer = writer;
    }

    public Event() {
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCollectionNum() {
        return collectionNum;
    }

    public void setCollectionNum(int collectionNum) {
        this.collectionNum = collectionNum;
    }

    public int getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(int praiseNum) {
        this.praiseNum = praiseNum;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    @Override
    public String toString() {
        return "Event{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", collectionNum=" + collectionNum +
                ", praiseNum=" + praiseNum +
                ", releaseTime=" + releaseTime +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
