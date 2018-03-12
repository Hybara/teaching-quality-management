package cn.edu.jsu.rjxy.entity.vo;

public class QuestionType {

  private long id;
  private String name;

  public QuestionType() {
  }

  public QuestionType(String name) {
    this.name = name;
  }

  public QuestionType(long id, String name) {
    this.id = id;
    this.name = name;
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

  @Override
  public String toString() {
    return "QuestionType{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }
}
