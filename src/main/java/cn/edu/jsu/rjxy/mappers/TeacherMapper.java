package cn.edu.jsu.rjxy.mappers;

import cn.edu.jsu.rjxy.entity.vo.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TeacherMapper {

  @Select("SELECT * FROM teacher WHERE id=#{id}")
  Teacher getById(@Param("id") long id);

  @Select("SELECT * FROM teacher WHERE number=#{number} AND password=#{password}")
  Teacher getByNumberAndPassword(@Param("number") String number, @Param("password") String password);
}
