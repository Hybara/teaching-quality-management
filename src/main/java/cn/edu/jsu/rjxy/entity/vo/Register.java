package cn.edu.jsu.rjxy.entity.vo;

public class Register {

  private long id;
  private String number;
  private String name;
  private String password;
  private String header;

  public Register() {
  }

  public Register(String number, String name, String password) {
    this.number = number;
    this.name = name;
    this.password = password;
  }

  public Register(String number, String name, String password, String header) {
    this.number = number;
    this.name = name;
    this.password = password;
    this.header = header;
  }

  public Register(long id, String number, String name, String password, String header) {
    this.id = id;
    this.number = number;
    this.name = name;
    this.password = password;
    this.header = header;
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getHeader() {
    return header;
  }

  public void setHeader(String header) {
    this.header = header;
  }

  @Override
  public String toString() {
    return "Register{" +
        "id=" + id +
        ", number='" + number + '\'' +
        ", name='" + name + '\'' +
        ", password='" + password + '\'' +
        ", header='" + header + '\'' +
        '}';
  }
}
