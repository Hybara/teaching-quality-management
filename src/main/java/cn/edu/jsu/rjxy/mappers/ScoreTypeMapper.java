package cn.edu.jsu.rjxy.mappers;

import cn.edu.jsu.rjxy.entity.vo.ScoreType;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ScoreTypeMapper {

  @Select("SELECT * FROM score_type WHERE id=#{id}")
  ScoreType getById(@Param("id") long id);

  @Select("SELECT * FROM score_type")
  List<ScoreType> getAll();
}
