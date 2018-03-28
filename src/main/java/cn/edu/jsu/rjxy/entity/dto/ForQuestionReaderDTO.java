package cn.edu.jsu.rjxy.entity.dto;

import cn.edu.jsu.rjxy.entity.vo.ForQuestion;
import cn.edu.jsu.rjxy.entity.vo.Register;
import cn.edu.jsu.rjxy.entity.vo.Student;
import cn.edu.jsu.rjxy.entity.vo.Teacher;
import java.util.Date;

public class ForQuestionReaderDTO {

  private long id;
  private String text;
  private Date createTime;
  private String userName;
  private String userHeader;

  public ForQuestionReaderDTO(ForQuestion questionChild, Student questioner) {
    this.id = questionChild.getId();
    this.text = questionChild.getText();
    this.createTime = questionChild.getCreateTime();

    if (questionChild.isFlag()) {
      this.userName = "匿名";
      this.userHeader = "/img/header.jpg";
    } else {
      this.userName = questioner.getName();
      this.userHeader = questioner.getHeader();
    }
  }

  public ForQuestionReaderDTO(ForQuestion questionChild, Teacher questioner) {
    this.id = questionChild.getId();
    this.text = questionChild.getText();
    this.createTime = questionChild.getCreateTime();

    if (questionChild.isFlag()) {
      this.userName = "匿名";
      this.userHeader = "/img/header.jpg";
    } else {
      this.userName = questioner.getName();
      this.userHeader = questioner.getHeader();
    }
  }

  public ForQuestionReaderDTO(ForQuestion questionChild, Register questioner) {
    this.id = questionChild.getId();
    this.text = questionChild.getText();
    this.createTime = questionChild.getCreateTime();

    if (questionChild.isFlag()) {
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

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserHeader() {
    return userHeader;
  }

  public void setUserHeader(String userHeader) {
    this.userHeader = userHeader;
  }

  @Override
  public String toString() {
    return "ForQuestionReaderDTO{" +
        "id=" + id +
        ", text='" + text + '\'' +
        ", createTime=" + createTime +
        ", userName='" + userName + '\'' +
        ", userHeader='" + userHeader + '\'' +
        '}';
  }
}
