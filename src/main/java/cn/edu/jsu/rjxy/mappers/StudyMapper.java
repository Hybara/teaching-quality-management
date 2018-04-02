package cn.edu.jsu.rjxy.mappers;

import cn.edu.jsu.rjxy.entity.vo.Study;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface StudyMapper {

  @Select("SELECT * FROM study WHERE id=#{id}")
  @ResultMap("StudyResult")
  Study getById(@Param("id") long id);

  List<Study> getPageByStudentId(@Param("stuId") long stuId, @Param("step") Integer step,
      @Param("size") Integer size, @Param("type") String type, @Param("search") String search);

  int getCountByStudentId(@Param("stuId") long stuId, @Param("type") String type,
      @Param("search") String search);
}
