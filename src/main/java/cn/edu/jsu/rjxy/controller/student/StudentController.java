package cn.edu.jsu.rjxy.controller.student;

import cn.edu.jsu.rjxy.entity.vo.Student;
import cn.edu.jsu.rjxy.service.MessageService;
import cn.edu.jsu.rjxy.service.ScoreService;
import cn.edu.jsu.rjxy.service.ScoreTypeService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentController {

  @Autowired
  private ScoreTypeService scoreTypeService;
  @Autowired
  private ScoreService scoreService;
  @Autowired
  private MessageService messageService;

  private int NO_DATA = 0;
  private int SCORES_PAGE_SIZE = 8;
  private String ALL_SCORE_TYPE = "all";
  private String NO_SEARCH = null;
  private String MESSAGE_RECIPIENT_TYPE = "student";
  private String NO_READ_MESSAGE_TYPE = "noread";

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

}
