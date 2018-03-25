package cn.edu.jsu.rjxy.entity.dto;

import cn.edu.jsu.rjxy.entity.vo.Evaluate;
import cn.edu.jsu.rjxy.entity.vo.Register;
import cn.edu.jsu.rjxy.entity.vo.Student;
import cn.edu.jsu.rjxy.entity.vo.System;
import cn.edu.jsu.rjxy.entity.vo.Teacher;

public class EvaluateReaderDTO {

  private long id;
  private String userHeader;
  private String userName;
  private String title;
  private String text;
  private String result;

  public EvaluateReaderDTO(Evaluate evaluate, System evaluateLevel, Student evaluater) {
    this.id = evaluate.getId();
    this.title = evaluate.getTitle();
    this.text = evaluate.getText();
    this.result = evaluateLevel.getKey().substring(evaluateLevel.getKey().lastIndexOf(".")+1, evaluateLevel.getKey().length());

    if (evaluate.isFlag()) {
      this.userName = "匿名";
      this.userHeader = "/img/header.jpg";
    } else {
      this.userName = evaluater.getName();
      this.userHeader = evaluater.getHeader();
    }
  }

  public EvaluateReaderDTO(Evaluate evaluate, System evaluateLevel, Teacher evaluater) {
    this.id = evaluate.getId();
    this.title = evaluate.getTitle();
    this.text = evaluate.getText();
    this.result = evaluateLevel.getKey().substring(evaluateLevel.getKey().lastIndexOf(".")+1, evaluateLevel.getKey().length());

    if (evaluate.isFlag()) {
      this.userName = "匿名";
      this.userHeader = "/img/header.jpg";
    } else {
      this.userName = evaluater.getName();
      this.userHeader = evaluater.getHeader();
    }
  }

  public EvaluateReaderDTO(Evaluate evaluate, System evaluateLevel, Register evaluater) {
    this.id = evaluate.getId();
    this.title = evaluate.getTitle();
    this.text = evaluate.getText();
    this.result = evaluateLevel.getKey().substring(evaluateLevel.getKey().lastIndexOf(".")+1, evaluateLevel.getKey().length());

    if (evaluate.isFlag()) {
      this.userName = "匿名";
      this.userHeader = "/img/header.jpg";
    } else {
      this.userName = evaluater.getName();
      this.userHeader = evaluater.getHeader();
    }
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getUserHeader() {
    return userHeader;
  }

  public void setUserHeader(String userHeader) {
    this.userHeader = userHeader;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  @Override
  public String toString() {
    return "EvaluateReaderDTO{" +
        "id=" + id +
        ", userHeader='" + userHeader + '\'' +
        ", userName='" + userName + '\'' +
        ", title='" + title + '\'' +
        ", text='" + text + '\'' +
        ", result='" + result + '\'' +
        '}';
  }
}
