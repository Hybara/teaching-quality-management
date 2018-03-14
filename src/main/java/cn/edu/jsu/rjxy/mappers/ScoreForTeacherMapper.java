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

  List<ScoreForTeacher> getScoresForClasses(@Param("type") String scoreType,
      @Param("classes") List<StuForClass> classes, @Param("step") Integer step,
      @Param("size") Integer size, @Param("search") String search);

  int getScoresCountForClasses(@Param("type") String scoreType,
      @Param("classes") List<StuForClass> classes, @Param("search") String search);
}
