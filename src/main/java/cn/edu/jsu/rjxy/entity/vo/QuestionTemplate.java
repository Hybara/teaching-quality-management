package cn.edu.jsu.rjxy.entity.vo;

import java.util.Date;

public class QuestionTemplate {

  private long id;
  private String name;
  private String questionList;
  private long creater;
  private String createrType;
  private Date createTime;
  private long updater;
  private String updaterType;
  private Date updateTime;

  public QuestionTemplate() {
  }

  public QuestionTemplate(long id) {
    this.id = id;
  }

  public QuestionTemplate(String name, String questionList, long creater,
      String createrType, Date createTime) {
    this.name = name;
    this.questionList = questionList;
    this.creater = creater;
    this.createrType = createrType;
    this.createTime = createTime;
  }

  public QuestionTemplate(String name, String questionList, long creater,
      String createrType, Date createTime, long updater, String updaterType,
      Date updateTime) {
    this.name = name;
    this.questionList = questionList;
    this.creater = creater;
    this.createrType = createrType;
    this.createTime = createTime;
    this.updater = updater;
    this.updaterType = updaterType;
    this.updateTime = updateTime;
  }

  public QuestionTemplate(long id, String name, String questionList, long creater,
      String createrType, Date createTime, long updater, String updaterType,
      Date updateTime) {
    this.id = id;
    this.name = name;
    this.questionList = questionList;
    this.creater = creater;
    this.createrType = createrType;
    this.createTime = createTime;
    this.updater = updater;
    this.updaterType = updaterType;
    this.updateTime = updateTime;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getQuestionList() {
    return questionList;
  }

  public void setQuestionList(String questionList) {
    this.questionList = questionList;
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

  public long getUpdater() {
    return updater;
  }

  public void setUpdater(long updater) {
    this.updater = updater;
  }

  public String getUpdaterType() {
    return updaterType;
  }

  public void setUpdaterType(String updaterType) {
    this.updaterType = updaterType;
  }

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  @Override
  public String toString() {
    return "QuestionTemplate{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", questionList='" + questionList + '\'' +
        ", creater=" + creater +
        ", createrType='" + createrType + '\'' +
        ", createTime=" + createTime +
        ", updater=" + updater +
        ", updaterType='" + updaterType + '\'' +
        ", updateTime=" + updateTime +
        '}';
  }
}
