package cn.edu.jsu.rjxy.mappers;

import cn.edu.jsu.rjxy.entity.vo.Question;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface QuestionMapper {

  @Select("SELECT * FROM question WHERE id=#{id}")
  @ResultMap("questionMap")
  Question getById(@Param("id") long id);

  List<Question> getScoreQuestions(@Param("scoreForTeacherId") long scoreForTeacherId,
      @Param("type") String type, @Param("step") Integer step, @Param("size") Integer size);

  int getScoreQuestionsCount(@Param("scoreForTeacherId") long scoreForTeacherId,
      @Param("type") String type);

  @Select("SELECT * FROM question "
      + "WHERE score_for_teacher=#{scoreForTeacherId}"
      + " AND creater=#{creater}"
      + " AND creater_type=#{type}"
      + " ORDER BY create_time DESC "
      + " LIMIT 0,1")
  Question getLastQuestion(@Param("scoreForTeacherId") long scoreForTeacherId,
      @Param("creater") long creater, @Param("type") String type);

  @Insert("INSERT INTO question(score_for_teacher, title, text, result, creater, create_time, creater_type, flag) "
      + "VALUE(#{question.scoreForTeacher.id},"
      + "#{question.title},"
      + "#{question.text},"
      + "#{question.result},"
      + "#{question.creater},"
      + "NOW(),"
      + "#{question.createrType},"
      + "#{question.flag})")
  boolean insertScoreQuestion(@Param("question") Question question);


  @Update("UPDATE question "
      + "SET score_for_teacher=#{question.scoreForTeacher.id}, "
      + " title=#{question.title},"
      + " text=#{question.text}, "
      + " result=#{question.result}, "
      + " creater=#{question.creater}, "
      + " create_time=#{question.createTime}, "
      + " creater_type=#{question.createrType}, "
      + " flag=#{question.flag} "
      + "WHERE id=#{question.id}")
  boolean updateScoreQuestion(@Param("question") Question question);

}
