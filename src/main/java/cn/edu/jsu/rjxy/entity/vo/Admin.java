package cn.edu.jsu.rjxy.entity.vo;

import java.util.Date;

public class Admin {

  private long id;
  private String account;
  private String password;
  private Admin creater;
  private Date createTime;

  public Admin() {
  }

  public Admin(long id, String account, String password, Admin creater, Date createTime) {
    this.id = id;
    this.account = account;
    this.password = password;
    this.creater = creater;
    this.createTime = createTime;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Admin getCreater() {
    return creater;
  }

  public void setCreater(Admin creater) {
    this.creater = creater;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  @Override
  public String toString() {
    return "Admin{" +
        "id=" + id +
        ", account='" + account + '\'' +
        ", password='" + password + '\'' +
        ", creater=" + creater +
        ", createTime=" + createTime +
        '}';
  }
}