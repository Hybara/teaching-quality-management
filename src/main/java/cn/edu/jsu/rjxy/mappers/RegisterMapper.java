package cn.edu.jsu.rjxy.mappers;

import cn.edu.jsu.rjxy.entity.vo.Register;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface RegisterMapper {

  @Select("select * from register where number=#{number} and password=#{password}")
  Register getByNumberAndPassword(@Param("number") String number, @Param("password") String password);

}
