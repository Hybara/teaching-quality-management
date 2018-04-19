package cn.edu.jsu.rjxy.entity.vo;

import java.util.Date;

public class Report {

  private long id;
  private long reportedId;
  private String reportedType;
  private String reason;
  private long creater;
  private Date createTime;
  private int handler;
  private Date handlerTime;

  public Report() {
  }

  public Report(long id) {
    this.id = id;
  }

  public Report(long reportedId, String reportedType, String reason, long creater,
      Date createTime) {
    this.reportedId = reportedId;
    this.reportedType = reportedType;
    this.reason = reason;
    this.creater = creater;
    this.createTime = createTime;
  }

  public Report(long id, long reportedId, String reportedType, String reason, long creater,
      Date createTime, int handler, Date handlerTime) {
    this.id = id;
    this.reportedId = reportedId;
    this.reportedType = reportedType;
    this.reason = reason;
    this.creater = creater;
    this.createTime = createTime;
    this.handler = handler;
    this.handlerTime = handlerTime;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getReportedId() {
    return reportedId;
  }

  public void setReportedId(long reportedId) {
    this.reportedId = reportedId;
  }

  public String getReportedType() {
    return reportedType;
  }

  public void setReportedType(String reportedType) {
    this.reportedType = reportedType;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
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

  public int getHandler() {
    return handler;
  }

  public void setHandler(int handler) {
    this.handler = handler;
  }

  public Date getHandlerTime() {
    return handlerTime;
  }

  public void setHandlerTime(Date handlerTime) {
    this.handlerTime = handlerTime;
  }

  @Override
  public String toString() {
    return "Report{" +
        "id=" + id +
        ", reportedId=" + reportedId +
        ", reportedType='" + reportedType + '\'' +
        ", reason='" + reason + '\'' +
        ", creater=" + creater +
        ", createTime=" + createTime +
        ", handler=" + handler +
        ", handlerTime=" + handlerTime +
        '}';
  }
}
