package cn.edu.jsu.rjxy.entity.vo;

public class QuestionnaireQuestionType {

  private long id;
  private String name;

  public QuestionnaireQuestionType() {
  }

  public QuestionnaireQuestionType(String name) {
    this.name = name;
  }

  public QuestionnaireQuestionType(long id, String name) {
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
    return "QuestionnaireQuestionType{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }
}
