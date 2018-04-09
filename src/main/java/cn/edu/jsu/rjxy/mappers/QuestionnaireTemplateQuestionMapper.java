package cn.edu.jsu.rjxy.mappers;

import cn.edu.jsu.rjxy.entity.vo.QuestionnaireTemplate;
import cn.edu.jsu.rjxy.entity.vo.QuestionnaireTemplateQuestion;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface QuestionnaireTemplateQuestionMapper {

  @Select("SELECT * FROM questionnaire_template_questions WHERE id=#{id}")
  @ResultMap("templateQuestion")
  QuestionnaireTemplateQuestion getById(@Param("id") long id);

  @Select("SELECT tq.* FROM questionnaire_template_questions tq "
      + "LEFT JOIN questionnaire_bank qb ON tq.question=qb.id "
      + "WHERE tq.template=#{template} "
      + "ORDER BY qb.type")
  @ResultMap("templateQuestion")
  List<QuestionnaireTemplateQuestion> getQuestionnaire(@Param("template") long template);

  boolean updateQuestionCoefficients(@Param("params") Map<Long, Double> params);
}
