package cn.edu.jsu.rjxy.controller;

import cn.edu.jsu.rjxy.entity.vo.Student;
import cn.edu.jsu.rjxy.service.ScoreService;
import cn.edu.jsu.rjxy.service.ScoreTypeService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {

  @Autowired
  private ScoreTypeService scoreTypeService;
  @Autowired
  private ScoreService scoreService;

  @RequestMapping("/login/{token}")
  public String login(@PathVariable String token, HttpSession session, Model model) {
    Student student = (Student) session.getAttribute(token);
    System.out.println(student);
    model.addAttribute("scoreTypes", scoreTypeService.getAll());
    model.addAttribute("token", token);
    return "/student/scores";
  }

  @RequestMapping("/getScores/{type}")
  public String getScores(@PathVariable String type, long stuId) {
    scoreService.getScoresInCurrentTerm(type, stuId);
    return "/student/score";
  }
}
