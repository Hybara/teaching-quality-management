package cn.edu.jsu.rjxy.controller.register;

import cn.edu.jsu.rjxy.entity.vo.Register;
import cn.edu.jsu.rjxy.service.ReportService;
import cn.edu.jsu.rjxy.util.JSONBaseUtil;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("RegisterReportController")
public class ReportController {

  @Autowired
  ReportService reportService;

  private static final int PAGE_SIZE = 15;

  @RequestMapping("/register/goReport/{token}")
  public String goReport(@PathVariable String token, HttpSession session, Model model) {
    Register register = (Register) session.getAttribute(token);
    if (register == null) {
      return "redirect:/logout/" + token;
    }
    model.addAttribute("token", token);
    return "/register/report";
  }

  @RequestMapping("/register/getReport")
  @ResponseBody
  public Map<String, Object> getReports(int page, String token, HttpSession session) {
    Register register = (Register) session.getAttribute(token);
    if (register == null) {
      return JSONBaseUtil.structuralResponseMap(null, -1);
    }
    int count = reportService.getNoHandlerReportCount();
    return JSONBaseUtil.structuralResponseMap(reportService.getNoHandlerReportPage(page, PAGE_SIZE),
        Math.ceil( ((double)count)/PAGE_SIZE ));
  }

  @RequestMapping("/register/report/notAllowed")
  @ResponseBody
  public String notAllowed(long reportId, String token, HttpSession session) {
    Register register = (Register) session.getAttribute(token);
    if (register == null) {
      return "logout";
    }
    if (reportService.notAllowed(reportId)) {
      return "ok";
    } else {
      return "failure";
    }
  }

  @RequestMapping("/register/report/allow")
  @ResponseBody
  public String allow(long reportId, String token, HttpSession session) {
    Register register = (Register) session.getAttribute(token);
    if (register == null) {
      return "logout";
    }
    if (reportService.allow(reportId, register.getId())) {
      return "ok";
    } else {
      return "failure";
    }
  }
}
