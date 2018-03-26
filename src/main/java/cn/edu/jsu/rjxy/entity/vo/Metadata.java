package cn.edu.jsu.rjxy.entity.vo;

public class Metadata {

  private long id;
  private String key;
  private double value;

  public Metadata() {
  }

  public Metadata(long id) {
    this.id = id;
  }

  public Metadata(String key, double value) {
    this.key = key;
    this.value = value;
  }

  public Metadata(long id, String key, double value) {
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
    return "Metadata{" +
        "id=" + id +
        ", key='" + key + '\'' +
        ", value=" + value +
        '}';
  }
}
