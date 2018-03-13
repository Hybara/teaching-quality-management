package cn.edu.jsu.rjxy.mappers;

import cn.edu.jsu.rjxy.entity.vo.ScoreForTeacher;
import cn.edu.jsu.rjxy.entity.vo.StuForClass;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ScoreForTeacherMapper {

  List<ScoreForTeacher> getScoresForClasses(@Param("classes") List<StuForClass> classes);

}
