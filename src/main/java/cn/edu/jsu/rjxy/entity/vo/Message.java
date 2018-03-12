package cn.edu.jsu.rjxy.entity.vo;

import java.util.Date;

public class Message {

  private long id;
  private String title;
  private String content;
  private long creater;
  private String createrType;
  private Date createTime;

  public Message() {
  }

  public Message(String title, String content, long creater, String createrType,
      Date createTime) {
    this.title = title;
    this.content = content;
    this.creater = creater;
    this.createrType = createrType;
    this.createTime = createTime;
  }

  public Message(long id, String title, String content, long creater, String createrType,
      Date createTime) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.creater = creater;
    this.createrType = createrType;
    this.createTime = createTime;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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

  public long getCreater() {
    return creater;
  }

  public void setCreater(long creater) {
    this.creater = creater;
  }

  public String getCreaterType() {
    return createrType;
  }

  public void setCreaterType(String createrType) {
    this.createrType = createrType;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  @Override
  public String toString() {
    return "Message{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", content='" + content + '\'' +
        ", creater=" + creater +
        ", createrType='" + createrType + '\'' +
        ", createTime=" + createTime +
        '}';
  }
}
