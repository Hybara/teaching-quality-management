package cn.edu.jsu.rjxy.service;

import cn.edu.jsu.rjxy.entity.vo.FillInQuestionnaire;
import cn.edu.jsu.rjxy.entity.vo.ScoreForTeacher;
import cn.edu.jsu.rjxy.mappers.FillInQuestionnaireMapper;
import cn.edu.jsu.rjxy.mappers.ScoreForTeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FillInQuestionnaireService {

  @Autowired
  FillInQuestionnaireMapper fillInQuestionnaireMapper;
  @Autowired
  ScoreForTeacherMapper scoreForTeacherMapper;

  public boolean questionnaireIsFilledIn(long fillInQuestionnaireId) {
    if (fillInQuestionnaireMapper.getById(fillInQuestionnaireId) != null) {
      return true;
    } else {
      return false;
    }
  }

  public FillInQuestionnaire getById(long fillInQuestionnaireId) {
    return fillInQuestionnaireMapper.getById(fillInQuestionnaireId);
  }

  public FillInQuestionnaire getByQuestionnaireAndCreater(long fillInQuestionnaireId, long creater,
      String type) {
    if (type == null) {
      return null;
    }
    return fillInQuestionnaireMapper
        .getByQuestionnaireAndCreater(fillInQuestionnaireId, creater, type);
  }

  public boolean fillInQuestionnaire(long questionnaireId, double result, long craeter,
      String type, long scoreForTeacherId) {
    if (type == null) {
      return false;
    }
    if (fillInQuestionnaireMapper.insertFillInQuestionnaire(
        new FillInQuestionnaire(questionnaireId, result, craeter, null, type))) {
      ScoreForTeacher score = scoreForTeacherMapper.getById(scoreForTeacherId);
      score.setAssessmentCount(score.getAssessmentCount() + 1);
      score.setAssessmentGrade(score.getAssessmentGrade() + result);
      if (scoreForTeacherMapper.updateScoreForTeacher(score)) {
        return true;
      }
    }
    return false;
  }


}
