package cn.edu.jsu.rjxy.controller.teacher;

import cn.edu.jsu.rjxy.entity.vo.Student;
import cn.edu.jsu.rjxy.entity.vo.Teacher;
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

@Controller("teacherQuestionController")
public class QuestionController {

  private static final int NO_DATA = 0;
  private static final int DEFAULT_PAGE = 1;
  private static final int QUESTION_PAGE_SIZE = 8;
  private static final int FORQUESTION_PAGE_SIZE = 7;
  private static final String DEFAULT_TYPE = "all";

  private static final String ADD_QUESTION_TYPE = "teacher";

  @Autowired
  private QuestionService questionService;
  @Autowired
  private ScoreService scoreService;

  @RequestMapping("/teacher/goMyQuestions/{id}/{token}")
  public String goMyQuestions(@PathVariable String token, @PathVariable Long id, HttpSession session,
      Model model) {
    Teacher teacher = (Teacher) session.getAttribute(token);
    if (teacher == null) {
      return "forward:/logout/" + token;
    } else if (id == null) {
      return "/teacher/getScores/all/" + token;
    } else {
      model.addAttribute("scoreInfo", scoreService.getScoreByScoreForTeacherId(id));
      return "/teacher/myQuestions";
    }
  }

  @RequestMapping("/teacher/getScoreQuestions")
  @ResponseBody
  public Map<String, Object> getScoreEvaluates(HttpSession session, String token,
      long scoreForClassId, String type, Integer page) {
    Teacher teacher = (Teacher) session.getAttribute(token);
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


  @RequestMapping("/teacher/goMyQuestion/{scoreId}/{questionId}/{token}")
  public String goMyQuestion(@PathVariable long scoreId,
      @PathVariable long questionId, @PathVariable String token,
      Model model, HttpSession session) {
    Teacher teacher = (Teacher) session.getAttribute(token);
    if (teacher == null) {
      return "redirect:/logout/" + token;
    }
    model.addAttribute("scoreInfo", scoreService.getScoreByScoreForTeacherId(scoreId));
    model.addAttribute("question", questionService.getQuestionReaderById(questionId));
    model.addAttribute("token", token);
      return "/teacher/myQuestion";
  }


  @RequestMapping("/teacher/getQuestionLists")
  @ResponseBody
  public Map<String, Object> getQuestionLists(String token, long questionId, Integer page,
      HttpSession session) {
    Teacher teacher = (Teacher) session.getAttribute(token);

    if (teacher == null) {
      return JSONBaseUtil.structuralResponseMap("", 0);
    }

    int questionChildsCount = questionService.getQuestionChildCount(questionId);
    return JSONBaseUtil.structuralResponseMap(
        questionService.getQuestionChildLists(questionId, page, FORQUESTION_PAGE_SIZE),
        questionChildsCount == NO_DATA ? NO_DATA
            : Math.ceil((double) questionChildsCount / FORQUESTION_PAGE_SIZE));
  }


  @RequestMapping("/teacher/resultQuestion")
  @ResponseBody
  public String question(String token, long questionId, HttpSession session, String text,
      Boolean flag) {
    Teacher teacher = (Teacher) session.getAttribute(token);
    if (teacher == null) {
      return "logout";
    }
    try {
      if (questionService
          .addQuestionChild(questionId, text, null, flag, teacher.getId(), ADD_QUESTION_TYPE)) {
        return "ok";
      } else {
        return "failure";
      }
    } catch (Exception e) {
      return "failure";
    }
  }


  @RequestMapping("/teacher/goQuestions/{teacherId}/{id}/{token}")
  public String goQuestions(@PathVariable String token, @PathVariable Long teacherId, @PathVariable Long id, HttpSession session,
      Model model) {
    Teacher teacher = (Teacher) session.getAttribute(token);
    if (teacher == null) {
      return "forward:/logout/" + token;
    } else if (id == null || teacherId == null) {
      return "/teacher/getScores/all/" + token;
    } else {
      model.addAttribute("teacher", teacherId);
      model.addAttribute("scoreInfo", scoreService.getScoreByScoreForTeacherId(id));
      return "/teacher/other/questions";
    }
  }
  @RequestMapping("/teacher/goQuestion/{teacherId}/{scoreId}/{questionId}/{token}")
  public String goQuestion(@PathVariable Long teacherId, @PathVariable long scoreId,
      @PathVariable long questionId, @PathVariable String token,
      Model model, HttpSession session) {
    Teacher teacher = (Teacher) session.getAttribute(token);
    if (teacher == null) {
      return "redirect:/logout/" + token;
    }
    if (teacherId == null) {
      return "/teacher/getScores/all/" + token;
    }
    model.addAttribute("teacher", teacherId);
    model.addAttribute("scoreInfo", scoreService.getScoreByScoreForTeacherId(scoreId));
    model.addAttribute("question", questionService.getQuestionReaderById(questionId));
    model.addAttribute("token", token);
    return "/teacher/other/question";
  }
}
