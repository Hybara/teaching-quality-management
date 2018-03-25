package cn.edu.jsu.rjxy.entity.vo;

public class Major {

  private long id;
  private String number;
  private String name;
  private Integer schooling;

  public Major() {
  }

  public Major(long id) {
    this.id = id;
  }

  public Major(int id, String number, String name, Integer schooling) {
    this.id = id;
    this.number = number;
    this.name = name;
    this.schooling = schooling;
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

  public Integer getSchooling() {
    return schooling;
  }

  public void setSchooling(Integer schooling) {
    this.schooling = schooling;
  }

  @Override
  public String toString() {
    return "Major{" +
        "id=" + id +
        ", number='" + number + '\'' +
        ", name='" + name + '\'' +
        ", schooling=" + schooling +
        '}';
  }
}
