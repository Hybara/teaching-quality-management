package cn.edu.jsu.rjxy.mappers;

import cn.edu.jsu.rjxy.entity.vo.ForQuestion;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ForQuestionMapper {

  @Select("SELECT * FROM for_question WHERE id=#{id}")
  ForQuestion getById(@Param("id") long id);

  @Select("SELECT * FROM for_question "
      + "WHERE question=#{questionId} "
      + "ORDER BY create_time DESC")
  List<ForQuestion> getByQuestionId(@Param("questionId") long questionId);

  @Select("SELECT * FROM for_question "
      + "WHERE question=#{questionId} "
      + "ORDER BY create_time DESC "
      + "LIMIT #{step},#{size}")
  List<ForQuestion> getPageByQuestionId(@Param("questionId") long questionId,
      @Param("step") int step, @Param("size") int size);

  @Select("SELECT COUNT(*) FROM for_question WHERE question=#{questionId}")
  int getCountByQuestionId(@Param("questionId") long questionId);

  @Insert("INSERT INTO for_question(question, text, creater, create_time, creater_type, flag) "
      + "value(#{forQuestion.question.id},"
      + " #{forQuestion.text},"
      + " #{forQuestion.creater},"
      + " NOW(),"
      + " #{forQuestion.createrType},"
      + " #{forQuestion.flag})")
  boolean insertForQuestion(@Param("forQuestion") ForQuestion forQuestion);
}
