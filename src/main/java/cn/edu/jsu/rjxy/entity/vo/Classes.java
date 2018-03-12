package cn.edu.jsu.rjxy.entity.vo;

public class Classes {

  private long id;
  private String name;
  private Major major;
  private Term term;

  public Classes() {
  }

  public Classes(long id, String name, Major major, Term term) {
    this.id = id;
    this.name = name;
    this.major = major;
    this.term = term;
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

  @Override
  public String toString() {
    return "Classes{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", major=" + major +
        ", term=" + term +
        '}';
  }
}
