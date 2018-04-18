package cn.edu.jsu.rjxy.mappers;

import cn.edu.jsu.rjxy.entity.vo.QuestionnaireForTeacher;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface QuestionnaireForTeacherMapper {

  @Select("SELECT * FROM questionnaire_for_teacher WHERE id=#{id}")
  QuestionnaireForTeacher getById(@Param("id") long id);

  @Select("SELECT * FROM questionnaire_for_teacher "
      + "WHERE score_for_teacher=#{scoreId} "
      + " AND (NOW() BETWEEN start_time AND end_time)")
  QuestionnaireForTeacher getTeacherQuestionnaire(@Param("scoreId") long scoreForTeacherId);

  @Insert("INSERT INTO questionnaire_for_teacher(score_for_teacher, creater, create_time, start_time, end_time) "
      + "VALUES(#{questionnaire.scoreId},"
      + "#{questionnaire.creater},"
      + "NOW(),"
      + "NOW(),"
      + "#{questionnaire.endTime})")
  boolean insertQuestionnaireForTeacher(@Param("questionnaire") QuestionnaireForTeacher questionnaire);
}
