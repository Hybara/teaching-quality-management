package cn.edu.jsu.rjxy.mappers;

import cn.edu.jsu.rjxy.entity.vo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AdminMapper {

  @Select("select * from admin where id = #{id}")
  @ResultMap("AdminResult")
  Admin findById(@Param("id") long id);

}
