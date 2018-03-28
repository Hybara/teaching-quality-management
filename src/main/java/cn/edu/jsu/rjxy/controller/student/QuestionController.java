package cn.edu.jsu.rjxy.controller.student;

import cn.edu.jsu.rjxy.entity.vo.Student;
import cn.edu.jsu.rjxy.mappers.ScoreForTeacherMapper;
import cn.edu.jsu.rjxy.service.EvaluateService;
import cn.edu.jsu.rjxy.service.QuestionService;
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
public class QuestionController {

  private static final int NO_DATA = 0;
  private static final int DEFAULT_PAGE = 1;
  private static final int QUESTION_PAGE_SIZE = 8;
  private static final int FORQUESTION_PAGE_SIZE = 7;
  private static final String DEFAULT_TYPE = "all";

  private static final String ADD_QUESTION_TYPE = "student";

  @Autowired
  private QuestionService questionService;
  @Autowired
  private ScoreService scoreService;

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
    int scoreQuestionCount = questionService.getScoreQuestionsCount(scoreForClassId, type);
    return JSONBaseUtil.structuralResponseMap(
        questionService.getScoreQuestions(scoreForClassId, type, page, QUESTION_PAGE_SIZE),
        scoreQuestionCount == NO_DATA ? NO_DATA
            : Math.ceil((double) scoreQuestionCount / QUESTION_PAGE_SIZE));
  }

  @RequestMapping("/student/question")
  @ResponseBody
  public String question(String token, long id, HttpSession session,
      String title, String text, String result, Boolean flag) {
    Student student = (Student) session.getAttribute(token);
    if (student == null) {
      return "logout";
    }
    try {
      if (questionService
          .addScoreQuestion(id, title, text, result, student.getId(), ADD_QUESTION_TYPE, flag)) {
        return "ok";
      } else {
        return "failure";
      }
    } catch (Exception e) {
      return "failure";
    }
  }

  @RequestMapping("/student/goQuestion/{scoreId}/{questionId}/{token}")
  public String goQuestion(@PathVariable long scoreId,
      @PathVariable long questionId, @PathVariable String token,
      Model model, HttpSession session) {
    Student student = (Student) session.getAttribute(token);
    if (student == null) {
      return "redirect:/logout/" + token;
    }
    model.addAttribute("flag", questionService.checkCreater(questionId, student.getId()));
    model.addAttribute("scoreInfo", scoreService.getScoreByScoreForTeacherId(scoreId));
    model.addAttribute("question", questionService.getQuestionReaderById(questionId));
    model.addAttribute("token", token);
    return "/student/question";
  }

  @RequestMapping("/student/getQuestionLists")
  @ResponseBody
  public Map<String, Object> getQuestionLists(String token, long questionId, Integer page,
      HttpSession session) {
    Student student = (Student) session.getAttribute(token);

    if (student == null) {
      return JSONBaseUtil.structuralResponseMap("", 0);
    }

    int questionChildsCount = questionService.getQuestionChildCount(questionId);
    return JSONBaseUtil.structuralResponseMap(
        questionService.getQuestionChildLists(questionId, page, FORQUESTION_PAGE_SIZE),
        questionChildsCount == NO_DATA ? NO_DATA
            : Math.ceil((double) questionChildsCount / FORQUESTION_PAGE_SIZE));
  }

  @RequestMapping("/student/resultQuestion")
  @ResponseBody
  public String question(String token, long questionId, HttpSession session, String text,
      String result, Boolean flag) {
    Student student = (Student) session.getAttribute(token);
    if (student == null) {
      return "logout";
    }
    try {
      if (questionService
          .addQuestionChild(questionId, text, result, flag, student.getId(), ADD_QUESTION_TYPE)) {
        return "ok";
      } else {
        return "failure";
      }
    } catch (Exception e) {
      return "failure";
    }
  }
}
