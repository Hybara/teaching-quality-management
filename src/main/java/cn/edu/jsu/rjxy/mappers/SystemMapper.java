package cn.edu.jsu.rjxy.mappers;

import cn.edu.jsu.rjxy.entity.vo.System;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SystemMapper {

  @Select("SELECT * FROM system WHERE id=#{id}")
  System getById(@Param("id") long id);

  @Select("SELECT * FROM system WHERE `value`=#{grade} AND `key` LIKE 'evaluate.%'")
  System getEvaluateResultByValue(@Param("grade") double grade);

  @Select("SELECT * FROM system WHERE `key`=CONCAT('evaluate.', #{key})")
  System getEvaluateResultByKey(@Param("key") String key);

  @Select("SELECT * FROM system WHERE `key`='evaluate.cycle'")
  System getEvaluateCycle();

}
