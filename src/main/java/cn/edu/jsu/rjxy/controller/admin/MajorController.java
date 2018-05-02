package cn.edu.jsu.rjxy.controller.admin;

import cn.edu.jsu.rjxy.entity.vo.Admin;
import cn.edu.jsu.rjxy.entity.vo.Major;
import cn.edu.jsu.rjxy.service.MajorService;
import cn.edu.jsu.rjxy.util.JSONBaseUtil;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("AdminMajorController")
public class MajorController {

  @Autowired
  private MajorService majorService;

  private static final String NO_SEARCH = null;
  private static final Integer DEFAULT_PAGE_SIZE = 12;
  private static final Integer INDEX_PAGE = 1;

  @RequestMapping("/admin/getMajor")
  @ResponseBody
  public Map<String, Object> getMajorList(Integer page, String search) {
    if (page == null) {
      page = INDEX_PAGE;
    }
    int majorCount = majorService.getMajorCount(search);
    return JSONBaseUtil
        .structuralResponseMap(majorService.getMajorList(page, DEFAULT_PAGE_SIZE, search),
            Math.ceil((double) majorCount / DEFAULT_PAGE_SIZE));
  }


  @RequestMapping("/admin/deleteMajor")
  @ResponseBody
  public String delMajor(long id, String token, HttpSession session) {
    Admin admin = (Admin) session.getAttribute(token);
    if (admin == null) {
      return "logout";
    }
    if (majorService.deleteMajor(id)) {
      return "ok";
    } else {
      return "failure";
    }
  }

  @RequestMapping("/admin/goAddMajor/{token}")
  public String goAddMajor(@PathVariable String token, HttpSession session, Model model) {
    Admin admin = (Admin) session.getAttribute(token);
    if (admin == null) {
      return "redirect:/logout/"+token;
    }
    model.addAttribute("token", token);
    model.addAttribute("majorId", 0);
    return "/admin/changeMajor";
  }

  @RequestMapping("/admin/goUpdateMajor/{id}/{token}")
  public String goUpdateMajor(@PathVariable long id, @PathVariable String token,
      HttpSession session, Model model) {
    Admin admin = (Admin) session.getAttribute(token);
    if (admin == null) {
      return "redirect:/logout/"+token;
    }
    Major major = majorService.getById(id);
    model.addAttribute("token", token);
    model.addAttribute("major", major);
    model.addAttribute("majorId", major.getId());
    return "/admin/changeMajor";
  }

  @RequestMapping("/admin/changeMajor/{id}/{token}")
  @ResponseBody
  public String updateMajor(Long id, String number, String name, Integer schooling,
      String token, HttpSession session) {
    Admin admin = (Admin) session.getAttribute(token);
    if (admin == null) {
      return "logout";
    }
    if (id==0) {
      if (majorService.insertMajor(new Major(number, name, schooling))) {
        return "ok";
      }
    } else if (id>0){
      if (majorService.updateMajor(new Major(id, number, name, schooling))) {
        return "ok";
      }
    }
    return "failure";
  }


}
