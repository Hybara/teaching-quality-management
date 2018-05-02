package cn.edu.jsu.rjxy.mappers;

import cn.edu.jsu.rjxy.entity.vo.Metadata;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MetadataMapper {

  @Select("SELECT * FROM metadata WHERE id=#{id}")
  Metadata getById(@Param("id") long id);

  @Select("SELECT * FROM metadata WHERE `value`=#{grade} AND `key` LIKE 'evaluate.%'")
  Metadata getEvaluateResultByValue(@Param("grade") double grade);

  @Select("SELECT * FROM metadata WHERE `key`=CONCAT('evaluate.', #{key})")
  Metadata getEvaluateResultByKey(@Param("key") String key);

  @Select("SELECT * FROM metadata WHERE `key`='evaluate.cycle'")
  Metadata getEvaluateCycle();

  @Select("SELECT * FROM metadata WHERE `value`=#{grade} AND `key` LIKE 'question.%'")
  Metadata getQuestionResultByValue(@Param("grade") double grade);

  @Select("SELECT * FROM metadata WHERE `key`=CONCAT('question.', #{key})")
  Metadata getQuestionResultByKey(@Param("key") String key);

  @Select("SELECT * FROM metadata WHERE `key`='question.cycle'")
  Metadata getQuestionCycle();

  @Select("SELECT * FROM metadata ORDER BY id")
  List<Metadata> getAll();

  boolean updateMetadatas(@Param("metadatas")Map<String, String> metadatas);

}
