package cn.edu.jsu.rjxy.entity.vo;

import java.util.Date;

public class Student {

  private long id;
  private String number;
  private String name;
  private int sex;
  private String idCard;
  private String header;
  private String password;
  private Date timeOfEnrollment;

  public Student() {
  }

  public Student(long id) {
    this.id = id;
  }

  public Student(String number, String name, int sex, String idCard, String password,
      Date timeOfEnrollment) {
    this.number = number;
    this.name = name;
    this.sex = sex;
    this.idCard = idCard;
    this.password = password;
    this.timeOfEnrollment = timeOfEnrollment;
  }

  public Student(long id, String number, String name, int sex, String idCard, String header,
      String password, Date timeOfEnrollment) {
    this.id = id;
    this.number = number;
    this.name = name;
    this.sex = sex;
    this.idCard = idCard;
    this.header = header;
    this.password = password;
    this.timeOfEnrollment = timeOfEnrollment;
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

  public String getIdCard() {
    return idCard;
  }

  public void setIdCard(String idCard) {
    this.idCard = idCard;
  }

  public String getHeader() {
    return header;
  }

  public void setHeader(String header) {
    this.header = header;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Date getTimeOfEnrollment() {
    return timeOfEnrollment;
  }

  public void setTimeOfEnrollment(Date timeOfEnrollment) {
    this.timeOfEnrollment = timeOfEnrollment;
  }

  @Override
  public String toString() {
    return "Student{" +
        "id=" + id +
        ", number='" + number + '\'' +
        ", name='" + name + '\'' +
        ", sex=" + sex +
        ", idCard='" + idCard + '\'' +
        ", header='" + header + '\'' +
        ", password='" + password + '\'' +
        ", timeOfEnrollment=" + timeOfEnrollment +
        '}';
  }
}
