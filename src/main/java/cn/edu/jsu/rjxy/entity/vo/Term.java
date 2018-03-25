package cn.edu.jsu.rjxy.entity.vo;

import java.util.Date;

public class Term {

  private long id;
  private String name;
  private Date startTime;
  private Date endTime;

  public Term() {
  }

  public Term(long id) {
    this.id = id;
  }

  public Term(long id, String name, Date startTime, Date endTime) {
    this.id = id;
    this.name = name;
    this.startTime = startTime;
    this.endTime = endTime;
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
    return "Term{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        '}';
  }
}
