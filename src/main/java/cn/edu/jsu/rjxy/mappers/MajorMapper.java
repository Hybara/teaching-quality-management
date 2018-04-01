package cn.edu.jsu.rjxy.mappers;

import cn.edu.jsu.rjxy.entity.vo.Major;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MajorMapper {

  @Select("SELECT * FROM major WHERE id=#{id}")
  Major getById(@Param("id") long id);

  @Select("SELECT * FROM major WHERE number=#{number}")
  Major getByNumber(@Param("number") String number);

  @Select("SELECT * FROM major WHERE name=#{name}")
  Major getByName(@Param("name") String name);

  List<Major> getPage(@Param("step") Integer step, @Param("size") Integer size,
      @Param("search") String search);

  int getCount(@Param("search") String search);
}
