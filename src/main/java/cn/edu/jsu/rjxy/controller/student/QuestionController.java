package cn.edu.jsu.rjxy.controller.student;

import cn.edu.jsu.rjxy.entity.vo.Student;
import cn.edu.jsu.rjxy.service.EvaluateService;
import cn.edu.jsu.rjxy.service.QuestionService;
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
public class QuestionController {

  private static final int NO_DATA = 0;
  private static final int DEFAULT_PAGE = 1;
  private static final int QUESTION_PAGE_SIZE = 8;
  private static final String DEFAULT_TYPE = "all";

  private static final String ADD_QUESTION_TYPE = "student";

  @Autowired
  private QuestionService questionService;

  @RequestMapping("/student/getScoreQuestions")
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
    int scoreEvaluateCount = questionService.getScoreQuestionsCount(scoreForClassId, type);
    return JSONBaseUtil.structuralResponseMap(
        questionService.getScoreQuestions(scoreForClassId, type, page, QUESTION_PAGE_SIZE),
        scoreEvaluateCount == NO_DATA ? NO_DATA
            : Math.ceil((double) scoreEvaluateCount / QUESTION_PAGE_SIZE));
  }

  @RequestMapping("/student/question")
  @ResponseBody
  public String question(String token, long id, HttpSession session,
      String title, String text, String result, Boolean flag) {
    Student student = (Student) session.getAttribute(token);
    if (student == null) {
      return "logout";
    }
    if (questionService
        .addScoreQuestion(id, title, text, result, student.getId(), ADD_QUESTION_TYPE, flag)) {
      return "ok";
    } else {
      return "failure";
    }
  }

  @RequestMapping("/student/goQuestion/{id}/{token}")
  public String goQuestion(@PathVariable String token, @PathVariable long id,
      Model model, HttpSession session) {
    Student student = (Student) session.getAttribute(token);
    if (student == null) {
      return "/logout/" + token;
    }
    model.addAttribute("question", questionService.getQuestionById(id));
    model.addAttribute("token", token);
    return "/student/question";
  }
}
