package cn.edu.jsu.rjxy.service;

import cn.edu.jsu.rjxy.entity.dto.ScoreDTO;
import cn.edu.jsu.rjxy.entity.dto.ScoreInfoDTO;
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

  public ScoreInfoDTO getScoreByScoreForTeacherId(long scoreForTeacherId) {
    return new ScoreInfoDTO(scoreForTeacherMapper.getById(scoreForTeacherId));
  }

  public List<ScoreDTO> getScoresInCurrentTerm(String scoreType, long stuId, Integer index,
      Integer size, String search) {
    if ("all".equals(scoreType)) {
      scoreType = null;
    }
    search = constructQueryConditions(search);
    List<StuForClass> classes = stuForClassMapper.getStudentClassesInCurrentTermByStuId(stuId);
//    System.out.println(classes);
    List<ScoreForTeacher> scoresForTeacher = scoreForTeacherMapper
        .getScoresForClasses(scoreType, classes, (index - 1) * size, size, search);

    List<ScoreDTO> scores = new ArrayList<>();
    for (ScoreForTeacher score : scoresForTeacher) {
      scores.add(new ScoreDTO(score));
    }

    return scores;
  }

  public int getScoresCountInCurrentTerm(String scoreType, long stuId, String search) {
    if ("all".equals(scoreType)) {
      scoreType = null;
    }
    search = constructQueryConditions(search);
    List<StuForClass> classes = stuForClassMapper.getStudentClassesInCurrentTermByStuId(stuId);
//    System.out.println(classes);
    return scoreForTeacherMapper.getScoresCountForClasses(scoreType, classes, search);
  }

  private String constructQueryConditions(String search) {
    if (search!=null) {
      char[] searchArray = search.toCharArray();
      search = "%";
      for (int i=0; i<searchArray.length; i++) {
        search += searchArray[i]+"%";
      }
    }
    System.out.println("search: "+search);
    return search;
  }

}
