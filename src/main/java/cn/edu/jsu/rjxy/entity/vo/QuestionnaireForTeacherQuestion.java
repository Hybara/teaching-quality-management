package cn.edu.jsu.rjxy.entity.vo;

public class QuestionnaireForTeacherQuestion {

  private long id;
  private long questionnaireId;
  private QuestionnaireBank question;
  private double coefficient;

  public QuestionnaireForTeacherQuestion() {
  }

  public QuestionnaireForTeacherQuestion(long questionnaireId,
      QuestionnaireBank question, double coefficient) {
    this.questionnaireId = questionnaireId;
    this.question = question;
    this.coefficient = coefficient;
  }

  public QuestionnaireForTeacherQuestion(long id, long questionnaireId,
      QuestionnaireBank question, double coefficient) {
    this.id = id;
    this.questionnaireId = questionnaireId;
    this.question = question;
    this.coefficient = coefficient;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getQuestionnaireId() {
    return questionnaireId;
  }

  public void setQuestionnaireId(long questionnaireId) {
    this.questionnaireId = questionnaireId;
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
    return "QuestionnaireForTeacherQuestion{" +
        "id=" + id +
        ", questionnaireId=" + questionnaireId +
        ", question=" + question +
        ", coefficient=" + coefficient +
        '}';
  }
}
