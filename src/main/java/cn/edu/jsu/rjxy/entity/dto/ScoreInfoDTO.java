package cn.edu.jsu.rjxy.entity.dto;

import cn.edu.jsu.rjxy.entity.vo.Score;
import cn.edu.jsu.rjxy.entity.vo.ScoreForTeacher;
import cn.edu.jsu.rjxy.entity.vo.Teacher;
import cn.edu.jsu.rjxy.mappers.ScoreForTeacherMapper;
import java.text.DecimalFormat;
import org.springframework.beans.factory.annotation.Autowired;

public class ScoreInfoDTO {

  private long id;

  private String scoreName;
  private String scoreCredit;
  private String scoreHours;
  private String scoreTestWay;
  private String scoreRemarks;
  private String scoreTypeName;

  private String teacherName;
  private String teacherBusiness;
  private String teacherSex;
  private String teacherEmail;
  private String teacherPhone;
  private String teacherQq;
  private String teacherWechat;

  private Double result;
  private Double evaluateGrade;
  private Integer evaluateCount;
  private Double questionGrade;
  private Integer questionCount;
  private Double assessmentGrade;
  private Integer assessmentCount;

  public ScoreInfoDTO(ScoreForTeacher scoreForTeacher) {
    this.id = scoreForTeacher.getId();
    initScore(scoreForTeacher.getScore());
    initTeacher(scoreForTeacher.getTeacher());
    this.result = scoreForTeacher.getResult();
    this.scoreRemarks = scoreForTeacher.getRemarks();
    initGrade(scoreForTeacher);
  }

  private void initScore(Score score) {
    this.scoreName = score.getName();
    this.scoreCredit = score.getCredit().toString();
    this.scoreHours = score.getHours().toString();
    this.scoreTypeName = score.getType().getName();
    this.scoreTestWay = score.getTestWay();
  }

  private void initTeacher(Teacher teacher) {
    this.teacherName = teacher.getName();
    this.teacherBusiness = teacher.getBusiness();
    this.teacherSex = teacher.getSex()==0?"女":"男";
    this.teacherEmail = teacher.getEmail()==null?"暂无":teacher.getEmail();
    this.teacherPhone = teacher.getPhone()==null?"暂无":teacher.getPhone();
    this.teacherQq = teacher.getQq()==null?"暂无":teacher.getQq();
    this.teacherWechat = teacher.getWechat()==null?"暂无":teacher.getWechat();
  }

  private void initGrade(ScoreForTeacher scoreForTeacher) {
    this.evaluateCount = scoreForTeacher.getEvaluateCount();
    this.questionCount = scoreForTeacher.getQuestionCount();
    this.assessmentCount = scoreForTeacher.getAssessmentCount();
    if (this.evaluateCount==0) {
      this.evaluateGrade = 0.0;
    } else {
      this.evaluateGrade = Double.parseDouble(String.format("%.2f", scoreForTeacher.getEvaluateGrade()/scoreForTeacher.getEvaluateCount()));
    }
    if (this.questionCount==0) {
      this.questionGrade = 0.0;
    } else {
      this.questionGrade = Double.parseDouble(String.format("%.2f", scoreForTeacher.getQuestionGrade()/scoreForTeacher.getQuestionCount()));
    }
    if (this.assessmentCount==0) {
      this.assessmentGrade = 0.0;
    } else {
      this.assessmentGrade = Double.parseDouble(String.format("%.2f", scoreForTeacher.getAssessmentGrade()/scoreForTeacher.getAssessmentCount()));
    }
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getScoreName() {
    return scoreName;
  }

  public void setScoreName(String scoreName) {
    this.scoreName = scoreName;
  }

  public String getScoreCredit() {
    return scoreCredit;
  }

  public void setScoreCredit(String scoreCredit) {
    this.scoreCredit = scoreCredit;
  }

  public String getScoreHours() {
    return scoreHours;
  }

  public void setScoreHours(String scoreHours) {
    this.scoreHours = scoreHours;
  }

  public String getScoreTestWay() {
    return scoreTestWay;
  }

  public void setScoreTestWay(String scoreTestWay) {
    this.scoreTestWay = scoreTestWay;
  }

  public String getScoreRemarks() {
    return scoreRemarks;
  }

  public void setScoreRemarks(String scoreRemarks) {
    this.scoreRemarks = scoreRemarks;
  }

  public String getScoreTypeName() {
    return scoreTypeName;
  }

  public void setScoreTypeName(String scoreTypeName) {
    this.scoreTypeName = scoreTypeName;
  }

  public String getTeacherName() {
    return teacherName;
  }

  public void setTeacherName(String teacherName) {
    this.teacherName = teacherName;
  }

  public String getTeacherBusiness() {
    return teacherBusiness;
  }

  public void setTeacherBusiness(String teacherBusiness) {
    this.teacherBusiness = teacherBusiness;
  }

  public String getTeacherSex() {
    return teacherSex;
  }

  public void setTeacherSex(String teacherSex) {
    this.teacherSex = teacherSex;
  }

  public String getTeacherEmail() {
    return teacherEmail;
  }

  public void setTeacherEmail(String teacherEmail) {
    this.teacherEmail = teacherEmail;
  }

  public String getTeacherPhone() {
    return teacherPhone;
  }

  public void setTeacherPhone(String teacherPhone) {
    this.teacherPhone = teacherPhone;
  }

  public String getTeacherQq() {
    return teacherQq;
  }

  public void setTeacherQq(String teacherQq) {
    this.teacherQq = teacherQq;
  }

  public String getTeacherWechat() {
    return teacherWechat;
  }

  public void setTeacherWechat(String teacherWechat) {
    this.teacherWechat = teacherWechat;
  }

  public Double getResult() {
    return result;
  }

  public void setResult(Double result) {
    this.result = result;
  }

  public Double getEvaluateGrade() {
    return evaluateGrade;
  }

  public void setEvaluateGrade(Double evaluateGrade) {
    this.evaluateGrade = evaluateGrade;
  }

  public Integer getEvaluateCount() {
    return evaluateCount;
  }

  public void setEvaluateCount(Integer evaluateCount) {
    this.evaluateCount = evaluateCount;
  }

  public Double getQuestionGrade() {
    return questionGrade;
  }

  public void setQuestionGrade(Double questionGrade) {
    this.questionGrade = questionGrade;
  }

  public Integer getQuestionCount() {
    return questionCount;
  }

  public void setQuestionCount(Integer questionCount) {
    this.questionCount = questionCount;
  }

  public Double getAssessmentGrade() {
    return assessmentGrade;
  }

  public void setAssessmentGrade(Double assessmentGrade) {
    this.assessmentGrade = assessmentGrade;
  }

  public Integer getAssessmentCount() {
    return assessmentCount;
  }

  public void setAssessmentCount(Integer assessmentCount) {
    this.assessmentCount = assessmentCount;
  }

  @Override
  public String toString() {
    return "ScoreInfoDTO{" +
        "id=" + id +
        ", scoreName='" + scoreName + '\'' +
        ", scoreCredit='" + scoreCredit + '\'' +
        ", scoreHours='" + scoreHours + '\'' +
        ", scoreTestWay='" + scoreTestWay + '\'' +
        ", scoreRemarks='" + scoreRemarks + '\'' +
        ", scoreTypeName='" + scoreTypeName + '\'' +
        ", teacherName='" + teacherName + '\'' +
        ", teacherBusiness='" + teacherBusiness + '\'' +
        ", teacherSex='" + teacherSex + '\'' +
        ", teacherEmail='" + teacherEmail + '\'' +
        ", teacherPhone='" + teacherPhone + '\'' +
        ", teacherQq='" + teacherQq + '\'' +
        ", teacherWechat='" + teacherWechat + '\'' +
        ", result=" + result +
        ", evaluateGrade=" + evaluateGrade +
        ", evaluateCount=" + evaluateCount +
        ", questionGrade=" + questionGrade +
        ", questionCount=" + questionCount +
        ", assessmentGrade=" + assessmentGrade +
        ", assessmentCount=" + assessmentCount +
        '}';
  }
}
