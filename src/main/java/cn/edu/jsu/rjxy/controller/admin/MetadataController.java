package cn.edu.jsu.rjxy.controller.admin;

import cn.edu.jsu.rjxy.entity.vo.Admin;
import cn.edu.jsu.rjxy.service.MetadataService;
import java.util.Arrays;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MetadataController {

  @Autowired
  MetadataService metadataService;

  @RequestMapping("/admin/goMetadata/{token}")
  public String goMetadata(@PathVariable String token, HttpSession session, Model model) {
    Admin admin = (Admin) session.getAttribute(token);
    if (admin == null) {
      return "redirect:/logout/" + token;
    }
    model.addAttribute("metadatas", metadataService.getAllMetadata());
    model.addAttribute("token", token);
    return "/admin/metadata";
  }

  @RequestMapping("/admin/updateMetadata/{token}")
  public String updateMetadata(String[] metadatas, @PathVariable String token, HttpSession session) {
    Admin admin = (Admin) session.getAttribute(token);
    if (admin == null) {
      return "redirect:/admin/goMetadata/" + token;
    }
    metadataService.updateMetadatas(Arrays.asList(metadatas));
    return "redirect:/admin/goMetadata/" + token;
  }
}