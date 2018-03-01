package cn.edu.jsu.rjxy.mappers;

import cn.edu.jsu.rjxy.entity.User;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {

  @Select("select * from user")
  @ResultMap("userResult")
  List<User> findAll();

}
