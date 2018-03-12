package cn.edu.jsu.rjxy.mappers;

import cn.edu.jsu.rjxy.entity.vo.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TeacherMapper {

  @Select("select * from teacher where number=#{number} and password=#{password}")
  Teacher getByNumberAndPassword(@Param("number") String number, @Param("password") String password);
}
