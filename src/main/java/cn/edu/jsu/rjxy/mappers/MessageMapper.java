package cn.edu.jsu.rjxy.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MessageMapper {

  @Select("select count(*) from message where ")
  int getStudentMessageCount(@Param("stuId") int stuId);

}
