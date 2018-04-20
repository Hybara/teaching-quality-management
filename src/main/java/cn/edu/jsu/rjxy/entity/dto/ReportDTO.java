package cn.edu.jsu.rjxy.entity.dto;

import cn.edu.jsu.rjxy.entity.vo.Evaluate;
import cn.edu.jsu.rjxy.entity.vo.ForQuestion;
import cn.edu.jsu.rjxy.entity.vo.Question;
import cn.edu.jsu.rjxy.entity.vo.Report;

public class ReportDTO {

  private long id;
  private String content;
  private String reason;
  private long contentId;
  private String contentType;

  public ReportDTO(Report report, Evaluate evaluate) {
    this.id = report.getId();
    this.content = evaluate.getText();
    this.reason = report.getReason();
    this.contentId = evaluate.getId();
    this.contentType = report.getReportedType();
  }

  public ReportDTO(Report report, Question question) {
    this.id = report.getId();
    this.content = question.getText();
    this.reason = report.getReason();
    this.contentId = question.getId();
    this.contentType = report.getReportedType();
  }

  public ReportDTO(Report report, ForQuestion forQuestion) {
    this.id = report.getId();
    this.content = forQuestion.getText();
    this.reason = report.getReason();
    this.contentId = forQuestion.getId();
    this.contentType = report.getReportedType();
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public long getContentId() {
    return contentId;
  }

  public void setContentId(long contentId) {
    this.contentId = contentId;
  }

  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  @Override
  public String toString() {
    return "ReportDTO{" +
        "id=" + id +
        ", content='" + content + '\'' +
        ", reason='" + reason + '\'' +
        ", contentId=" + contentId +
        ", contentType='" + contentType + '\'' +
        '}';
  }
}
