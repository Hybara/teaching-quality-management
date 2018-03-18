package cn.edu.jsu.rjxy.controller.student;

import cn.edu.jsu.rjxy.entity.vo.Student;
import cn.edu.jsu.rjxy.service.ScoreService;
import cn.edu.jsu.rjxy.util.JSONBaseUtil;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ScoresController {

  @Autowired
  private ScoreService scoreService;

  private int NO_DATA = 0;
  private int SCORES_PAGE_SIZE = 8;
  private int INDEX_PAGE_NUMBER = 1;

  @RequestMapping("/student/getScores/{type}/{token}")
  @ResponseBody
  public Map<String, Object> getScores(@PathVariable String type, @PathVariable String token,
      HttpSession session, Integer page, String search) {
    Student student = (Student) session.getAttribute(token);
    if (page == null) {
      page = INDEX_PAGE_NUMBER;
    }
    int scoreCount = scoreService.getScoresCountInCurrentTerm(type, student.getId(), search);
    return JSONBaseUtil.structuralResponseMap(
        scoreService.getScoresInCurrentTerm(type, student.getId(), page, SCORES_PAGE_SIZE, search),
        scoreCount == NO_DATA ? NO_DATA : Math.ceil((double) scoreCount / SCORES_PAGE_SIZE));
  }

  @RequestMapping("/student/getScore/{id}/{token}")
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
}
