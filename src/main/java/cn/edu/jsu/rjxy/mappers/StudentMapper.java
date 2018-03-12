package cn.edu.jsu.rjxy.mappers;

import cn.edu.jsu.rjxy.entity.vo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface StudentMapper {

  @Select("select * from student where stu_id=#{stuId} and password=#{password}")
  Student getByStuIdAndPassword(@Param("stuId") String stuId, @Param("password") String password);

}
