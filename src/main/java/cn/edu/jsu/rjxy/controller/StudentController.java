package cn.edu.jsu.rjxy.controller;

import cn.edu.jsu.rjxy.entity.vo.Student;
import cn.edu.jsu.rjxy.service.MessageService;
import cn.edu.jsu.rjxy.service.ScoreService;
import cn.edu.jsu.rjxy.service.ScoreTypeService;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/student")
public class StudentController {

  @Autowired
  private ScoreTypeService scoreTypeService;
  @Autowired
  private ScoreService scoreService;
  @Autowired
  private MessageService messageService;

  private int NO_DATA = 0;
  private int SCORES_PAGE_SIZE = 8;
  private int INDEX_PAGE_NUMBER = 1;
  private String ALL_SCORE_TYPE = "all";
  private String NO_SEARCH = null;
  private String MESSAGE_RECIPIENT_TYPE = "student";
  private String DEFAULT_MESSAGE_TYPE = "all";
  private String NO_READ_MESSAGE_TYPE = "noread";
  private int JSON_DATA_MAP_SIZE = 2;
  private int MESSAGE_PAGE_SIZE = 8;

  @RequestMapping("/login/{token}")
  public String login(@PathVariable String token, HttpSession session, Model model) {
    Student student = (Student) session.getAttribute(token);
    model.addAttribute("scoreTypes", scoreTypeService.getAll());
    model.addAttribute("token", token);
    int scoreCount = scoreService
        .getScoresCountInCurrentTerm(ALL_SCORE_TYPE, student.getId(), NO_SEARCH);
    model.addAttribute("scoreCount",
        scoreCount == NO_DATA ? NO_DATA : (int)Math.ceil(scoreCount / SCORES_PAGE_SIZE));
    model.addAttribute("noReadMessage", messageService
        .getMessagesCountByRecipientAndRecipientTypeAndFlag(student.getId(), MESSAGE_RECIPIENT_TYPE,
            NO_READ_MESSAGE_TYPE));
    return "/student/scores";
  }

  @RequestMapping("/message/{token}")
  public String goMessage(@PathVariable String token, HttpSession session, Model model) {
    Student student = (Student) session.getAttribute(token);
    int messageCount = messageService
        .getMessagesCountByRecipientAndRecipientTypeAndFlag(student.getId(), MESSAGE_RECIPIENT_TYPE,
            DEFAULT_MESSAGE_TYPE);
    model.addAttribute("count",
        messageCount == NO_DATA ? NO_DATA : (int)Math.ceil(messageCount / MESSAGE_PAGE_SIZE));
    if (student != null) {
      return "/student/message";
    } else {
      return "/login";
    }
  }

  @RequestMapping("/getMessages/{token}")
  @ResponseBody
  public Map<String, Object> getMessages(@PathVariable String token, HttpSession session,
      String type, Integer page) {
    Student student = (Student) session.getAttribute(token);
    if (page == null) {
      page = INDEX_PAGE_NUMBER;
    }
    int messageCount = messageService
        .getMessagesCountByRecipientAndRecipientTypeAndFlag(student.getId(), MESSAGE_RECIPIENT_TYPE,
            type);
    return structuralResponseMap(
        messageService
            .getMessagesByRecipientAndRecipientTypeAndFlag(student.getId(), MESSAGE_RECIPIENT_TYPE,
                type, page, MESSAGE_PAGE_SIZE),
        messageCount == NO_DATA ? NO_DATA : (int)Math.ceil(messageCount/MESSAGE_PAGE_SIZE));
  }

  @RequestMapping("/getScores/{type}/{token}")
  @ResponseBody
  public Map<String, Object> getScores(@PathVariable String type, @PathVariable String token,
      HttpSession session, Integer page, String search) {
    Student student = (Student) session.getAttribute(token);
    if (page == null) {
      page = INDEX_PAGE_NUMBER;
    }
    int scoreCount = scoreService.getScoresCountInCurrentTerm(type, student.getId(), search);
    return structuralResponseMap(
        scoreService.getScoresInCurrentTerm(type, student.getId(), page, SCORES_PAGE_SIZE, search),
        scoreCount == NO_DATA ? NO_DATA : (int)Math.ceil(scoreCount / SCORES_PAGE_SIZE));
  }

  @RequestMapping("/getScore/{id}/{token}")
  public String getScore(@PathVariable Long id, @PathVariable String token, HttpSession session,
      Model model) {
    if (session.getAttribute(token) == null) {
      return "/login";
    } else if (id == null) {
      return "/student/getScores/all/" + token;
    }
    model.addAttribute("scoreInfo", scoreService.getScoreByScoreForTeacherId(id));
    model.addAttribute("token", token);
    return "/student/score";
  }

  private Map<String, Object> structuralResponseMap(Object data, int count) {
    Map<String, Object> responseMap = new HashMap<>(JSON_DATA_MAP_SIZE);
    responseMap.put("data", data);
    responseMap.put("count", count);
    return responseMap;
  }
}
