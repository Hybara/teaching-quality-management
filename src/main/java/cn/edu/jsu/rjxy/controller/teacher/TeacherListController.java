package cn.edu.jsu.rjxy.controller.teacher;

import cn.edu.jsu.rjxy.entity.vo.Teacher;
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

@Controller("teacherTeacherListController")
public class TeacherListController {

  @Autowired
  private TeacherService teacherService;

  private static final int INDEX_PAGE = 1;
  private static final int PAGE_SIZE = 12;
  private static final String NO_SEARCH = null;

  @RequestMapping("/teacher/teacherList/{token}")
  public String goTeacherList(@PathVariable String token, HttpSession session, Model model) {
    Teacher teacher = (Teacher) session.getAttribute(token);
    if (teacher == null) {
      return "redirect:/logout/" + token;
    }
    teacher = teacherService.getById(teacher.getId());
    if (teacher == null) {
      return "redirect:/logout/" + token;
    }
    session.setAttribute(token, teacher);
    int teacherCount = teacherService.getTeacherCountByMajor(teacher.getMajor().getId(), NO_SEARCH, teacher.getId());
    model.addAttribute("count", Math.ceil(((double) teacherCount)/PAGE_SIZE));
    return "/teacher/teachers";
  }

  @RequestMapping("teacher/getTeachers")
  @ResponseBody
  public Map<String, Object> getScores(String token, HttpSession session, Integer page, String search) {
    Teacher teacher = (Teacher) session.getAttribute(token);
    if (page == null) {
      page = INDEX_PAGE;
    }
    if (search == null || "".equals(search)) {
      search = NO_SEARCH;
    }
    int teacherCount = teacherService.getTeacherCountByMajor(teacher.getMajor().getId(), search, teacher.getId());
    return JSONBaseUtil.structuralResponseMap(
        teacherService
            .getTeacherListByMajor(teacher.getMajor().getId(), page, PAGE_SIZE, search, teacher.getId()),
        Math.ceil((double) teacherCount / PAGE_SIZE));
  }

}
