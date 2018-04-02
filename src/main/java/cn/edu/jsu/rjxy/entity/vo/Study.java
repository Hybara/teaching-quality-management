package cn.edu.jsu.rjxy.entity.vo;

public class Study {

  private long id;
  private ScoreForTeacher scoreForTeacher;
  private Student student;

  public Study() {
  }

  public Study(ScoreForTeacher scoreForTeacher, Student student) {
    this.scoreForTeacher = scoreForTeacher;
    this.student = student;
  }

  public Study(long id, ScoreForTeacher scoreForTeacher, Student student) {
    this.id = id;
    this.scoreForTeacher = scoreForTeacher;
    this.student = student;
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

  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  @Override
  public String toString() {
    return "Study{" +
        "id=" + id +
        ", scoreForTeacher=" + scoreForTeacher +
        ", student=" + student +
        '}';
  }
}
