package cn.edu.jsu.rjxy.entity.dto;

import cn.edu.jsu.rjxy.entity.vo.Evaluate;
import cn.edu.jsu.rjxy.entity.vo.Metadata;
import cn.edu.jsu.rjxy.entity.vo.Question;
import cn.edu.jsu.rjxy.entity.vo.Register;
import cn.edu.jsu.rjxy.entity.vo.Student;
import cn.edu.jsu.rjxy.entity.vo.Teacher;

public class QuestionReaderDTO {

  private long id;
  private String userHeader;
  private String userName;
  private String title;
  private String text;
  private String result;

  public QuestionReaderDTO(Question question, Metadata questionLevel, Student questioner) {
    this.id = question.getId();
    this.title = question.getTitle();
    this.text = question.getText();

    if (questionLevel == null) {
      this.result = "notAnswer";
    } else {
      this.result = questionLevel.getKey().substring(questionLevel.getKey().lastIndexOf(".")+1, questionLevel.getKey().length());
    }

    if (question.isFlag()) {
      this.userName = "匿名";
      this.userHeader = "/img/header.jpg";
    } else {
      this.userName = questioner.getName();
      this.userHeader = questioner.getHeader();
    }
  }

  public QuestionReaderDTO(Question question, Metadata questionLevel, Teacher questioner) {
    this.id = question.getId();
    this.title = question.getTitle();
    this.text = question.getText();

    if (questionLevel == null) {
      this.result = "notAnswer";
    } else {
      this.result = questionLevel.getKey().substring(questionLevel.getKey().lastIndexOf(".")+1, questionLevel.getKey().length());
    }

    if (question.isFlag()) {
      this.userName = "匿名";
      this.userHeader = "/img/header.jpg";
    } else {
      this.userName = questioner.getName();
      this.userHeader = questioner.getHeader();
    }
  }

  public QuestionReaderDTO(Question question, Metadata questionLevel, Register questioner) {
    this.id = question.getId();
    this.title = question.getTitle();
    this.text = question.getText();

    if (questionLevel == null) {
      this.result = "notAnswer";
    } else {
      this.result = questionLevel.getKey().substring(questionLevel.getKey().lastIndexOf(".")+1, questionLevel.getKey().length());
    }

    if (question.isFlag()) {
      this.userName = "匿名";
      this.userHeader = "/img/header.jpg";
    } else {
      this.userName = questioner.getName();
      this.userHeader = questioner.getHeader();
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
