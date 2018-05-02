package cn.edu.jsu.rjxy.controller.admin;

import cn.edu.jsu.rjxy.entity.vo.Admin;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {


  @RequestMapping("/admin/login/{token}")
  public String login(@PathVariable String token, HttpSession session, Model model) {
    Admin admin = (Admin) session.getAttribute(token);
    if (admin == null) {
      return "/logout/" + token;
    }
    model.addAttribute("token", token);
    return "/admin/major";
  }

}
