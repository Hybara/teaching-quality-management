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

  @Select("select * from admin where id=#{id}")
  Admin getById(@Param("id") long id);

  @Select("select * from admin where account=#{account} and password=#{password}")
  Admin getByAccountAndPassword(@Param("account") String account, @Param("password") String password);

}
