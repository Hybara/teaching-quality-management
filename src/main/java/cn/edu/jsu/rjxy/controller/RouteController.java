package cn.edu.jsu.rjxy.controller;

import cn.edu.jsu.rjxy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouteController {

  @Autowired
  private UserService userService;

  @RequestMapping({"/", "/index"})
  public String index() {
    System.out.println(userService.getUserList());
    return "index";
  }
}
