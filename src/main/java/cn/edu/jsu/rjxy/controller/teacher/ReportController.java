package cn.edu.jsu.rjxy.controller.teacher;

import cn.edu.jsu.rjxy.entity.vo.Teacher;
import cn.edu.jsu.rjxy.service.ReportService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("TeacherReportController")
public class ReportController {

  @Autowired
  ReportService reportService;

  @RequestMapping("/teacher/report/{type}/{id}/{token}")
  public String goReport(@PathVariable String type,
      @PathVariable long id,
      @PathVariable String token,
      HttpSession session,
      Model model) {
    Teacher teacher = (Teacher) session.getAttribute(token);
    if (teacher == null) {
      return "redirect:/logout/"+token;
    }
    model.addAttribute("token", token);
    model.addAttribute("type", type);
    model.addAttribute("id", id);
    return "/teacher/report";
  }

  @RequestMapping("/teacher/addReport")
  @ResponseBody
  public String addReport(String type, long id, String token, String reason, HttpSession session) {
    Teacher teacher = (Teacher) session.getAttribute(token);
    if (teacher == null) {
      return "logout";
    }
    if (reportService.addReport(id, type, teacher.getId(), reason)) {
      return "ok";
    }
    return "failure";
  }
}
