package cn.edu.jsu.rjxy.entity.dto;

import cn.edu.jsu.rjxy.entity.vo.QuestionnaireBank;

public class QuestionnaireBankItemDTO {

  private long id;
  private long typeId;
  private String typeName;
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

  public QuestionnaireBankItemDTO() {

  }

  public QuestionnaireBankItemDTO(QuestionnaireBank questionnaireBank) {
    this.id = questionnaireBank.getId();
    this.title = questionnaireBank.getTitle();
    this.remarks = questionnaireBank.getRemarks();
    this.contentA = questionnaireBank.getContentA();
    this.resultA = questionnaireBank.getResultA();
    this.contentB = questionnaireBank.getContentB();
    this.resultB = questionnaireBank.getResultB();
    this.contentC = questionnaireBank.getContentC();
    this.resultC = questionnaireBank.getResultC();
    this.contentD = questionnaireBank.getContentD();
    this.resultD = questionnaireBank.getResultD();
    this.typeId = questionnaireBank.getType().getId();
    this.typeName = questionnaireBank.getType().getName();
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getTypeId() {
    return typeId;
  }

  public void setTypeId(long typeId) {
    this.typeId = typeId;
  }

  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
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
    return "QuestionnaireBankItemDTO{" +
        "id=" + id +
        ", typeId=" + typeId +
        ", typeName='" + typeName + '\'' +
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
