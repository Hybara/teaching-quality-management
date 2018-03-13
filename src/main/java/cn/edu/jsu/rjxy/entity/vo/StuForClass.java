package cn.edu.jsu.rjxy.entity.vo;

public class StuForClass {

  private long id;
  private Student student;
  private Classes classes;

  public StuForClass() {
  }

  public StuForClass(long id, Student student, Classes classes) {
    this.id = id;
    this.student = student;
    this.classes = classes;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  public Classes getClasses() {
    return classes;
  }

  public void setClasses(Classes classes) {
    this.classes = classes;
  }

  @Override
  public String toString() {
    return "StuForClassMapper{" +
        "id=" + id +
        ", student=" + student +
        ", classes=" + classes +
        '}';
  }
}
