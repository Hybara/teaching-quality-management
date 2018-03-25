package cn.edu.jsu.rjxy.entity.vo;

public class ScoreType {

  private long id;
  private String name;

  public ScoreType() {
  }

  public ScoreType(long id) {
    this.id = id;
  }

  public ScoreType(String name) {
    this.name = name;
  }

  public ScoreType(long id, String name) {
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
    return "ScoreType{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }
}
