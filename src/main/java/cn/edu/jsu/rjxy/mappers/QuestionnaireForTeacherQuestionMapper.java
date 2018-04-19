package cn.edu.jsu.rjxy.mappers;

import cn.edu.jsu.rjxy.entity.vo.QuestionnaireForTeacherQuestion;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface QuestionnaireForTeacherQuestionMapper {

  @Select("SELECT * FROM questionnaire_for_teacher_questions WHERE id=#{id}")
  @ResultMap("result")
  QuestionnaireForTeacherQuestion getById(@Param("id") long id);

  @Select("SELECT * FROM questionnaire_for_teacher_questions WHERE questionnaire=#{questionnaire}")
  @ResultMap("result")
  List<QuestionnaireForTeacherQuestion> getByQuestionnaireId(@Param("questionnaire") long questionnaire);

  boolean insertQuestionnaireForTeacherQuestion(@Param("templateId") long templateId,
      @Param("questionIds") List<Long> questionIds);
}
