package cn.edu.jsu.rjxy.controller.register;

import cn.edu.jsu.rjxy.entity.vo.Register;
import cn.edu.jsu.rjxy.service.QuestionnaireService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("RegisterQuestionnaireController")
public class QuestionnaireController {

  @Autowired
  private QuestionnaireService questionnaireService;

  @RequestMapping("/register/goQuestionnaire/{token}")
  public String goQuestionnaire(@PathVariable String token, HttpSession session, Model model) {
    Register register = (Register) session.getAttribute(token);
    if (register == null) {
      return "redirect:/logout/" + token;
    }
    model.addAttribute("token", token);
    model.addAttribute("typeList", questionnaireService.getAllQuestionType());
    return "/register/questionnaire";
  }

  @RequestMapping("/register/addQuestionnaireQuestionType")
  @ResponseBody
  public String addQuestionnaireQuestionType(String token, String data, HttpSession session) {
    Register register = (Register) session.getAttribute(token);
    if (register == null) {
      return "logout";
    }
    if (data == null || "".equals(data)) {
      return "nodata";
    }
    if (questionnaireService.addQuestionType(data)) {
      return "ok";
    }
    return "failure";
  }
}
