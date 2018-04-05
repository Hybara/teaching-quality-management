package cn.edu.jsu.rjxy.controller.register;

import cn.edu.jsu.rjxy.entity.dto.ScoreDTO;
import cn.edu.jsu.rjxy.entity.dto.ScoreInfoDTO;
import cn.edu.jsu.rjxy.entity.vo.Register;
import cn.edu.jsu.rjxy.entity.vo.Score;
import cn.edu.jsu.rjxy.entity.vo.ScoreForTeacher;
import cn.edu.jsu.rjxy.entity.vo.Teacher;
import cn.edu.jsu.rjxy.service.MajorService;
import cn.edu.jsu.rjxy.service.ScoreService;
import cn.edu.jsu.rjxy.service.ScoreTypeService;
import cn.edu.jsu.rjxy.service.TeacherService;
import cn.edu.jsu.rjxy.util.JSONBaseUtil;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("RegisterScoreController")
public class ScoreController {

  @Autowired
  private MajorService majorService;
  @Autowired
  private TeacherService teacherService;
  @Autowired
  private ScoreService scoreService;
  @Autowired
  private ScoreTypeService scoreTypeService;

  private static final String ALL_SCORE_TYPE = null;
  private static final String NO_SEARCH = null;
  private static final int SCORES_PAGE_SIZE = 8;
  private static final int INDEX_PAGE_NUMBER = 1;
  private static final int PAGE_SIZE = 12;

  @RequestMapping("/register/majorScores/{majorId}/{token}")
  public String goMajorScores(@PathVariable Long majorId, @PathVariable String token,
      HttpSession session, Model model) {
    Register register = (Register) session.getAttribute(token);
    if (register == null) {
      return "redirect:/logout/" + token;
    }
    if (majorId == null) {
      return "redirect:/register/login/" + token;
    }
    int scoreCount = scoreService.getMajorScoresCountInCurrentTerm(majorId, NO_SEARCH);
    model.addAttribute("token", token);
    model.addAttribute("major", majorService.getById(majorId));
    model.addAttribute("count", Math.ceil(((double) scoreCount) / PAGE_SIZE));
    return "/register/teacher/scoreslist";
  }

  @RequestMapping("/register/getMajorScores")
  @ResponseBody
  public Map<String, Object> getMajorScores(String token, Long majorId, HttpSession session,
      Integer page, String search) {
    Register register = (Register) session.getAttribute(token);
    if (page == null) {
      page = INDEX_PAGE_NUMBER;
    }
    if (search == null || "".equals(search)) {
      search = NO_SEARCH;
    }
    int scoreCount = scoreService.getMajorScoresCountInCurrentTerm(majorId, NO_SEARCH);
    return JSONBaseUtil.structuralResponseMap(
        scoreService.getMajorScoresInCurrentTerm(majorId, page, PAGE_SIZE, search),
        Math.ceil(((double) scoreCount) / PAGE_SIZE));
  }

  @RequestMapping("/register/goScores/{majorId}/{teacherId}/{token}")
  public String goScores(@PathVariable Long majorId,
      @PathVariable Long teacherId,
      @PathVariable String token,
      HttpSession session, Model model) {
    Register register = (Register) session.getAttribute(token);
    if (register == null) {
      return "redirect:/logout/" + token;
    }
    if (majorId == null) {
      return "redirect:/register/login/" + token;
    }
    if (teacherId == null) {
      return "redirect:/register/teacherList/" + token;
    }

    int scoreCount = scoreService
        .getTeachScoresCountInCurrentTerm(ALL_SCORE_TYPE, teacherId, NO_SEARCH);
    model.addAttribute("type", "teacher");
    model.addAttribute("token", token);
    model.addAttribute("teacher", teacherService.getById(teacherId));
    model.addAttribute("scoreTypes", scoreTypeService.getAll());
    model.addAttribute("scoreCount", Math.ceil(((double) scoreCount) / SCORES_PAGE_SIZE));
    return "/register/teacher/scores";
  }

  @RequestMapping("/register/goMajorScores/{majorId}/{scoreId}/{token}")
  public String goMajorScores(@PathVariable Long majorId,
      @PathVariable Long scoreId,
      @PathVariable String token,
      HttpSession session, Model model) {
    Register register = (Register) session.getAttribute(token);
    if (register == null) {
      return "redirect:/logout/" + token;
    }
    if (majorId == null) {
      return "redirect:/register/login/" + token;
    }
    if (scoreId == null) {
      return "redirect:/register/majorScores/" + token;
    }
    Score score = scoreService.getScoreById(scoreId);
    int scoreCount = scoreService.getScoresCountInCurrentTerm(scoreId, NO_SEARCH);
    model.addAttribute("type", "score");
    model.addAttribute("token", token);
    model.addAttribute("score", scoreService.getScoreById(scoreId));
    model.addAttribute("scoreTypes", scoreTypeService.getAll());
    model.addAttribute("scoreCount", Math.ceil(((double) scoreCount) / SCORES_PAGE_SIZE));
    return "/register/teacher/scores";
  }

  @RequestMapping("/register/getScores/{teacherId}/{type}/{token}")
  @ResponseBody
  public Map<String, Object> getScores(@PathVariable long teacherId, @PathVariable String type,
      @PathVariable String token,
      HttpSession session, Integer page, String search) {
    Register register = (Register) session.getAttribute(token);
    if (page == null) {
      page = INDEX_PAGE_NUMBER;
    }
    List<ScoreDTO> scoreDTOS = scoreService
        .getTeachScoresInCurrentTerm(type, teacherId, page, SCORES_PAGE_SIZE, search);
    int scoreCount = scoreService.getTeachScoresCountInCurrentTerm(type, teacherId, search);
    return JSONBaseUtil.structuralResponseMap(
        scoreDTOS,
        Math.ceil((double) scoreCount / SCORES_PAGE_SIZE));
  }

  @RequestMapping("/register/getSameNameScores/{scoreId}/{token}")
  @ResponseBody
  public Map<String, Object> getSameNameScores(@PathVariable long scoreId,
      @PathVariable String token,
      HttpSession session, Integer page, String search) {
    Register register = (Register) session.getAttribute(token);
    if (page == null) {
      page = INDEX_PAGE_NUMBER;
    }
    List<ScoreDTO> scoreDTOS = scoreService
        .getScoresPageInCurrentTerm(scoreId, page, SCORES_PAGE_SIZE, search);
    int scoreCount = scoreService.getScoresCountInCurrentTerm(scoreId, search);
    return JSONBaseUtil.structuralResponseMap(
        scoreDTOS,
        Math.ceil((double) scoreCount / SCORES_PAGE_SIZE));
  }

  @RequestMapping("/register/goScore/{type}/{majorId}/{teacherId}/{scoreId}/{token}")
  public String goScore(@PathVariable String type, @PathVariable Long majorId, @PathVariable Long teacherId,
      @PathVariable Long scoreId, @PathVariable String token,
      HttpSession session, Model model) {
    Register register = (Register) session.getAttribute(token);
    if (register == null) {
      return "redirect:/logout/" + token;
    }
    if (majorId == null) {
      return "redirect:/register/login/" + token;
    }
    if (teacherId == null) {
      return "redirect:/register/teacherList/" + token;
    }
    if (scoreId == null) {
      return "/register/goScores/" + majorId + "/" + teacherId + "/" + token;
    }
    if (type == null) {
      type = "teacher";
    }
    Teacher teacher = teacherService.getById(teacherId);
    ScoreInfoDTO scoreInfoDTO = scoreService.getScoreByScoreForTeacherId(scoreId);

    model.addAttribute("type", type);
    model.addAttribute("token", token);
    model.addAttribute("teacher", teacherService.getById(teacherId));
    model.addAttribute("score", scoreService.getScoreByScoreForTeacherId(scoreId));
    return "/register/teacher/score";
  }

}
