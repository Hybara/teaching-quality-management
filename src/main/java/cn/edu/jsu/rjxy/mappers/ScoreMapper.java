package cn.edu.jsu.rjxy.mappers;

import cn.edu.jsu.rjxy.entity.vo.Score;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ScoreMapper {

  @Select("SELECT * FROM score WHERE id=#{id}")
  @ResultMap("ScoreMap")
  Score getById(@Param("id") long id);

  List<Score> getPageByMajor(@Param("majorId") long majorId, @Param("step") Integer step,
      @Param("size") Integer size, @Param("search") String search);

  int getCountByMajor(@Param("majorId") long majorId, @Param("search") String search);
}
