package cn.edu.jsu.rjxy.controller.register;

import cn.edu.jsu.rjxy.entity.vo.Register;
import cn.edu.jsu.rjxy.service.RegisterService;
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
public class RegisterController {

  @Autowired
  private RegisterService registerService;

  @Value("${img.url}")
  public String basePath;

  private static final String USER_TYPE = "register";

  @RequestMapping("/register/login/{token}")
  public String login(@PathVariable String token, HttpSession session) {
    Register register = (Register) session.getAttribute(token);
    if (register == null) {
      return "redirect:/logout/" + token;
    }
    return "redirect:/register/majors/" + token;
  }


  @RequestMapping("/register/goPassword/{token}")
  public String goPassword(@PathVariable String token, HttpSession session) {
    Register register = (Register) session.getAttribute(token);
    if (register == null) {
      return "forward:/logout/" + token;
    } else {
      return "/register/changePassword";
    }
  }

  @RequestMapping("/register/checkPassword")
  @ResponseBody
  public String chechPassword(String token, String password, HttpSession session) {
    Register register = (Register) session.getAttribute(token);
    if (register == null) {
      return "logout";
    }
    if (password != null && register.getPassword().equals(password)) {
      return "ok";
    } else {
      return "error";
    }
  }

  @RequestMapping("/register/changePassword")
  @ResponseBody
  public String changePassword(String token, String password, HttpSession session) {
    Register register = (Register) session.getAttribute(token);
    if (register == null) {
      return "logout";
    }
    if (registerService.setPassword(register.getId(), password)) {
      session.setAttribute(token, registerService.getById(register.getId()));
      return "ok";
    } else {
      return "error";
    }
  }

  @RequestMapping("/register/goHeader/{token}")
  public String goHeader(@PathVariable String token, HttpSession session, Model model) {
    Register register = (Register) session.getAttribute(token);
    model.addAttribute("message", "");
    if (register != null) {
      return "/register/changeHeader";
    } else {
      return "redirect:/logout/" + token;
    }
  }

  @RequestMapping("/register/changeHeader/{token}")
  public String changeHeader(@RequestParam(value = "file") MultipartFile file,
      @PathVariable String token, HttpSession session, Model model) {
    Register register = (Register) session.getAttribute(token);
    if (register == null) {
      return "forward:/logout/" + token;
    }
    if (file.isEmpty()) {
      model.addAttribute("message", "文件为空");
      return "/register/changeHeader";
    }
    // 设置文件名
    String fileName = MD5Util.getMD5(register.getNumber().toString());
    // 获取文件的后缀名
    String suffixName = file.getOriginalFilename()
        .substring(file.getOriginalFilename().lastIndexOf("."));
    fileName += suffixName;
    if (HeaderUploadUtil.uploadHeader(file, basePath, fileName, USER_TYPE)) {
      if (registerService
          .setHeader(register.getId(), File.separator + "register" + File.separator + fileName)) {
        register = registerService.getById(register.getId());
        session.setAttribute(token, register);
        model.addAttribute("message", "上传成功");
      } else {
        model.addAttribute("message", "上传失败");
      }
    } else {
      model.addAttribute("message", "上传失败");
    }
    return "/register/changeHeader";
  }

}
