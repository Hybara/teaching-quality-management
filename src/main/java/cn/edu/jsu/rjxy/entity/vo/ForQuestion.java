package cn.edu.jsu.rjxy.entity.vo;

public class ForQuestion {

  private long id;
  private Question question;
  private String title;
  private String text;
  private long creater;
  private String createrType;
  private boolean flag;

  public ForQuestion() {
  }

  public ForQuestion(long id, Question question, String text, long creater,
      String createrType, boolean flag) {
    this.id = id;
    this.question = question;
    this.text = text;
    this.creater = creater;
    this.createrType = createrType;
    this.flag = flag;
  }

  public ForQuestion(long id, Question question, String title, String text, long creater,
      String createrType, boolean flag) {
    this.id = id;
    this.question = question;
    this.title = title;
    this.text = text;
    this.creater = creater;
    this.createrType = createrType;
    this.flag = flag;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Question getQuestion() {
    return question;
  }

  public void setQuestion(Question question) {
    this.question = question;
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

  public long getCreater() {
    return creater;
  }

  public void setCreater(long creater) {
    this.creater = creater;
  }

  public String getCreaterType() {
    return createrType;
  }

  public void setCreaterType(String createrType) {
    this.createrType = createrType;
  }

  public boolean isFlag() {
    return flag;
  }

  public void setFlag(boolean flag) {
    this.flag = flag;
  }

  @Override
  public String toString() {
    return "ForQuestion{" +
        "id=" + id +
        ", question=" + question +
        ", title='" + title + '\'' +
        ", text='" + text + '\'' +
        ", creater=" + creater +
        ", createrType='" + createrType + '\'' +
        ", flag=" + flag +
        '}';
  }
}
