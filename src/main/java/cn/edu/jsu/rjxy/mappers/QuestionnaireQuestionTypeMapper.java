package cn.edu.jsu.rjxy.mappers;

import cn.edu.jsu.rjxy.entity.vo.QuestionnaireQuestionType;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface QuestionnaireQuestionTypeMapper {

  @Select("SELECT * FROM questionnaire_question_type WHERE id=#{id}")
  QuestionnaireQuestionType getById(@Param("id") long id);

  @Select("SELECT * FROM questionnaire_question_type WHERE name=#{name}")
  QuestionnaireQuestionType getByName(@Param("name") String name);

  @Select("SELECT * FROM questionnaire_question_type")
  List<QuestionnaireQuestionType> getAllType();

  @Insert("INSERT INTO questionnaire_question_type(name) value(#{name})")
  boolean addType(@Param("name") String name);

}
