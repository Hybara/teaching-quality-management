package cn.edu.jsu.rjxy.mappers;

import cn.edu.jsu.rjxy.entity.vo.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MessageMapper {

  @Select("SELECT * FROM message WHERE id=#{id}")
  Message getById(@Param("id") long id);

}
