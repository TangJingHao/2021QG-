package www.tangan.com.bean;

import java.util.Date;

/**
 * @author 谭淦
 */

public class Comment {
    private int id;
    private int eventId;
    private int userId;
    private String content;
    private String writer;
    private Date releaseTime;

    public Comment() {
    }

    public Comment(int id, int eventId, int userId, String content, String writer, Date releaseTime) {
        this.id = id;
        this.eventId = eventId;
        this.userId = userId;
        this.content = content;
        this.writer = writer;
        this.releaseTime = releaseTime;
    }

    public Comment(int eventId, int userId, String content, String writer, Date releaseTime) {
        this.eventId = eventId;
        this.userId = userId;
        this.content = content;
        this.writer = writer;
        this.releaseTime = releaseTime;
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

    public int getUserId() {

        return userId;
    }

    public void setUserId(int userId) {

        this.userId = userId;
    }

    public String getContent() {

        return content;
    }

    public void setContent(String content) {

        this.content = content;
    }

    public String getWriter() {

        return writer;
    }

    public void setWriter(String writer) {

        this.writer = writer;
    }

    public Date getReleaseTime() {

        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {

        this.releaseTime = releaseTime;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", eventId=" + eventId +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", releaseTime=" + releaseTime +
                '}';
    }
}
