package cn.edu.jsu.rjxy.controller.student;

import cn.edu.jsu.rjxy.entity.vo.Student;
import cn.edu.jsu.rjxy.service.MessageService;
import cn.edu.jsu.rjxy.service.ScoreService;
import cn.edu.jsu.rjxy.service.ScoreTypeService;
import cn.edu.jsu.rjxy.service.StudentService;
import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class StudentController {

  @Autowired
  private ScoreTypeService scoreTypeService;
  @Autowired
  private ScoreService scoreService;
  @Autowired
  private MessageService messageService;
  @Autowired
  private StudentService studentService;

  private int NO_DATA = 0;
  private int SCORES_PAGE_SIZE = 8;
  private String ALL_SCORE_TYPE = "all";
  private String NO_SEARCH = null;
  private String MESSAGE_RECIPIENT_TYPE = "student";
  private String NO_READ_MESSAGE_TYPE = "noread";

  private String DEFAULT_MESSAGE_TYPE = "all";
  private int MESSAGE_PAGE_SIZE = 8;

  @RequestMapping("/student/login/{token}")
  public String login(@PathVariable String token, HttpSession session, Model model) {
    Student student = (Student) session.getAttribute(token);
    model.addAttribute("scoreTypes", scoreTypeService.getAll());
    model.addAttribute("token", token);
    int scoreCount = scoreService
        .getScoresCountInCurrentTerm(ALL_SCORE_TYPE, student.getId(), NO_SEARCH);
    model.addAttribute("scoreCount",
        scoreCount == NO_DATA ? NO_DATA : Math.ceil((double) scoreCount / SCORES_PAGE_SIZE));
    model.addAttribute("noReadMessage", messageService
        .getMessagesCountByRecipientAndRecipientTypeAndFlag(student.getId(), MESSAGE_RECIPIENT_TYPE,
            NO_READ_MESSAGE_TYPE));
    return "/student/scores";
  }

  @RequestMapping("/student/goScore/{id}/{token}")
  public String goScore(@PathVariable Long id, @PathVariable String token, HttpSession session,
      Model model) {
    if (session.getAttribute(token) == null) {
      return "forward:/logout/" + token;
    } else if (id == null) {
      return "/student/getScores/all/" + token;
    }
    model.addAttribute("scoreInfo", scoreService.getScoreByScoreForTeacherId(id));
    model.addAttribute("token", token);
    return "/student/score";
  }

  @RequestMapping("/student/goEvaluate/{token}")
  public String goEvaluate(@PathVariable String token, HttpSession session) {
    Student student = (Student) session.getAttribute(token);
    System.out.println(student);
    if (student == null) {
      return "forward:/logout/" + token;
    } else {
      return "/student/evaluate";
    }
  }

  @RequestMapping("/student/goMessage/{token}")
  public String goMessage(@PathVariable String token, HttpSession session, Model model) {
    Student student = (Student) session.getAttribute(token);
    int messageCount = messageService
        .getMessagesCountByRecipientAndRecipientTypeAndFlag(student.getId(), MESSAGE_RECIPIENT_TYPE,
            DEFAULT_MESSAGE_TYPE);
    model.addAttribute("count",
        messageCount == NO_DATA ? NO_DATA : Math.ceil((double) messageCount / MESSAGE_PAGE_SIZE));
    if (student != null) {
      return "/student/message";
    } else {
      return "forward:/logout/" + token;
    }
  }

  @RequestMapping("/student/goHeader/{token}")
  public String goHeader(@PathVariable String token, HttpSession session, Model model) {
    Student student = (Student) session.getAttribute(token);
    model.addAttribute("message", "");
    if (student != null) {
      return "/student/changeHeader";
    } else {
      return "forward:/logout/" + token;
    }
  }

  //https://my.oschina.net/qjedu/blog/1550704
  @RequestMapping("/student/changeHeader/{token}")
  public String changeHeader(@RequestParam(value = "file") MultipartFile file,
      @PathVariable String token, HttpSession session, Model model) {
    Student student = (Student) session.getAttribute(token);
    if (student == null) {
      return "forward:/logout/" + token;
    }
    if (file.isEmpty()) {
      model.addAttribute("message", "文件为空");
      return "/student/changeHeader";
    }
    if (uploadHeader(file, student)) {
      String fileName = student.getNumber().getBytes().toString();
      String suffixName = file.getOriginalFilename()
          .substring(file.getOriginalFilename().lastIndexOf("."));
      String header = fileName + suffixName;
      if (studentService.setHeader(student.getId(), header)) {
        student = studentService.getLoginer(student.getNumber(), student.getPassword());
        session.setAttribute(token, student);
        model.addAttribute("message", "上传成功");
      } else {
        model.addAttribute("message", "上传失败");
      }
    } else {
      model.addAttribute("message", "上传失败");
    }
    return "/student/changeHeader";
  }

  private boolean uploadHeader(MultipartFile file, Student student) {
    // 设置文件名
    String fileName = student.getNumber().getBytes().toString();
    // 获取文件的后缀名
    String suffixName = file.getOriginalFilename()
        .substring(file.getOriginalFilename().lastIndexOf("."));

    try {

      //获取跟目录
      File path = new File(ResourceUtils.getURL("classpath:").getPath());
      if (!path.exists()) {
        path = new File("");
      }

      //如果上传目录为/static/images/upload/，则可以如下获取：
      File filePath = new File(path.getAbsolutePath(), "static/img/header/");
      if (!filePath.exists()) {
        filePath.mkdirs();
      }

      File dest = new File(filePath + fileName + suffixName);
      if (!dest.getParentFile().exists()) {
        dest.getParentFile().mkdirs();
      }
      file.transferTo(dest);
    } catch (IllegalStateException | IOException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

}
