package cn.edu.jsu.rjxy.mappers;

import cn.edu.jsu.rjxy.entity.vo.StuForClass;
import cn.edu.jsu.rjxy.entity.vo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
@Mapper
public interface StudentMapper {

  @Select("select * from student where id=#{id}")
  Student getById(@Param("id") long id);

  @Select("select * from student where number=#{number} and password=#{password}")
  Student getByNumberAndPassword(@Param("number") String number, @Param("password") String password);

}
