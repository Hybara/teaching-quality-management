package cn.edu.jsu.rjxy.entity.vo;

public class Score {

  private long id;
  private String name;
  private Major major;
  private Term term;
  private ScoreType type;
  private Double credit;
  private Double hours;
  private String testWay;
  private String remarks;

  public Score() {
  }

  public Score(String name, Major major, Term term, ScoreType type) {
    this.name = name;
    this.major = major;
    this.term = term;
    this.type = type;
  }

  public Score(long id, String name, Major major, Term term, ScoreType type,
      Double credit, Double hours, String testWay, String remarks) {
    this.id = id;
    this.name = name;
    this.major = major;
    this.term = term;
    this.type = type;
    this.credit = credit;
    this.hours = hours;
    this.testWay = testWay;
    this.remarks = remarks;
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

  public Major getMajor() {
    return major;
  }

  public void setMajor(Major major) {
    this.major = major;
  }

  public Term getTerm() {
    return term;
  }

  public void setTerm(Term term) {
    this.term = term;
  }

  public ScoreType getType() {
    return type;
  }

  public void setType(ScoreType type) {
    this.type = type;
  }

  public Double getCredit() {
    return credit;
  }

  public void setCredit(Double credit) {
    this.credit = credit;
  }

  public Double getHours() {
    return hours;
  }

  public void setHours(Double hours) {
    this.hours = hours;
  }

  public String getTestWay() {
    return testWay;
  }

  public void setTestWay(String testWay) {
    this.testWay = testWay;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  @Override
  public String toString() {
    return "Score{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", major=" + major +
        ", term=" + term +
        ", type=" + type +
        ", credit=" + credit +
        ", hours=" + hours +
        ", testWay='" + testWay + '\'' +
        ", remarks='" + remarks + '\'' +
        '}';
  }
}
