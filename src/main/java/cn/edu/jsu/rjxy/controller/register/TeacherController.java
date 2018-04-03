package cn.edu.jsu.rjxy.controller.register;

import cn.edu.jsu.rjxy.entity.vo.Register;
import cn.edu.jsu.rjxy.entity.vo.Teacher;
import cn.edu.jsu.rjxy.service.MajorService;
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

@Controller("RegisterTeacherController")
public class TeacherController {

  @Autowired
  private TeacherService teacherService;
  @Autowired
  private MajorService majorService;

  private static final int INDEX_PAGE = 1;
  private static final int PAGE_SIZE = 12;
  private static final String NO_SEARCH = null;
  private static final Long NO_EXCLUDE_TEACHER = null;

  @RequestMapping("/register/teacherList/{majorId}/{token}")
  public String goTeacherList(@PathVariable Long majorId, @PathVariable String token, HttpSession session, Model model) {
    Register register = (Register) session.getAttribute(token);
    if (register == null) {
      return "redirect:/logout/" + token;
    }
    if (majorId == null) {
      return "redirect:/register/login/" + token;
    }
    int teacherCount = teacherService.getTeacherCountByMajor(majorId, NO_SEARCH, NO_EXCLUDE_TEACHER);
    model.addAttribute("token", token);
    model.addAttribute("major", majorService.getById(majorId));
    model.addAttribute("count", Math.ceil(((double) teacherCount)/PAGE_SIZE));
    return "/register/teacher/teachers";
  }

  @RequestMapping("register/getTeachers")
  @ResponseBody
  public Map<String, Object> getScores(String token, Long majorId, HttpSession session, Integer page, String search) {
    Register register = (Register) session.getAttribute(token);
    if (page == null) {
      page = INDEX_PAGE;
    }
    if (search == null || "".equals(search)) {
      search = NO_SEARCH;
    }
    int teacherCount = teacherService.getTeacherCountByMajor(majorId, search, NO_EXCLUDE_TEACHER);
    return JSONBaseUtil.structuralResponseMap(
        teacherService
            .getTeacherListByMajor(majorId, page, PAGE_SIZE, search, NO_EXCLUDE_TEACHER),
        Math.ceil((double) teacherCount / PAGE_SIZE));
  }
}
