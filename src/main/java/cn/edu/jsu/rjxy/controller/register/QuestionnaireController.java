package cn.edu.jsu.rjxy.controller.register;

import cn.edu.jsu.rjxy.entity.vo.Register;
import cn.edu.jsu.rjxy.service.QuestionnaireService;
import cn.edu.jsu.rjxy.util.JSONBaseUtil;
import java.util.Map;
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

  private static final int QUESTION_BANK_PAGE_SIZE = 1;

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

  @RequestMapping("/register/questionnaire/question/{type}/{token}")
  public String goQuestionnaireBank(@PathVariable Long type, @PathVariable String token,
      HttpSession session, Model model) {
    Register register = (Register) session.getAttribute(token);
    if (register == null) {
      return "redirect:/logout/" + token;
    }
    if (type == null) {
      return "redirect:/register/goQuestionnaire/" + token;
    }
    model.addAttribute("token", token);
    model.addAttribute("type", questionnaireService.getQuestionTypeById(type));
    model.addAttribute("pageSize", QUESTION_BANK_PAGE_SIZE);
    return "/register/questionnaireBank";
  }

  @RequestMapping("/register/getQuestionBank")
  @ResponseBody
  public Map<String, Object> getQuestionBank(Long type, String token, Integer page, String search,
      HttpSession session) {
    Register register = (Register) session.getAttribute(token);
    if (register == null) {
      return JSONBaseUtil.structuralResponseMap("", 0);
    }
    int count = questionnaireService.getQuestionCountByType(type, search);
    return JSONBaseUtil.structuralResponseMap(
        questionnaireService.getQuestionListByType(type, page, QUESTION_BANK_PAGE_SIZE, search),
        Math.ceil(((double) count)/QUESTION_BANK_PAGE_SIZE));
  }
}
