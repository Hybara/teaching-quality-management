package cn.edu.jsu.rjxy.mappers;

import cn.edu.jsu.rjxy.entity.vo.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TeacherMapper {

  @Select("SELECT * FROM teacher WHERE id=#{id}")
  @ResultMap("teacherMap")
  Teacher getById(@Param("id") long id);

  @Select("SELECT * FROM teacher WHERE number=#{number} AND password=#{password}")
  Teacher getByNumberAndPassword(@Param("number") String number, @Param("password") String password);

  @Update("UPDATE teacher "
      + "SET number=#{teacher.number},"
      + " name=#{teacher.name},"
      + " sex=#{teacher.sex},"
      + " password=#{teacher.password},"
      + " major=#{teacher.major.id},"
      + " business=#{teacher.business},"
      + " email=#{teacher.email},"
      + " phone=#{teacher.phone},"
      + " QQ=#{teacher.qq},"
      + " wechat=#{teacher.wechat},"
      + " header=#{teacher.header},"
      + " evaluate=#{teacher.evaluate} "
      + "WHERE id=#{teacher.id}")
  boolean updateTeacher(@Param("teacher") Teacher teacher);
}
