package cn.edu.jsu.rjxy.service;

import cn.edu.jsu.rjxy.entity.vo.ScoreForTeacher;
import cn.edu.jsu.rjxy.entity.vo.StuForClass;
import cn.edu.jsu.rjxy.mappers.ScoreForTeacherMapper;
import cn.edu.jsu.rjxy.mappers.StuForClassMapper;
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

  public List<ScoreForTeacher> getScoresInCurrentTerm(String scoreType, long stuId) {
    List<StuForClass> classes = stuForClassMapper.getStudentClassesInCurrentTermByStuId(stuId);
    System.out.println(scoreForTeacherMapper.getScoresForClasses(classes));
    return null;
  }

}
