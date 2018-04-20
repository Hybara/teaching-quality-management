package cn.edu.jsu.rjxy.mappers;

import cn.edu.jsu.rjxy.entity.vo.Evaluate;
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
public interface EvaluateMapper {

  @Select("SELECT * FROM evaluate WHERE id=#{id}")
  @ResultMap("result")
  Evaluate getById(@Param("id") long id);

  List<Evaluate> getScoreEvaluates(@Param("scoreForTeacherId") long scoreForTeacherId,
      @Param("type") String type, @Param("step") Integer step, @Param("size") Integer size);

  int getScoreEvaluatesCount(@Param("scoreForTeacherId") long scoreForTeacherId,
      @Param("type") String type);

  @Select("SELECT * FROM evaluate "
      + "WHERE score_for_teacher=#{scoreForTeacherId}"
      + " AND creater=#{creater}"
      + " AND creater_type=#{type}"
      + " ORDER BY create_time DESC "
      + " LIMIT 0,1")
  Evaluate getLastEvaluate(@Param("scoreForTeacherId") long scoreForTeacherId,
      @Param("creater") long creater, @Param("type") String type);

  @Insert("INSERT INTO evaluate(score_for_teacher, title, text, result, creater, create_time, creater_type, flag) "
      + "VALUE(#{evaluate.scoreForTeacher.id},"
      + "#{evaluate.title},"
      + "#{evaluate.text},"
      + "#{evaluate.result},"
      + "#{evaluate.creater},"
      + "NOW(),"
      + "#{evaluate.createrType},"
      + "#{evaluate.flag})")
  boolean insertScoreEvaluate(@Param("evaluate") Evaluate evaluate);

  @Update("UPDATE evaluate "
      + "SET title=#{evaluate.title},"
      + "  text=#{evaluate.text},"
      + "  result=#{evaluate.result} "
      + " WHERE id=#{evaluate.id}")
  boolean updateScoreEvaluate(@Param("evaluate") Evaluate evaluate);

}
