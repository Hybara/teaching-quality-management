package cn.edu.jsu.rjxy.controller.register;

import cn.edu.jsu.rjxy.entity.vo.Register;
import cn.edu.jsu.rjxy.entity.vo.Student;
import cn.edu.jsu.rjxy.service.EvaluateService;
import cn.edu.jsu.rjxy.service.ScoreService;
import cn.edu.jsu.rjxy.service.TeacherService;
import cn.edu.jsu.rjxy.util.JSONBaseUtil;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("RegisterEvaluateController")
public class EvaluateController {

  private static final int NO_DATA = 0;
  private static final int DEFAULT_PAGE = 1;
  private static final int EVALUATE_PAGE_SIZE = 8;
  private static final String DEFAULT_TYPE = "all";

  private static final String ADD_EVALUATE_TYPE = "register";

  @Autowired
  private EvaluateService evaluateService;
  @Autowired
  private TeacherService teacherService;
  @Autowired
  private ScoreService scoreService;

  @RequestMapping("/register/goEvaluate/{majorId}/{teacherId}/{scoreId}/{token}")
  public String goEvaluate(@PathVariable Long majorId, @PathVariable Long teacherId,
      @PathVariable Long scoreId, @PathVariable String token, HttpSession session, Model model) {
    Register register = (Register) session.getAttribute(token);
    if (register == null) {
      return "redirect:/logout/" + token;
    }
    if (majorId == null) {
      return "redirect:/register/login/" + token;
    }
    if (teacherId == null) {
      return "redirect:/register/teacherList/" + token;
    }
    if (scoreId == null) {
      return "/register/goScores/" + majorId + "/" + teacherId + "/" + token;
    }
    model.addAttribute("token", token);
    model.addAttribute("teacher", teacherService.getById(teacherId));
    model.addAttribute("score", scoreService.getScoreByScoreForTeacherId(scoreId));
    model.addAttribute("timeLine",
        evaluateService.getEvaluateTimeLine(scoreId, register.getId(), ADD_EVALUATE_TYPE));
    return "register/teacher/evaluate";
  }

  @RequestMapping("/register/getScoreEvaluates")
  @ResponseBody
  public Map<String, Object> getScoreEvaluates(HttpSession session, String token,
      long scoreForClassId, String type, Integer page) {
    Register register = (Register) session.getAttribute(token);
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

  @RequestMapping("/register/evaluate")
  @ResponseBody
  public String evaluate(String token, long id, HttpSession session,
      String title, String text, String result, Boolean flag) {
    Register register = (Register) session.getAttribute(token);
    if (register == null) {
      return "logout";
    }
    if (evaluateService
        .addScoreEvaluate(id, title, text, result, register.getId(), ADD_EVALUATE_TYPE, flag)) {
      return "ok";
    } else {
      return "failure";
    }
  }
}
