package cn.edu.jsu.rjxy.service;

import cn.edu.jsu.rjxy.entity.dto.ScoreDTO;
import cn.edu.jsu.rjxy.entity.vo.Score;
import cn.edu.jsu.rjxy.entity.vo.ScoreForTeacher;
import cn.edu.jsu.rjxy.entity.vo.StuForClass;
import cn.edu.jsu.rjxy.mappers.ScoreForTeacherMapper;
import cn.edu.jsu.rjxy.mappers.StuForClassMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ScoreService {

  @Autowired
  ScoreForTeacherMapper scoreForTeacherMapper;
  @Autowired
  StuForClassMapper stuForClassMapper;

  public List<ScoreDTO> getScoresInCurrentTerm(String scoreType, long stuId) {
    List<StuForClass> classes = stuForClassMapper.getStudentClassesInCurrentTermByStuId(stuId);
    System.out.println(classes);
    List<ScoreForTeacher> scoresForTeacher = scoreForTeacherMapper.getScoresForClasses(classes);
    List<ScoreDTO> scores = new ArrayList<>();
    for(ScoreForTeacher score : scoresForTeacher) {
      scores.add(new ScoreDTO(score));
    }
    return scores;
  }

}
