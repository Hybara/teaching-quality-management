package cn.edu.jsu.rjxy.entity.dto;

import cn.edu.jsu.rjxy.entity.vo.Teacher;

public class TeacherDTO {

  private long id;
  private String number;
  private String name;
  private String sex;
  private String business;
  private String email;
  private String phone;
  private String qq;
  private String wechat;
  private String header;
  private String evaluate;

  private long majorId;
  private String majorName;

  public TeacherDTO(Teacher teacher) {
    this.id = teacher.getId();
    this.name = teacher.getName();
    this.number = teacher.getNumber();
    this.sex = teacher.getSex() == 1 ? "男" : "女";
    this.business = teacher.getBusiness();

    this.email = teacher.getEmail();
    this.phone = teacher.getPhone();
    this.qq = teacher.getQq();
    this.wechat = teacher.getWechat();
    this.header = teacher.getHeader();
    this.evaluate = teacher.getEvaluate();

    this.majorId = teacher.getMajor().getId();
    this.majorName = teacher.getMajor().getName();
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

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
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

  public long getMajorId() {
    return majorId;
  }

  public void setMajorId(long majorId) {
    this.majorId = majorId;
  }

  public String getMajorName() {
    return majorName;
  }

  public void setMajorName(String majorName) {
    this.majorName = majorName;
  }

  @Override
  public String toString() {
    return "TeacherDTO{" +
        "id=" + id +
        ", number='" + number + '\'' +
        ", name='" + name + '\'' +
        ", sex='" + sex + '\'' +
        ", business='" + business + '\'' +
        ", email='" + email + '\'' +
        ", phone='" + phone + '\'' +
        ", qq='" + qq + '\'' +
        ", wechat='" + wechat + '\'' +
        ", header='" + header + '\'' +
        ", evaluate='" + evaluate + '\'' +
        ", majorId=" + majorId +
        ", majorName='" + majorName + '\'' +
        '}';
  }
}
