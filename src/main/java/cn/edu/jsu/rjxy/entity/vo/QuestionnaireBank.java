package cn.edu.jsu.rjxy.entity.vo;

public class QuestionnaireBank {

  private long id;
  private QuestionnaireQuestionType type;
  private String title;
  private String remarks;
  private String contentA;
  private Double resultA;
  private String contentB;
  private Double resultB;
  private String contentC;
  private Double resultC;
  private String contentD;
  private Double resultD;

  public QuestionnaireBank() {
  }

  public QuestionnaireBank(long id) {
    this.id = id;
  }

  public QuestionnaireBank(long id, QuestionnaireQuestionType type, String title,
      String remarks, String contentA, Double resultA, String contentB, Double resultB) {
    this.id = id;
    this.type = type;
    this.title = title;
    this.remarks = remarks;
    this.contentA = contentA;
    this.resultA = resultA;
    this.contentB = contentB;
    this.resultB = resultB;
  }

  public QuestionnaireBank(long id, QuestionnaireQuestionType type, String title,
      String remarks, String contentA, Double resultA, String contentB, Double resultB,
      String contentC, Double resultC, String contentD, Double resultD) {

    this.id = id;
    this.type = type;
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

  public QuestionnaireQuestionType getType() {
    return type;
  }

  public void setType(QuestionnaireQuestionType type) {
    this.type = type;
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

  public Double getResultA() {
    return resultA;
  }

  public void setResultA(Double resultA) {
    this.resultA = resultA;
  }

  public String getContentB() {
    return contentB;
  }

  public void setContentB(String contentB) {
    this.contentB = contentB;
  }

  public Double getResultB() {
    return resultB;
  }

  public void setResultB(Double resultB) {
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
    return "QuestionnaireBank{" +
        "id=" + id +
        ", type=" + type +
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
