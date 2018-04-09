package cn.edu.jsu.rjxy.entity.vo;

public class QuestionnaireTemplateQuestion {

  private long id;
  private long templateId;
  private QuestionnaireBank question;
  private double coefficient;

  public QuestionnaireTemplateQuestion() {
  }

  public QuestionnaireTemplateQuestion(long templateId,
      QuestionnaireBank question, double coefficient) {
    this.templateId = templateId;
    this.question = question;
    this.coefficient = coefficient;
  }

  public QuestionnaireTemplateQuestion(long id, long templateId,
      QuestionnaireBank question, double coefficient) {
    this.id = id;
    this.templateId = templateId;
    this.question = question;
    this.coefficient = coefficient;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getTemplateId() {
    return templateId;
  }

  public void setTemplateId(long templateId) {
    this.templateId = templateId;
  }

  public QuestionnaireBank getQuestion() {
    return question;
  }

  public void setQuestion(QuestionnaireBank question) {
    this.question = question;
  }

  public double getCoefficient() {
    return coefficient;
  }

  public void setCoefficient(double coefficient) {
    this.coefficient = coefficient;
  }

  @Override
  public String toString() {
    return "QuestionnaireTemplateQuestion{" +
        "id=" + id +
        ", templateId=" + templateId +
        ", question=" + question +
        ", coefficient=" + coefficient +
        '}';
  }
}
