package cn.edu.jsu.rjxy.controller.student;

import cn.edu.jsu.rjxy.entity.vo.QuestionnaireForTeacher;
import cn.edu.jsu.rjxy.entity.vo.Student;
import cn.edu.jsu.rjxy.service.FillInQuestionnaireService;
import cn.edu.jsu.rjxy.service.QuestionnaireService;
import cn.edu.jsu.rjxy.service.ScoreService;
import java.util.Arrays;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("StudentQuestionnaireController")
public class QuestionnaireController {

  @Autowired
  QuestionnaireService questionnaireService;
  @Autowired
  ScoreService scoreService;
  @Autowired
  FillInQuestionnaireService fillInQuestionnaireService;

  @RequestMapping("/student/goQuestionnaire/{id}/{token}")
  public String goQuestionnaire(@PathVariable long id,
      @PathVariable String token,
      HttpSession session, Model model) {
    Student student = (Student) session.getAttribute(token);
    if (student == null) {
      return "redirect:/logout/" + token;
    }
    model.addAttribute("id", id);
    model.addAttribute("token", token);
    QuestionnaireForTeacher questionnaire = questionnaireService.teacherQuestionnaireIsExist(id);
    System.out.println(id);
    System.out.println(questionnaire);
    model.addAttribute("template", questionnaire);
    model.addAttribute("types", questionnaireService.getQuestionnaireQuestionType(questionnaire.getId()));
    model.addAttribute("scoreInfo", scoreService.getScoreByScoreForTeacherId(id));
    model.addAttribute("questions", questionnaireService.getQuestionnaireQuestions(questionnaire.getId()));
    return "/student/questionnaire";
  }

  @RequestMapping("/student/fillInQuestionnaire")
  @ResponseBody
  public String fillInQuestionnaire(long scoreId,
      long questionnaireId,
      @RequestParam(value = "results[]") Double[] results,
      @RequestParam(value = "ids[]") Long[] ids,
      String token,
      HttpSession session, Model model) {
    Student student = (Student) session.getAttribute(token);
    if (student == null) {
      return "logout";
    }
    double result = questionnaireService.getQuestionnaireResult(questionnaireId, Arrays.asList(ids), Arrays.asList(results));
    if (fillInQuestionnaireService.fillInQuestionnaire(questionnaireId, result, student.getId(), "student", scoreId)) {
      return Double.toString(result);
    }
    return "failure";
  }

}
