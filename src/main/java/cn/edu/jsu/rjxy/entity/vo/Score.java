package cn.edu.jsu.rjxy.entity.vo;

public class Score {

  private long id;
  private String name;
  private String number;
  private Major major;
  private Term term;
  private ScoreType type;
  private Double credit;
  private Double hours;
  private String testWay;

  public Score() {
  }

  public Score(long id) {
    this.id = id;
  }

  public Score(String number, String name, Major major, Term term, ScoreType type) {
    this.number = number;
    this.name = name;
    this.major = major;
    this.term = term;
    this.type = type;
  }

  public Score(long id, String number, String name, Major major, Term term, ScoreType type,
      Double credit, Double hours, String testWay) {
    this.number = number;
    this.id = id;
    this.name = name;
    this.major = major;
    this.term = term;
    this.type = type;
    this.credit = credit;
    this.hours = hours;
    this.testWay = testWay;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
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

  @Override
  public String toString() {
    return "Score{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", number='" + number + '\'' +
        ", major=" + major +
        ", term=" + term +
        ", type=" + type +
        ", credit=" + credit +
        ", hours=" + hours +
        ", testWay='" + testWay + '\'' +
        '}';
  }
}
