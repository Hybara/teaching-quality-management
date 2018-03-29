package cn.edu.jsu.rjxy.controller.teacher;

import cn.edu.jsu.rjxy.entity.vo.Teacher;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TeacherController {

  @RequestMapping("/teacher/login/{token}")
  public String login(@PathVariable String token, HttpSession session, Model model) {
    Teacher teacher = (Teacher) session.getAttribute(token);
    if (teacher == null) {
      return "redirect:/logout/" + token;
    }
    return "redirect:/teacher/scores/" + token;
  }

}
