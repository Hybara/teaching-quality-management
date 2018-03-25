package cn.edu.jsu.rjxy.entity.vo;

import java.util.Date;

public class Evaluate {

  private long id;
  private ScoreForTeacher scoreForTeacher;
  private String title;
  private String text;
  private double result;
  private long creater;
  private Date createTime;
  private String createrType;
  private boolean flag;

  public Evaluate() {
  }

  public Evaluate(long id) {
    this.id = id;
  }
  public Evaluate(ScoreForTeacher scoreForTeacher, String text, double result, long creater,
      Date createTime, String createrType, boolean flag) {
    this.scoreForTeacher = scoreForTeacher;
    this.text = text;
    this.result = result;
    this.creater = creater;
    this.createTime = createTime;
    this.createrType = createrType;
    this.flag = flag;
  }

  public Evaluate(long id, ScoreForTeacher scoreForTeacher, String title, String text,
      double result, long creater, Date createTime, String createrType, boolean flag) {
    this.id = id;
    this.scoreForTeacher = scoreForTeacher;
    this.title = title;
    this.text = text;
    this.result = result;
    this.creater = creater;
    this.createTime = createTime;
    this.createrType = createrType;
    this.flag = flag;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public ScoreForTeacher getScoreForTeacher() {
    return scoreForTeacher;
  }

  public void setScoreForTeacher(ScoreForTeacher scoreForTeacher) {
    this.scoreForTeacher = scoreForTeacher;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
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

  public boolean isFlag() {
    return flag;
  }

  public void setFlag(boolean flag) {
    this.flag = flag;
  }

  @Override
  public String toString() {
    return "Evaluate{" +
        "id=" + id +
        ", scoreForTeacher=" + scoreForTeacher +
        ", title='" + title + '\'' +
        ", text='" + text + '\'' +
        ", result=" + result +
        ", creater=" + creater +
        ", createTime=" + createTime +
        ", createrType='" + createrType + '\'' +
        ", flag=" + flag +
        '}';
  }
}
