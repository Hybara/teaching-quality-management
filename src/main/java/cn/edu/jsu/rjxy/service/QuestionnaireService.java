package cn.edu.jsu.rjxy.service;

import cn.edu.jsu.rjxy.entity.vo.QuestionnaireQuestionType;
import cn.edu.jsu.rjxy.mappers.QuestionnaireQuestionTypeMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionnaireService {

  @Autowired
  QuestionnaireQuestionTypeMapper questionnaireQuestionTypeMapper;

  public QuestionnaireQuestionType getQuestionTypeById(long id) {
    return questionnaireQuestionTypeMapper.getById(id);
  }

  public QuestionnaireQuestionType getQuestionTypeByName(String name) {
    return questionnaireQuestionTypeMapper.getByName(name);
  }

  public List<QuestionnaireQuestionType> getAllQuestionType() {
    return questionnaireQuestionTypeMapper.getAllType();
  }

  public boolean addQuestionType(String name) {
    if (name==null || "".equals(name)) {
      return false;
    }
    if (questionnaireQuestionTypeMapper.getByName(name) != null) {
      return false;
    }
    String reg = "[\\u4e00-\\u9fa5]+";
    if (name.matches(reg)) {
      return questionnaireQuestionTypeMapper.addType(name);
    }
    return false;
  }
}
