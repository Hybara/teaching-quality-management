package cn.edu.jsu.rjxy.controller.student;

import cn.edu.jsu.rjxy.entity.vo.Evaluate;
import cn.edu.jsu.rjxy.entity.vo.ScoreForTeacher;
import cn.edu.jsu.rjxy.entity.vo.Student;
import cn.edu.jsu.rjxy.service.EvaluateService;
import cn.edu.jsu.rjxy.util.JSONBaseUtil;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EvaluateController {

  private static final int NO_DATA = 0;
  private static final int DEFAULT_PAGE = 1;
  private static final int EVALUATE_PAGE_SIZE = 8;
  private static final String DEFAULT_TYPE = "all";

  private static final String ADD_EVALUATE_TYPE = "student";

  @Autowired
  private EvaluateService evaluateService;

  @RequestMapping("/student/getScoreEvaluates")
  @ResponseBody
  public Map<String, Object> getScoreEvaluates(HttpSession session, String token,
      long scoreForClassId, String type, Integer page) {
    Student student = (Student) session.getAttribute(token);
    if (page == null) {
      page = DEFAULT_PAGE;
    }
    if (type == null) {
      type = DEFAULT_TYPE;
    }
    int scoreEvaluateCount = evaluateService.getScoreEvaluatesCount(scoreForClassId, type);
    return JSONBaseUtil.structuralResponseMap(
        evaluateService.getScoreEvaluates(scoreForClassId, type, page, EVALUATE_PAGE_SIZE),
        scoreEvaluateCount == NO_DATA ? NO_DATA
            : Math.ceil((double) scoreEvaluateCount / EVALUATE_PAGE_SIZE));
  }

  @RequestMapping("/student/evaluate")
  @ResponseBody
  public String evaluate(String token, long id, HttpSession session,
      String title, String text, String result, Boolean flag) {
    Student student = (Student) session.getAttribute(token);
    if (student == null) {
      return "logout";
    }
    if (evaluateService.addScoreEvaluate(id, title, text, result, student.getId(), ADD_EVALUATE_TYPE, flag)) {
      return "ok";
    } else {
      return "failure";
    }
  }

}
