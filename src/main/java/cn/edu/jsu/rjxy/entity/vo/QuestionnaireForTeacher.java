package cn.edu.jsu.rjxy.entity.vo;

import java.util.Date;

public class QuestionnaireForTeacher {

  private long id;
  private long scoreId;
  private long creater;
  private Date createTime;
  private long updater;
  private Date updateTime;
  private Date startTime;
  private Date endTime;

  public QuestionnaireForTeacher() {
  }

  public QuestionnaireForTeacher(long scoreId, long creater, Date createTime, Date startTime, Date endTime) {
    this.scoreId = scoreId;
    this.creater = creater;
    this.createTime = createTime;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  public QuestionnaireForTeacher(long scoreId, long creater, Date createTime, long updater,
      Date updateTime, Date startTime, Date endTime) {
    this.scoreId = scoreId;
    this.creater = creater;
    this.createTime = createTime;
    this.updater = updater;
    this.updateTime = updateTime;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  public QuestionnaireForTeacher(long id, long scoreId, long creater, Date createTime, long updater,
      Date updateTime, Date startTime, Date endTime) {
    this.id = id;
    this.scoreId = scoreId;
    this.creater = creater;
    this.createTime = createTime;
    this.updater = updater;
    this.updateTime = updateTime;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getScoreId() {
    return scoreId;
  }

  public void setScoreId(long scoreId) {
    this.scoreId = scoreId;
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

  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }

  @Override
  public String toString() {
    return "QuestionnaireForTeacher{" +
        "id=" + id +
        ", scoreId=" + scoreId +
        ", creater=" + creater +
        ", createTime=" + createTime +
        ", updater=" + updater +
        ", updateTime=" + updateTime +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        '}';
  }
}
