package cn.edu.jsu.rjxy.mappers;

import cn.edu.jsu.rjxy.entity.vo.Register;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface RegisterMapper {

  @Select("SELECT * FROM register WHERE id=#{id}")
  Register getById(@Param("id") long id);

  @Select("SELECT * FROM register WHERE number=#{number} AND password=#{password}")
  Register getByNumberAndPassword(@Param("number") String number, @Param("password") String password);

  @Update("UPDATE register SET password=#{password} WHERE id=#{id}")
  boolean updatePassword(@Param("id") long id, @Param("password") String password);

  @Update("UPDATE register SET header=#{header} WHERE id=#{id}")
  boolean updateHeader(@Param("id") long id, @Param("header") String header);

}
