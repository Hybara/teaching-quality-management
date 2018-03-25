package cn.edu.jsu.rjxy.entity.vo;

public class System {

  private long id;
  private String key;
  private double value;

  public System() {
  }

  public System(long id) {
    this.id = id;
  }

  public System(String key, double value) {
    this.key = key;
    this.value = value;
  }

  public System(long id, String key, double value) {
    this.id = id;
    this.key = key;
    this.value = value;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public double getValue() {
    return value;
  }

  public void setValue(double value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "System{" +
        "id=" + id +
        ", key='" + key + '\'' +
        ", value=" + value +
        '}';
  }
}
