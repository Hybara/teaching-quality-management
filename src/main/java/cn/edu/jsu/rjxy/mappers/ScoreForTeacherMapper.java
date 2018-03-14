package cn.edu.jsu.rjxy.mappers;

import cn.edu.jsu.rjxy.entity.vo.ScoreForTeacher;
import cn.edu.jsu.rjxy.entity.vo.StuForClass;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ScoreForTeacherMapper {

  @Select("SELECT * FROM score_for_teacher WHERE id=#{id}")
  @ResultMap("ScoreForTeacherMap")
  ScoreForTeacher getById(@Param("id") long id);

  List<ScoreForTeacher> getScoresForClasses(@Param("classes") List<StuForClass> classes);

}
