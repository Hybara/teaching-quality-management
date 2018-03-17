package cn.edu.jsu.rjxy.entity.dto;

import cn.edu.jsu.rjxy.entity.vo.ReceiveMessage;
import java.util.Date;

public class MessageReaderDTO {

  private long id;
  private String title;
  private String content;
  private Date createTime;
  private boolean flag;

  public MessageReaderDTO(ReceiveMessage receiveMessage) {
    this.id = receiveMessage.getId();
    this.title = receiveMessage.getMessage().getTitle();
    this.content = receiveMessage.getMessage().getContent();
    this.createTime = receiveMessage.getMessage().getCreateTime();
    this.flag = receiveMessage.isFlag();
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public boolean isFlag() {
    return flag;
  }

  public void setFlag(boolean flag) {
    this.flag = flag;
  }

  @Override
  public String toString() {
    return "MessageReaderDTO{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", content='" + content + '\'' +
        ", createTime=" + createTime +
        ", flag=" + flag +
        '}';
  }
}
