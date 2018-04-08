package cn.edu.jsu.rjxy.entity.vo;

import java.util.Date;

public class QuestionnaireTemplate {

  private long id;
  private String name;
  private long creater;
  private Date createTime;
  private long updater;
  private Date updateTime;

  public QuestionnaireTemplate() {
  }

  public QuestionnaireTemplate(String name, long creater, Date createTime) {
    this.name = name;
    this.creater = creater;
    this.createTime = createTime;
  }

  public QuestionnaireTemplate(String name, long creater, Date createTime, long updater,
      Date updateTime) {
    this.name = name;
    this.creater = creater;
    this.createTime = createTime;
    this.updater = updater;
    this.updateTime = updateTime;
  }

  public QuestionnaireTemplate(long id, String name, long creater, Date createTime, long updater,
      Date updateTime) {
    this.id = id;
    this.name = name;
    this.creater = creater;
    this.createTime = createTime;
    this.updater = updater;
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

  public long getCreater() {
    return creater;
  }

  public void setCreater(long creater) {
    this.creater = creater;
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

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  @Override
  public String toString() {
    return "QuestionnaireTemplate{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", creater=" + creater +
        ", createTime=" + createTime +
        ", updater=" + updater +
        ", updateTime=" + updateTime +
        '}';
  }
}
