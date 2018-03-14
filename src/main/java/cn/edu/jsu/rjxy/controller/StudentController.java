package cn.edu.jsu.rjxy.controller;

import cn.edu.jsu.rjxy.entity.dto.ScoreDTO;
import cn.edu.jsu.rjxy.entity.vo.Student;
import cn.edu.jsu.rjxy.service.ScoreService;
import cn.edu.jsu.rjxy.service.ScoreTypeService;
import java.util.HashMap;
import java.util.List;
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

  private int NO_DATA = 0;
  private int SCORES_PAGE_SIZE = 8;
  private int INDEX_PAGE_NUMBER = 1;
  private String ALL_SCORE_TYPE = "all";
  private String NO_SEARCH = null;

  @RequestMapping("/login/{token}")
  public String login(@PathVariable String token, HttpSession session, Model model) {
    Student student = (Student) session.getAttribute(token);
//    System.out.println(student);
    model.addAttribute("scoreTypes", scoreTypeService.getAll());
    model.addAttribute("token", token);
    int scoreCount = scoreService.getScoresCountInCurrentTerm(ALL_SCORE_TYPE, student.getId(), NO_SEARCH);
//    System.out.println(scoreCount);
    model.addAttribute("scoreCount",
        scoreCount == NO_DATA ? NO_DATA : scoreCount/SCORES_PAGE_SIZE+1);
    return "/student/scores";
  }

  @RequestMapping("/getScores/{type}/{token}")
  @ResponseBody
  public Map<String, Object> getScores(@PathVariable String type, @PathVariable String token,
      HttpSession session, Integer page, String search) {
    Student student = (Student) session.getAttribute(token);
    if (page == null) {
      page = INDEX_PAGE_NUMBER;
    }
    List<ScoreDTO> scores = scoreService
        .getScoresInCurrentTerm(type, student.getId(), page, SCORES_PAGE_SIZE, search);
    int scoreCount = scoreService.getScoresCountInCurrentTerm(type, student.getId(), search);
    Map<String, Object> scoresMap = new HashMap<>(2);
//    System.out.println("scores:\n"+scores);
    scoresMap.put("scores", scores);
    scoresMap.put("count", scoreCount == NO_DATA ? NO_DATA : scoreCount/SCORES_PAGE_SIZE+1);
    return scoresMap;
  }
}
