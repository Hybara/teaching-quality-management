package cn.edu.jsu.rjxy.entity.vo;

public class ReceiveMessage {

  private long id;
  private Message message;
  private long recipient;
  private String recipientType;
  private boolean flag;

  public ReceiveMessage() {
  }

  public ReceiveMessage(long id) {
    this.id = id;
  }

  public ReceiveMessage(Message message, long recipient, String recipientType, boolean flag) {
    this.message = message;
    this.recipient = recipient;
    this.recipientType = recipientType;
    this.flag = flag;
  }

  public ReceiveMessage(long id, Message message, long recipient, String recipientType,
      boolean flag) {
    this.id = id;
    this.message = message;
    this.recipient = recipient;
    this.recipientType = recipientType;
    this.flag = flag;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Message getMessage() {
    return message;
  }

  public void setMessage(Message message) {
    this.message = message;
  }

  public long getRecipient() {
    return recipient;
  }

  public void setRecipient(long recipient) {
    this.recipient = recipient;
  }

  public String getRecipientType() {
    return recipientType;
  }

  public void setRecipientType(String recipientType) {
    this.recipientType = recipientType;
  }

  public boolean isFlag() {
    return flag;
  }

  public void setFlag(boolean flag) {
    this.flag = flag;
  }

  @Override
  public String toString() {
    return "ReceiveMessage{" +
        "id=" + id +
        ", message=" + message +
        ", recipient=" + recipient +
        ", recipientType='" + recipientType + '\'' +
        ", flag=" + flag +
        '}';
  }
}
