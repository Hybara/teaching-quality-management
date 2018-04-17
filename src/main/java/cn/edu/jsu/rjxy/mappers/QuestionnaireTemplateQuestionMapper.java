package cn.edu.jsu.rjxy.mappers;

import cn.edu.jsu.rjxy.entity.vo.QuestionnaireTemplate;
import cn.edu.jsu.rjxy.entity.vo.QuestionnaireTemplateQuestion;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
      + "ORDER BY qb.type, tq.question")
  @ResultMap("templateQuestion")
  List<QuestionnaireTemplateQuestion> getQuestionnaire(@Param("template") long template);

  @Insert("INSERT INTO questionnaire_template_questions(template, question, coefficient) "
      + "VALUES(#{templateId}, #{questionId}, 1)")
  boolean addQuestion(@Param("templateId") long templateId, @Param("questionId") long questionId);

  boolean updateQuestionCoefficients(@Param("params") Map<Long, Double> params);

  @Update("UPDATE questionnaire_template_questions"
      + " SET question=#{newQuestion} "
      + " WHERE template=#{templateId} AND question=#{oldQuestion}")
  boolean updateQuestion(@Param("templateId") long templateId,
      @Param("oldQuestion") long oldQuestion,
      @Param("newQuestion") long newQuestion);

  @Delete("DELETE FROM questionnaire_template_questions "
      + "WHERE template=#{templateId} AND question=#{question}")
  boolean deleteQuestion(@Param("templateId") long templateId,
      @Param("question") long question);
}
