package cn.edu.jsu.rjxy.mappers;

import cn.edu.jsu.rjxy.entity.vo.Score;
import cn.edu.jsu.rjxy.entity.vo.ScoreForTeacher;
import cn.edu.jsu.rjxy.entity.vo.StuForClass;
import cn.edu.jsu.rjxy.entity.vo.Teacher;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ScoreForTeacherMapper {

  @Select("SELECT * FROM score_for_teacher WHERE id=#{id}")
  @ResultMap("ScoreForTeacherMap")
  ScoreForTeacher getById(@Param("id") long id);

  List<ScoreForTeacher> getScoresForTeacher(@Param("type") String scoreType,
      @Param("teacherId") long teacherId, @Param("step") Integer step,
      @Param("size") Integer size, @Param("search") String search);

  int getScoresCountForTeacher(@Param("type") String scoreType,
      @Param("teacherId") long teacherId, @Param("search") String search);

  @Update("UPDATE score_for_teacher "
      + "SET score=#{scoreForTeacher.score.id},"
      + " teacher=#{scoreForTeacher.teacher.id},"
      + " class=#{scoreForTeacher.stuForClass.id},"
      + " result=#{scoreForTeacher.result},"
      + " evaluate_grade=#{scoreForTeacher.evaluateGrade},"
      + " evaluate_count=#{scoreForTeacher.evaluateCount},"
      + " question_grade=#{scoreForTeacher.questionGrade},"
      + " question_count=#{scoreForTeacher.questionCount},"
      + " assessment_grade=#{scoreForTeacher.assessmentGrade},"
      + " assessment_count=#{scoreForTeacher.assessmentCount}, "
      + " remarks=#{scoreForTeacher.remarks} "
      + "WHERE id=#{scoreForTeacher.id}")
  boolean updateScoreForTeacher(@Param("scoreForTeacher")ScoreForTeacher scoreForTeacher);
}
