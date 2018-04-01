package cn.edu.jsu.rjxy.controller.register;

import cn.edu.jsu.rjxy.entity.vo.Major;
import cn.edu.jsu.rjxy.entity.vo.Register;
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

@Controller("registerMajorController")
public class MajorController {

  @Autowired
  private MajorService majorService;

  private static final String NO_SEARCH = null;
  private static final Integer DEFAULT_PAGE_SIZE = 24;
  private static final Integer INDEX_PAGE = 1;

  @RequestMapping("/register/majors/{token}")
  public String goMajor(@PathVariable String token, HttpSession session, Model model) {
    Register register = (Register) session.getAttribute(token);
    if (register == null) {
      return "redirect:/logout/" + token;
    }
    int majorCount = majorService.getMajorCount(NO_SEARCH);
    model.addAttribute("token", token);
    model.addAttribute("count", Math.ceil(((double) majorCount) / DEFAULT_PAGE_SIZE));
    return "/register/major";
  }

  @RequestMapping("/register/getMajors")
  @ResponseBody
  public Map<String, Object> getMajors(String token, HttpSession session, Integer page,
      String search) {
    Register register = (Register) session.getAttribute(token);
    if (page == null) {
      page = INDEX_PAGE;
    }
    int majorCount = majorService.getMajorCount(search);
    return JSONBaseUtil
        .structuralResponseMap(majorService.getMajorList(page, DEFAULT_PAGE_SIZE, search),
            Math.ceil((double) majorCount / DEFAULT_PAGE_SIZE));
  }
}
