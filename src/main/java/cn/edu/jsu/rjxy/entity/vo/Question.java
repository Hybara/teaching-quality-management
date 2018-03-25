package cn.edu.jsu.rjxy.entity.vo;

public class Question {

  private long id;
  private ScoreForTeacher scoreForTeacher;
  private String title;
  private String text;
  private String result;
  private long creater;
  private String createrType;
  private boolean flag;

  public Question() {
  }

  public Question(long id) {
    this.id = id;
  }

  public Question(ScoreForTeacher scoreForTeacher, String text, String result, long creater,
      String createrType, boolean flag) {
    this.scoreForTeacher = scoreForTeacher;
    this.text = text;
    this.result = result;
    this.creater = creater;
    this.createrType = createrType;
    this.flag = flag;
  }

  public Question(long id, ScoreForTeacher scoreForTeacher, String title, String text,
      String result, long creater, String createrType, boolean flag) {
    this.id = id;
    this.scoreForTeacher = scoreForTeacher;
    this.title = title;
    this.text = text;
    this.result = result;
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

  public ScoreForTeacher getScoreForTeacher() {
    return scoreForTeacher;
  }

  public void setScoreForTeacher(ScoreForTeacher scoreForTeacher) {
    this.scoreForTeacher = scoreForTeacher;
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
    return "Evaluate{" +
        "id=" + id +
        ", scoreForTeacher=" + scoreForTeacher +
        ", title='" + title + '\'' +
        ", text='" + text + '\'' +
        ", result='" + result + '\'' +
        ", creater=" + creater +
        ", createrType='" + createrType + '\'' +
        ", flag=" + flag +
        '}';
  }
}
