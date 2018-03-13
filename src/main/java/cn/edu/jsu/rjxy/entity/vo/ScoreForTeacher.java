package cn.edu.jsu.rjxy.entity.vo;

public class ScoreForTeacher {

  private long id;
  private Score score;
  private Teacher teacher;
  private StuForClass stuForClass;
  private Double result;
  private Double evaluateGrade;
  private Integer evaluateCount;
  private Double questionGrade;
  private Integer questionCount;
  private Double assessmentGrade;
  private Integer assessmentCount;

  public ScoreForTeacher() {
  }

  public ScoreForTeacher(Score score, Teacher teacher, StuForClass stuForClass) {
    this.score = score;
    this.teacher = teacher;
    this.stuForClass = stuForClass;
  }

  public ScoreForTeacher(Score score, Teacher teacher, StuForClass stuForClass, Double result) {
    this.score = score;
    this.teacher = teacher;
    this.stuForClass = stuForClass;
    this.result = result;
  }

  public ScoreForTeacher(long id, Score score, Teacher teacher,
      StuForClass stuForClass, Double result, Double evaluateGrade, Integer evaluateCount, Double questionGrade,
      Integer questionCount, Double assessmentGrade, Integer assessmentCount) {
    this.id = id;
    this.score = score;
    this.teacher = teacher;
    this.stuForClass = stuForClass;
    this.result = result;
    this.evaluateGrade = evaluateGrade;
    this.evaluateCount = evaluateCount;
    this.questionGrade = questionGrade;
    this.questionCount = questionCount;
    this.assessmentGrade = assessmentGrade;
    this.assessmentCount = assessmentCount;
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

  public StuForClass getStuForClass() {
    return stuForClass;
  }

  public void setStuForClass(StuForClass stuForClass) {
    this.stuForClass = stuForClass;
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
    return "ScoreForTeacher{" +
        "id=" + id +
        ", score=" + score +
        ", teacher=" + teacher +
        ", stuForClass=" + stuForClass +
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
