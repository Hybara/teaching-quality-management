package cn.edu.jsu.rjxy.service;

import cn.edu.jsu.rjxy.entity.dto.ScoreDTO;
import cn.edu.jsu.rjxy.entity.dto.ScoreInfoDTO;
import cn.edu.jsu.rjxy.entity.vo.Evaluate;
import cn.edu.jsu.rjxy.entity.vo.Score;
import cn.edu.jsu.rjxy.entity.vo.ScoreForTeacher;
import cn.edu.jsu.rjxy.entity.vo.StuForClass;
import cn.edu.jsu.rjxy.entity.vo.Study;
import cn.edu.jsu.rjxy.mappers.ScoreForTeacherMapper;
import cn.edu.jsu.rjxy.mappers.ScoreMapper;
import cn.edu.jsu.rjxy.mappers.StuForClassMapper;
import cn.edu.jsu.rjxy.mappers.StudentMapper;
import cn.edu.jsu.rjxy.mappers.StudyMapper;
import cn.edu.jsu.rjxy.util.QueryConditionsUitl;
import java.util.ArrayList;
import java.util.List;
import javax.swing.text.Style;
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
  @Autowired
  StudyMapper studyMapper;
  @Autowired
  ScoreMapper scoreMapper;

  public Score getScoreById(long scoreId) {
    return scoreMapper.getById(scoreId);
  }

  public ScoreForTeacher getById(long scoreForTeacherId) {
    return scoreForTeacherMapper.getById(scoreForTeacherId);
  }

  public ScoreInfoDTO getScoreByScoreForTeacherId(long scoreForTeacherId) {
    return new ScoreInfoDTO(scoreForTeacherMapper.getById(scoreForTeacherId));
  }

  public List<ScoreDTO> getScoresInCurrentTerm(String scoreType, long stuId, Integer index,
      Integer size, String search) {
    if ("all".equals(scoreType)) {
      scoreType = null;
    }
    search = QueryConditionsUitl.constructQueryConditions(search);

    List<Study> studies = studyMapper
        .getPageByStudentId(stuId, (index - 1) * size, size, scoreType, search);
    List<ScoreForTeacher> scoresForTeachers = new ArrayList<>();
    for (Study study : studies) {
      scoresForTeachers.add(study.getScoreForTeacher());
    }

    List<ScoreDTO> scores = new ArrayList<>();
    for (ScoreForTeacher score : scoresForTeachers) {
      scores.add(new ScoreDTO(score));
    }

    return scores;
  }

  public int getScoresCountInCurrentTerm(String scoreType, long stuId, String search) {
    if ("all".equals(scoreType)) {
      scoreType = null;
    }
    search = QueryConditionsUitl.constructQueryConditions(search);
    return studyMapper.getCountByStudentId(stuId, scoreType, search);
  }

  public List<ScoreDTO> getTeachScoresInCurrentTerm(String scoreType, long teacherId, Integer index,
      Integer size, String search) {
    if ("all".equals(scoreType)) {
      scoreType = null;
    }
    search = QueryConditionsUitl.constructQueryConditions(search);

    List<ScoreForTeacher> scoresForTeacher = scoreForTeacherMapper
        .getScoresForTeacher(scoreType, teacherId, (index - 1) * size, size, search);

    List<ScoreDTO> scores = new ArrayList<>();
    for (ScoreForTeacher score : scoresForTeacher) {
      scores.add(new ScoreDTO(score));
    }

    return scores;
  }

  public int getTeachScoresCountInCurrentTerm(String scoreType, long teacherId, String search) {
    if ("all".equals(scoreType)) {
      scoreType = null;
    }
    search = QueryConditionsUitl.constructQueryConditions(search);
    return scoreForTeacherMapper.getScoresCountForTeacher(scoreType, teacherId, search);
  }

  public boolean updateScoreForTeacher(ScoreForTeacher scoreForTeacher) {
    return scoreForTeacherMapper.updateScoreForTeacher(scoreForTeacher);
  }

  public List<ScoreInfoDTO> getMajorScoresInCurrentTerm(long majorId, Integer index,
      Integer size, String search) {
    search = QueryConditionsUitl.constructQueryConditions(search);
    Integer step = null;
    if (index != null && index > 1 && size != null) {
      step = (index - 1) * size;
    }
    List<Score> scores = scoreMapper.getPageByMajor(majorId, step, size, search);
    List<ScoreInfoDTO> scoreInfoDTOS = new ArrayList<>();
    for (Score score : scores) {
      scoreInfoDTOS.add(new ScoreInfoDTO(score));
    }
    return scoreInfoDTOS;
  }

  public int getMajorScoresCountInCurrentTerm(long majorId, String search) {
    search = QueryConditionsUitl.constructQueryConditions(search);
    return scoreMapper.getCountByMajor(majorId, search);
  }

  public List<ScoreDTO> getScoresPageInCurrentTerm(long scoreId, Integer index,
      Integer size, String search) {
    search = QueryConditionsUitl.constructQueryConditions(search);
    Integer step = null;
    if (index != null && index >= 1 && size != null) {
      step = (index - 1) * size;
    }
    List<ScoreForTeacher> scoreForTeachers = scoreForTeacherMapper.getScoresPage(scoreId, step, size, search);
    List<ScoreDTO> scoreDTOs = new ArrayList<>();
    for (ScoreForTeacher scoreForTeacher : scoreForTeachers) {
      scoreDTOs.add(new ScoreDTO(scoreForTeacher));
    }
    return scoreDTOs;
  }

  public int getScoresCountInCurrentTerm(long scoreId, String search) {
    search = QueryConditionsUitl.constructQueryConditions(search);
    return scoreForTeacherMapper.getScoresCount(scoreId, search);
  }
}
