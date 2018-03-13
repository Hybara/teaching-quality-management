package cn.edu.jsu.rjxy.mappers;

import cn.edu.jsu.rjxy.entity.vo.Classes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ClassesMapper {

  @Select("select * from classes where id=#{id}")
  Classes getById(@Param("id") long id);
}
