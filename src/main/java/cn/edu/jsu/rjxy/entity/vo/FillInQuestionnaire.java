package cn.edu.jsu.rjxy.entity.vo;

import java.util.Date;

public class FillInQuestionnaire {

  private long id;
  private long questionnaire;
  private double result;
  private long creater;
  private Date createTime;
  private String createrType;

  public FillInQuestionnaire() {
  }

  public FillInQuestionnaire(long questionnaire, double result, long creater,
      Date createTime, String createrType) {
    this.questionnaire = questionnaire;
    this.result = result;
    this.creater = creater;
    this.createTime = createTime;
    this.createrType = createrType;
  }

  public FillInQuestionnaire(long id, long questionnaire, double result, long creater,
      Date createTime, String createrType) {
    this.id = id;
    this.questionnaire = questionnaire;
    this.result = result;
    this.creater = creater;
    this.createTime = createTime;
    this.createrType = createrType;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getQuestionnaire() {
    return questionnaire;
  }

  public void setQuestionnaire(long questionnaire) {
    this.questionnaire = questionnaire;
  }

  public double getResult() {
    return result;
  }

  public void setResult(double result) {
    this.result = result;
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

  public String getCreaterType() {
    return createrType;
  }

  public void setCreaterType(String createrType) {
    this.createrType = createrType;
  }

  @Override
  public String toString() {
    return "FillInQuestionnaire{" +
        "id=" + id +
        ", questionnaire=" + questionnaire +
        ", result=" + result +
        ", creater=" + creater +
        ", createTime=" + createTime +
        ", createrType='" + createrType + '\'' +
        '}';
  }
}
