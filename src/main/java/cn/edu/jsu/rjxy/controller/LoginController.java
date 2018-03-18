package cn.edu.jsu.rjxy.controller;

import cn.edu.jsu.rjxy.service.AdminService;
import cn.edu.jsu.rjxy.service.LoginService;
import cn.edu.jsu.rjxy.service.RegisterService;
import cn.edu.jsu.rjxy.service.StudentService;
import cn.edu.jsu.rjxy.service.TeacherService;
import java.util.UUID;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

  private int SESSION_LIVE_TIME = 3000;

  @Autowired
  private LoginService loginService;
  @Autowired
  private AdminService adminService;
  @Autowired
  private RegisterService registerService;
  @Autowired
  private TeacherService teacherService;
  @Autowired
  private StudentService studentService;

  @RequestMapping("/login")
  public String login(String account, String password, String type, Model model, HttpSession session) {
    String path = "/login";
    if (type!=null && loginService.login(account, password, type)) {
      String token = createToken(account, password, type, session);
      model.addAttribute("token", token);
      path = "forward:/"+type+path+"/"+token;
    } else if (type!=null) {
      model.addAttribute("message", "账号或密码错误");
    }
    return path;
  }

  public String createToken(String account, String password, String type, HttpSession session) {
    String sessionKey = UUID.randomUUID().toString();
    if ("student".equals(type)) {
      session.setAttribute(sessionKey, studentService.getLoginer(account, password));
    } else if ("teacher".equals(type)) {
      session.setAttribute(sessionKey, teacherService.getLoginer(account, password));
    } else if ("register".equals(type)) {
      session.setAttribute(sessionKey, registerService.getLoginer(account, password));
    } else if ("admin".equals(type)) {
      session.setAttribute(sessionKey, adminService.getLoginer(account, password));
    }
    if (session.getMaxInactiveInterval()!=SESSION_LIVE_TIME) {
      session.setMaxInactiveInterval(SESSION_LIVE_TIME);
    }
    return sessionKey;
  }

  @RequestMapping("/logout/{token}")
  public String logout(@PathVariable String token, HttpSession session) {
    session.removeAttribute("token");
    return "/login";
  }

}
