package cn.edu.jsu.rjxy.controller.student;

import cn.edu.jsu.rjxy.entity.vo.Student;
import cn.edu.jsu.rjxy.service.ScoreService;
import cn.edu.jsu.rjxy.util.JSONBaseUtil;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("studentScoresController")
public class ScoresController {

  @Autowired
  private ScoreService scoreService;

  private static final int NO_DATA = 0;
  private static final int SCORES_PAGE_SIZE = 8;
  private static final int INDEX_PAGE_NUMBER = 1;

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

}
