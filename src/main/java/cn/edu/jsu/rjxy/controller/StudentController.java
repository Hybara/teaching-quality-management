package cn.edu.jsu.rjxy.controller;

import cn.edu.jsu.rjxy.entity.dto.ScoreDTO;
import cn.edu.jsu.rjxy.entity.vo.Student;
import cn.edu.jsu.rjxy.service.ScoreService;
import cn.edu.jsu.rjxy.service.ScoreTypeService;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

  @RequestMapping("/getScores/{type}/{token}")
  @ResponseBody
  public List<ScoreDTO> getScores(@PathVariable String type, @PathVariable String token, HttpSession session) {
    Student student = (Student) session.getAttribute(token);
    List<ScoreDTO> scores = scoreService.getScoresInCurrentTerm(type, student.getId());
    System.out.println("scores:\n"+scores);
    return scores;
  }
}
