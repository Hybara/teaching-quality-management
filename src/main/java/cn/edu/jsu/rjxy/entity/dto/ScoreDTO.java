package cn.edu.jsu.rjxy.entity.dto;

import cn.edu.jsu.rjxy.entity.vo.Score;
import cn.edu.jsu.rjxy.entity.vo.ScoreForTeacher;
import cn.edu.jsu.rjxy.mappers.ScoreForTeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class ScoreDTO {

  private long id;
  private String scoreNumber;
  private String scoreName;
  private String scoreTypeName;
  private String teacherName;
  private String teacherBusiness;
  private Double result;
  private Double evaluateGrade;
  private Integer evaluateCount;
  private Double questionGrade;
  private Integer questionCount;
  private Double assessmentGrade;
  private Integer assessmentCount;

  public ScoreDTO(ScoreForTeacher scoreForTeacher) {
    this.id = scoreForTeacher.getId();
    this.scoreNumber = scoreForTeacher.getScore().getNumber();
    this.scoreName = scoreForTeacher.getScore().getName();
    this.scoreTypeName = scoreForTeacher.getScore().getType().getName();
    this.teacherName = scoreForTeacher.getTeacher().getName();
    this.teacherBusiness = scoreForTeacher.getTeacher().getBusiness();
    this.result = scoreForTeacher.getResult();
    this.evaluateGrade = scoreForTeacher.getEvaluateGrade();
    this.evaluateCount = scoreForTeacher.getEvaluateCount();
    this.questionGrade = scoreForTeacher.getQuestionGrade();
    this.questionCount = scoreForTeacher.getQuestionCount();
    this.assessmentGrade = scoreForTeacher.getAssessmentGrade();
    this.assessmentCount = scoreForTeacher.getAssessmentCount();
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
    return "ScoreDTO{" +
        "id=" + id +
        ", scoreNumber='" + scoreNumber + '\'' +
        ", scoreName='" + scoreName + '\'' +
        ", scoreTypeName='" + scoreTypeName + '\'' +
        ", teacherName='" + teacherName + '\'' +
        ", teacherBusiness='" + teacherBusiness + '\'' +
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
