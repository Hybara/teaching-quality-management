package cn.edu.jsu.rjxy.controller.teacher;

import cn.edu.jsu.rjxy.entity.vo.Teacher;
import cn.edu.jsu.rjxy.service.TeacherService;
import cn.edu.jsu.rjxy.util.HeaderUploadUtil;
import cn.edu.jsu.rjxy.util.MD5Util;
import java.io.File;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class TeacherController {

  @Autowired
  private TeacherService teacherService;

  @Value("${img.url}")
  public String basePath;

  private static final String USER_TYPE = "teacher";

  @RequestMapping("/teacher/login/{token}")
  public String login(@PathVariable String token, HttpSession session, Model model) {
    Teacher teacher = (Teacher) session.getAttribute(token);
    if (teacher == null) {
      return "redirect:/logout/" + token;
    }
    return "redirect:/teacher/myScores/" + token;
  }

  @RequestMapping("/teacher/goPassword/{token}")
  public String goPassword(@PathVariable String token, HttpSession session) {
    Teacher teacher = (Teacher) session.getAttribute(token);
    if (teacher == null) {
      return "forward:/logout/" + token;
    } else {
      return "/teacher/changePassword";
    }
  }

  @RequestMapping("/teacher/changePassword")
  @ResponseBody
  public String changePassword(String token, String password, HttpSession session) {
    Teacher teacher = (Teacher) session.getAttribute(token);
    teacher = teacherService.getById(teacher.getId());
    if (teacher == null) {
      return "logout";
    }
    if (password == null || "".equals(password)) {
      return "error";
    }
    teacher.setPassword(password);
    if (teacherService.updateTeacher(teacher)) {
      session.setAttribute(token, teacher);
      return "ok";
    } else {
      return "error";
    }
  }

  @RequestMapping("/teacher/goHeader/{token}")
  public String goHeader(@PathVariable String token, HttpSession session, Model model) {
    Teacher teacher = (Teacher) session.getAttribute(token);
    model.addAttribute("message", "");
    if (teacher != null) {
      return "/teacher/changeHeader";
    } else {
      return "forward:/logout/" + token;
    }
  }

  //https://my.oschina.net/qjedu/blog/1550704
  @RequestMapping("/teacher/changeHeader/{token}")
  public String changeHeader(@RequestParam(value = "file") MultipartFile file,
      @PathVariable String token, HttpSession session, Model model) {
    Teacher teacher = (Teacher) session.getAttribute(token);
    teacher = teacherService.getById(teacher.getId());
    if (teacher == null) {
      return "forward:/logout/" + token;
    }
    if (file.isEmpty()) {
      model.addAttribute("message", "文件为空");
      return "/teacher/changeHeader";
    }
    // 设置文件名
    String fileName = MD5Util.getMD5(teacher.getNumber().toString());
    // 获取文件的后缀名
    String suffixName = file.getOriginalFilename()
        .substring(file.getOriginalFilename().lastIndexOf("."));
    fileName += suffixName;
    if (HeaderUploadUtil.uploadHeader(file, basePath, fileName, USER_TYPE)) {
      teacher.setHeader(File.separator + "teacher" + File.separator + fileName);
      if (teacherService.updateTeacher(teacher)) {
        session.setAttribute(token, teacher);
        model.addAttribute("message", "上传成功");
      } else {
        model.addAttribute("message", "上传失败");
      }
    } else {
      model.addAttribute("message", "上传失败");
    }
    return "/teacher/changeHeader";
  }

  @RequestMapping("/teacher/goInfo/{token}")
  public String goInfo(@PathVariable String token, HttpSession session, Model model) {
    Teacher teacher = (Teacher) session.getAttribute(token);
    model.addAttribute("message", "");
    if (teacher != null) {
      session.setAttribute(token, teacherService.getById(teacher.getId()));
      return "/teacher/info";
    } else {
      return "forward:/logout/" + token;
    }
  }


  @RequestMapping("/teacher/changeInfo")
  @ResponseBody
  public String changeInfo(String token, String email, String phone, String qq, String wechat,
      HttpSession session) {
    Teacher teacher = (Teacher) session.getAttribute(token);
    teacher = teacherService.getById(teacher.getId());
    if (teacher == null) {
      return "logout";
    }
    teacher.setEmail("".equals(email) ? null : email);
    teacher.setPhone("".equals(phone) ? null : phone);
    teacher.setQq("".equals(qq) ? null : qq);
    teacher.setWechat("".equals(wechat) ? null : wechat);
    if (teacherService.updateTeacher(teacher)) {
      session.setAttribute(token, teacher);
      return "ok";
    } else {
      return "error";
    }
  }
}
