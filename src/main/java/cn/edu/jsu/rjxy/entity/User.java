package cn.edu.jsu.rjxy.entity;

import java.io.Serializable;

public class User implements Serializable {

  private String userId;
  private String userName;
  private String userPwd;

  public User() {
  }

  public User(String userId, String userName, String userPwd) {
    this.userId = userId;
    this.userName = userName;
    this.userPwd = userPwd;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserPwd() {
    return userPwd;
  }

  public void setUserPwd(String userPwd) {
    this.userPwd = userPwd;
  }

  @Override
  public String toString() {
    return "User{" +
        "userId='" + userId + '\'' +
        ", userName='" + userName + '\'' +
        ", userPwd='" + userPwd + '\'' +
        '}';
  }
}
