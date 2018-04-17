package cn.edu.jsu.rjxy.controller.register;

import cn.edu.jsu.rjxy.entity.dto.QuestionnaireBankItemDTO;
import cn.edu.jsu.rjxy.entity.vo.QuestionnaireBank;
import cn.edu.jsu.rjxy.entity.vo.Register;
import cn.edu.jsu.rjxy.service.QuestionnaireService;
import cn.edu.jsu.rjxy.util.JSONBaseUtil;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("RegisterQuestionnaireController")
public class QuestionnaireController {

  @Autowired
  private QuestionnaireService questionnaireService;

  private static final int QUESTION_BANK_PAGE_SIZE = 20;
  private static final int QUESTION_TEMPLATE_PAGE_SIZE = 24;

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
        Math.ceil(((double) count) / QUESTION_BANK_PAGE_SIZE));
  }

  @RequestMapping("/register/questionnaire/goAddQuestion/{type}/{questionId}/{token}")
  public String goAddQuestion(@PathVariable Long type,
      @PathVariable Long questionId,
      @PathVariable String token,
      HttpSession session, Model model) {
    Register register = (Register) session.getAttribute(token);
    if (register == null) {
      return "redirect:/logout/" + token;
    }
    if (type == null) {
      return "redirect:/register/goQuestionnaire/" + token;
    }
    if (questionId != null && questionId != 0) {
      model.addAttribute("question", questionnaireService.getQuestionById(questionId));
    }
    model.addAttribute("token", token);
    model.addAttribute("type", questionnaireService.getQuestionTypeById(type));
    return "/register/questionAdd";
  }

  @RequestMapping("/register/questionnaire/addQuestion")
  @ResponseBody
  public String addQuestion(Long type,
      String token,
      QuestionnaireBankItemDTO bank,
      HttpSession session) {
    Register register = (Register) session.getAttribute(token);
    if (register == null) {
      return "logout";
    }
    bank.setTypeId(type);
    boolean flag = false;
    if (bank.getId() == 0) {
      flag = questionnaireService.addQuestionBank(bank);
    } else {
      flag = questionnaireService.updateQuestionBank(bank);
    }
    if (flag) {
      return "ok";
    } else {
      return "failure";
    }
  }

  @RequestMapping("/register/questionnaire/delQuestion")
  @ResponseBody
  public String addQuestion(Long type,
      String token,
      long id,
      HttpSession session) {
    Register register = (Register) session.getAttribute(token);
    if (register == null) {
      return "logout";
    }
    if (questionnaireService.deleteQuestionBank(id)) {
      return "ok";
    } else {
      return "failure";
    }
  }

  @RequestMapping("/register/template/goTemplate/{token}")
  public String goTemplate(@PathVariable String token, HttpSession session, Model model) {
    Register register = (Register) session.getAttribute(token);
    if (register == null) {
      return "redirect:/logout/" + token;
    }
    model.addAttribute("token", token);
    return "/register/questionnaireTemplate";
  }

  @RequestMapping("/register/template/getTemplate")
  @ResponseBody
  public Map<String, Object> getTemplate(String token, Integer page, String search,
      HttpSession session) {
    Register register = (Register) session.getAttribute(token);
    if (register == null) {
      return JSONBaseUtil.structuralResponseMap("", 0);
    }
    int count = questionnaireService.getTemplateCount(search);
    return JSONBaseUtil.structuralResponseMap(
        questionnaireService.getTemplatePage(page, QUESTION_TEMPLATE_PAGE_SIZE, search),
        Math.ceil(((double) count) / QUESTION_TEMPLATE_PAGE_SIZE));
  }

  @RequestMapping("/register/template/goQuestionnaire/{templateId}/{token}")
  public String goTemplateQuestionnaire(@PathVariable Long templateId,
      @PathVariable String token,
      HttpSession session,
      Model model) {
    Register register = (Register) session.getAttribute(token);
    if (register == null) {
      return "redirect:/logout/" + token;
    }
    model.addAttribute("token", token);
    model.addAttribute("template", questionnaireService.getTemplateById(templateId));
    model.addAttribute("questions", questionnaireService.getTemplate(templateId));
    model.addAttribute("types", questionnaireService.getTemplateQuestionType(templateId));
    return "/register/template";
  }

  @RequestMapping("/register/template/changeTemplateName")
  @ResponseBody
  public String changeTemplateName(String name, long templateId,
      String token,
      HttpSession session,
      Model model) {
    Register register = (Register) session.getAttribute(token);
    if (register == null) {
      return "logout";
    }
    if (name == null || "".equals(name)) {
      return "nodata";
    }
    if (questionnaireService.updateTemplateName(templateId, name, register.getId())) {
      return "ok";
    }
    return "failure";
  }

  @RequestMapping("/register/template/changeQuestionCoefficient")
  @ResponseBody
  public String changeTemplateName(@RequestParam(value = "questionIds[]") Long[] questionIds,
      @RequestParam(value = "coefficients[]") Double[] coefficients,
      String token,
      HttpSession session,
      Model model) {
    Register register = (Register) session.getAttribute(token);
    if (register == null) {
      return "logout";
    }
    List<Long> questionIdList = Arrays.asList(questionIds);
    List<Double> coefficientList = Arrays.asList(coefficients);
    if (questionnaireService.updateCoefficients(questionIdList, coefficientList)) {
      return "ok";
    }
    return "failure";
  }

  @RequestMapping("/register/template/goChangeTemplateQuestion/{templateId}/{questionId}/{token}")
  public String goChangeTemplateQuestion(@PathVariable Long templateId,
      @PathVariable Long questionId,
      @PathVariable String token,
      HttpSession session,
      Model model) {
    Register register = (Register) session.getAttribute(token);
    if (register == null) {
      return "redirect:/logout/"+token;
    }
    model.addAttribute("token", token);
    model.addAttribute("template", questionnaireService.getTemplateById(templateId));
    model.addAttribute("questionId", questionId);
    model.addAttribute("types", questionnaireService.getAllQuestionType());
    return "/register/template/changeQuestion";
  }

  @RequestMapping("/register/template/changeTemplateQuestion")
  @ResponseBody
  public String changeTemplateQuestion(Long templateId,
      Long oldQuestion,
      Long newQuestion,
      String token,
      HttpSession session) {
    Register register = (Register) session.getAttribute(token);
    if (register == null) {
      return "logout";
    }
    try {
      if (questionnaireService.changeQuestion(templateId, oldQuestion,  newQuestion, register.getId())) {
        return "ok";
      } else {
        return "failure";
      }
    } catch (Exception e) {
      return "failure";
    }
  }

  @RequestMapping("/register/template/getQuestionBank")
  @ResponseBody
  public Map<String, Object> getTemplateQuestionBank(Long type,
      Long template,
      String token,
      Integer page,
      HttpSession session) {
    Register register = (Register) session.getAttribute(token);
    if (register == null) {
      return JSONBaseUtil.structuralResponseMap("", 0);
    }
    int count = questionnaireService.getQuestionCountByType(type, template);
    return JSONBaseUtil.structuralResponseMap(
        questionnaireService.getQuestionListByType(type, page, QUESTION_BANK_PAGE_SIZE, template),
        Math.ceil(((double) count) / QUESTION_BANK_PAGE_SIZE));
  }
}
