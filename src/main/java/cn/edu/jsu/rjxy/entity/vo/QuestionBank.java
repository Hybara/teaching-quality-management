package cn.edu.jsu.rjxy.entity.vo;

public class QuestionBank {

  private long id;
  private QuestionType questionType;
  private String title;
  private String remarks;
  private String contentA;
  private double resultA;
  private String contentB;
  private double resultB;
  private String contentC;
  private Double resultC;
  private String contentD;
  private Double resultD;

  public QuestionBank() {
  }

  public QuestionBank(long id) {
    this.id = id;
  }

  public QuestionBank(QuestionType questionType, String title, String remarks,
      String contentA, double resultA, String contentB, double resultB) {
    this.questionType = questionType;
    this.title = title;
    this.remarks = remarks;
    this.contentA = contentA;
    this.resultA = resultA;
    this.contentB = contentB;
    this.resultB = resultB;
  }

  public QuestionBank(QuestionType questionType, String title, String remarks,
      String contentA, double resultA, String contentB, double resultB, String contentC,
      Double resultC) {
    this.questionType = questionType;
    this.title = title;
    this.remarks = remarks;
    this.contentA = contentA;
    this.resultA = resultA;
    this.contentB = contentB;
    this.resultB = resultB;
    this.contentC = contentC;
    this.resultC = resultC;
  }

  public QuestionBank(QuestionType questionType, String title, String remarks,
      String contentA, double resultA, String contentB, double resultB, String contentC,
      Double resultC, String contentD, Double resultD) {
    this.questionType = questionType;
    this.title = title;
    this.remarks = remarks;
    this.contentA = contentA;
    this.resultA = resultA;
    this.contentB = contentB;
    this.resultB = resultB;
    this.contentC = contentC;
    this.resultC = resultC;
    this.contentD = contentD;
    this.resultD = resultD;
  }

  public QuestionBank(long id, QuestionType questionType, String title, String remarks,
      String contentA, double resultA, String contentB, double resultB, String contentC,
      Double resultC, String contentD, Double resultD) {
    this.id = id;
    this.questionType = questionType;
    this.title = title;
    this.remarks = remarks;
    this.contentA = contentA;
    this.resultA = resultA;
    this.contentB = contentB;
    this.resultB = resultB;
    this.contentC = contentC;
    this.resultC = resultC;
    this.contentD = contentD;
    this.resultD = resultD;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public QuestionType getQuestionType() {
    return questionType;
  }

  public void setQuestionType(QuestionType questionType) {
    this.questionType = questionType;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public String getContentA() {
    return contentA;
  }

  public void setContentA(String contentA) {
    this.contentA = contentA;
  }

  public double getResultA() {
    return resultA;
  }

  public void setResultA(double resultA) {
    this.resultA = resultA;
  }

  public String getContentB() {
    return contentB;
  }

  public void setContentB(String contentB) {
    this.contentB = contentB;
  }

  public double getResultB() {
    return resultB;
  }

  public void setResultB(double resultB) {
    this.resultB = resultB;
  }

  public String getContentC() {
    return contentC;
  }

  public void setContentC(String contentC) {
    this.contentC = contentC;
  }

  public Double getResultC() {
    return resultC;
  }

  public void setResultC(Double resultC) {
    this.resultC = resultC;
  }

  public String getContentD() {
    return contentD;
  }

  public void setContentD(String contentD) {
    this.contentD = contentD;
  }

  public Double getResultD() {
    return resultD;
  }

  public void setResultD(Double resultD) {
    this.resultD = resultD;
  }

  @Override
  public String toString() {
    return "QuestionBank{" +
        "id=" + id +
        ", questionType=" + questionType +
        ", title='" + title + '\'' +
        ", remarks='" + remarks + '\'' +
        ", contentA='" + contentA + '\'' +
        ", resultA=" + resultA +
        ", contentB='" + contentB + '\'' +
        ", resultB=" + resultB +
        ", contentC='" + contentC + '\'' +
        ", resultC=" + resultC +
        ", contentD='" + contentD + '\'' +
        ", resultD=" + resultD +
        '}';
  }
}
