package cn.edu.jsu.rjxy.entity.vo;

public class Teacher {

  private long id;
  private String number;
  private String name;
  private int sex;
  private String business;
  private String email;
  private String phone;
  private String qq;
  private String wechat;
  private String password;
  private Major major;
  private String header;
  private String evaluate;

  public Teacher() {
  }

  public Teacher(long id) {
    this.id = id;
  }

  public Teacher(String number, String name, int sex, String password,
      Major major) {
    this.number = number;
    this.name = name;
    this.sex = sex;
    this.password = password;
    this.major = major;
  }

  public Teacher(long id, String number, String name, int sex, String business, String email,
      String phone, String qq, String wechat, String password, Major major, String header,
      String evaluate) {
    this.id = id;
    this.number = number;
    this.name = name;
    this.sex = sex;
    this.business = business;
    this.email = email;
    this.phone = phone;
    this.qq = qq;
    this.wechat = wechat;
    this.password = password;
    this.major = major;
    this.header = header;
    this.evaluate = evaluate;
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

  public int getSex() {
    return sex;
  }

  public void setSex(int sex) {
    this.sex = sex;
  }

  public String getBusiness() {
    return business;
  }

  public void setBusiness(String business) {
    this.business = business;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getQq() {
    return qq;
  }

  public void setQq(String qq) {
    this.qq = qq;
  }

  public String getWechat() {
    return wechat;
  }

  public void setWechat(String wechat) {
    this.wechat = wechat;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Major getMajor() {
    return major;
  }

  public void setMajor(Major major) {
    this.major = major;
  }

  public String getHeader() {
    return header;
  }

  public void setHeader(String header) {
    this.header = header;
  }

  public String getEvaluate() {
    return evaluate;
  }

  public void setEvaluate(String evaluate) {
    this.evaluate = evaluate;
  }

  @Override
  public String toString() {
    return "Teacher{" +
        "id=" + id +
        ", number='" + number + '\'' +
        ", name='" + name + '\'' +
        ", sex=" + sex +
        ", business='" + business + '\'' +
        ", email='" + email + '\'' +
        ", phone='" + phone + '\'' +
        ", qq='" + qq + '\'' +
        ", wechat='" + wechat + '\'' +
        ", password='" + password + '\'' +
        ", major=" + major +
        ", header='" + header + '\'' +
        ", evaluate='" + evaluate + '\'' +
        '}';
  }
}
