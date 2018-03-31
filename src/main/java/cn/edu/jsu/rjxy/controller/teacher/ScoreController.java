package cn.edu.jsu.rjxy.controller.teacher;

import cn.edu.jsu.rjxy.entity.vo.ScoreForTeacher;
import cn.edu.jsu.rjxy.entity.vo.Teacher;
import cn.edu.jsu.rjxy.service.MessageService;
import cn.edu.jsu.rjxy.service.ScoreService;
import cn.edu.jsu.rjxy.service.ScoreTypeService;
import cn.edu.jsu.rjxy.util.JSONBaseUtil;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("teacherScoreController")
public class ScoreController {

  @Autowired
  private ScoreService scoreService;
  @Autowired
  private ScoreTypeService scoreTypeService;
  @Autowired
  private MessageService messageService;

  private static final int NO_DATA = 0;
  private static final int SCORES_PAGE_SIZE = 8;
  private static final String ALL_SCORE_TYPE = "all";
  private static final String NO_SEARCH = null;
  private static final String MESSAGE_RECIPIENT_TYPE = "teacher";
  private static final String NO_READ_MESSAGE_TYPE = "noread";
  private static final int INDEX_PAGE_NUMBER = 1;

  private static final String DEFAULT_MESSAGE_TYPE = "all";
  private static final int MESSAGE_PAGE_SIZE = 8;

  private static final String EVALUATE_CREATER_TYPE = "teacher";

  @Value("${img.url}")
  public String basePath;


  @RequestMapping("/teacher/myScores/{token}")
  public String goScores(@PathVariable String token, HttpSession session, Model model) {
    Teacher teacher = (Teacher) session.getAttribute(token);
    model.addAttribute("scoreTypes", scoreTypeService.getAll());
    model.addAttribute("token", token);
    int scoreCount = scoreService
        .getTeachScoresCountInCurrentTerm(ALL_SCORE_TYPE, teacher.getId(), NO_SEARCH);
    model.addAttribute("scoreCount",
        scoreCount == NO_DATA ? NO_DATA : Math.ceil((double) scoreCount / SCORES_PAGE_SIZE));
    model.addAttribute("noReadMessage", messageService
        .getMessagesCountByRecipientAndRecipientTypeAndFlag(teacher.getId(), MESSAGE_RECIPIENT_TYPE,
            NO_READ_MESSAGE_TYPE));
    return "/teacher/myScores";
  }

  @RequestMapping("/teacher/getMyScores/{type}/{token}")
  @ResponseBody
  public Map<String, Object> getScores(@PathVariable String type, @PathVariable String token,
      HttpSession session, Integer page, String search) {
    Teacher teacher = (Teacher) session.getAttribute(token);
    if (page == null) {
      page = INDEX_PAGE_NUMBER;
    }
    int scoreCount = scoreService.getTeachScoresCountInCurrentTerm(type, teacher.getId(), search);
    return JSONBaseUtil.structuralResponseMap(
        scoreService
            .getTeachScoresInCurrentTerm(type, teacher.getId(), page, SCORES_PAGE_SIZE, search),
        scoreCount == NO_DATA ? NO_DATA : Math.ceil((double) scoreCount / SCORES_PAGE_SIZE));
  }

  @RequestMapping("/teacher/goMyScore/{id}/{token}")
  public String goScore(@PathVariable Long id, @PathVariable String token, HttpSession session,
      Model model) {
    Teacher teacher = (Teacher) session.getAttribute(token);
    if (teacher == null) {
      return "redirect:/logout/" + token;
    } else if (id == null) {
      return "/teacher/getMyScores/all/" + token;
    }
    model.addAttribute("scoreInfo", scoreService.getScoreByScoreForTeacherId(id));
    model.addAttribute("token", token);
    return "/teacher/myScore";
  }

  @RequestMapping("/teacher/setRemarks")
  @ResponseBody
  public String setRemarks(Long id, String token, String remarks,
      HttpSession session) {
    Teacher teacher = (Teacher) session.getAttribute(token);
    if (teacher == null) {
      return "logout";
    }
    if (id == null) {
      return "failure";
    }
    ScoreForTeacher scoreForTeacher = scoreService.getById(id);
    if (scoreForTeacher!=null) {
      scoreForTeacher.setRemarks(remarks);
      if (scoreService.updateScoreForTeacher(scoreForTeacher)) {
        return "ok";
      }
    }
    return "failure";
  }
}
