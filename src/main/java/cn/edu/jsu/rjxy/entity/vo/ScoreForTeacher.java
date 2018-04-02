package cn.edu.jsu.rjxy.entity.vo;

public class ScoreForTeacher {

  private long id;
  private Score score;
  private Teacher teacher;
  private Double result;
  private Double evaluateGrade;
  private Integer evaluateCount;
  private Double questionGrade;
  private Integer questionCount;
  private Double assessmentGrade;
  private Integer assessmentCount;
  private String remarks;

  public ScoreForTeacher() {
  }

  public ScoreForTeacher(long id) {
    this.id = id;
  }

  public ScoreForTeacher(Score score, Teacher teacher) {
    this.score = score;
    this.teacher = teacher;
  }

  public ScoreForTeacher(Score score, Teacher teacher, Double result) {
    this.score = score;
    this.teacher = teacher;
    this.result = result;
  }

  public ScoreForTeacher(long id, Score score, Teacher teacher,
      Double result, Double evaluateGrade, Integer evaluateCount, Double questionGrade,
      Integer questionCount, Double assessmentGrade, Integer assessmentCount) {
    this.id = id;
    this.score = score;
    this.teacher = teacher;
    this.result = result;
    this.evaluateGrade = evaluateGrade;
    this.evaluateCount = evaluateCount;
    this.questionGrade = questionGrade;
    this.questionCount = questionCount;
    this.assessmentGrade = assessmentGrade;
    this.assessmentCount = assessmentCount;
  }

  public ScoreForTeacher(long id, Score score, Teacher teacher,
      Double result, Double evaluateGrade, Integer evaluateCount,
      Double questionGrade, Integer questionCount, Double assessmentGrade,
      Integer assessmentCount, String remarks) {
    this.id = id;
    this.score = score;
    this.teacher = teacher;
    this.result = result;
    this.evaluateGrade = evaluateGrade;
    this.evaluateCount = evaluateCount;
    this.questionGrade = questionGrade;
    this.questionCount = questionCount;
    this.assessmentGrade = assessmentGrade;
    this.assessmentCount = assessmentCount;
    this.remarks = remarks;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Score getScore() {
    return score;
  }

  public void setScore(Score score) {
    this.score = score;
  }

  public Teacher getTeacher() {
    return teacher;
  }

  public void setTeacher(Teacher teacher) {
    this.teacher = teacher;
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

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  @Override
  public String toString() {
    return "ScoreForTeacher{" +
        "id=" + id +
        ", score=" + score +
        ", teacher=" + teacher +
        ", result=" + result +
        ", evaluateGrade=" + evaluateGrade +
        ", evaluateCount=" + evaluateCount +
        ", questionGrade=" + questionGrade +
        ", questionCount=" + questionCount +
        ", assessmentGrade=" + assessmentGrade +
        ", assessmentCount=" + assessmentCount +
        ", remarks='" + remarks + '\'' +
        '}';
  }
}
