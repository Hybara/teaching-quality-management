package cn.edu.jsu.rjxy.entity.vo;

public class ClassForTeacher {

  private long id;
  private Score score;
  private Teacher teacher;
  private Classes classes;
  private Double evaluateGrade;
  private Integer evaluateCount;
  private Double questionGrade;
  private Integer questionCount;
  private Double assessmentGrade;
  private Integer assessmentCount;

  public ClassForTeacher() {
  }

  public ClassForTeacher(Score score, Teacher teacher, Classes classes) {
    this.score = score;
    this.teacher = teacher;
    this.classes = classes;
  }

  public ClassForTeacher(long id, Score score, Teacher teacher,
      Classes classes, Double evaluateGrade, Integer evaluateCount, Double questionGrade,
      Integer questionCount, Double assessmentGrade, Integer assessmentCount) {
    this.id = id;
    this.score = score;
    this.teacher = teacher;
    this.classes = classes;
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

  public Classes getClasses() {
    return classes;
  }

  public void setClasses(Classes classes) {
    this.classes = classes;
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
    return "ClassForTeacher{" +
        "id=" + id +
        ", score=" + score +
        ", teacher=" + teacher +
        ", classes=" + classes +
        ", evaluateGrade=" + evaluateGrade +
        ", evaluateCount=" + evaluateCount +
        ", questionGrade=" + questionGrade +
        ", questionCount=" + questionCount +
        ", assessmentGrade=" + assessmentGrade +
        ", assessmentCount=" + assessmentCount +
        '}';
  }
}
