package cn.edu.jsu.rjxy.mappers;

import cn.edu.jsu.rjxy.entity.vo.StuForClass;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface StuForClassMapper {

  @Select("SELECT * FROM stu_for_class WHERE id=#{id}")
  StuForClass getById(@Param("id") long id);

  @Select("SELECT * FROM stu_for_class WHERE stu_id=#{stuId}")
  List<StuForClass> getStudentClassesByStuId(@Param("stuId") Long stuId);

  List<StuForClass> getStudentClassesInCurrentTermByStuId(@Param("stuId") Long stuId);


}
