package cn.edu.jsu.rjxy.entity.vo;

import java.util.Date;

public class ForQuestion {

  private long id;
  private Question question;
  private String text;
  private long creater;
  private String createrType;
  private Date createTime;
  private boolean flag;

  public ForQuestion() {
  }

  public ForQuestion(long id) {
    this.id = id;
  }

  public ForQuestion(long id, Question question, String text, long creater,
      String createrType, Date createTime, boolean flag) {
    this.id = id;
    this.question = question;
    this.text = text;
    this.creater = creater;
    this.createrType = createrType;
    this.createTime = createTime;
    this.flag = flag;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Question getQuestion() {
    return question;
  }

  public void setQuestion(Question question) {
    this.question = question;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
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

  public boolean isFlag() {
    return flag;
  }

  public void setFlag(boolean flag) {
    this.flag = flag;
  }

  @Override
  public String toString() {
    return "ForQuestion{" +
        "id=" + id +
        ", question=" + question +
        ", text='" + text + '\'' +
        ", creater=" + creater +
        ", createrType='" + createrType + '\'' +
        ", createTime=" + createTime +
        ", flag=" + flag +
        '}';
  }
}
