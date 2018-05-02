package cn.edu.jsu.rjxy.mappers;

import cn.edu.jsu.rjxy.entity.vo.Major;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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

  @Delete("DELETE FROM major WHERE id=#{id}")
  boolean deleteMajor(@Param("id") long id);

  @Insert("INSERT INTO major(number, name, schooling) "
      + "VALUE(#{major.number}, #{major.name}, #{major.schooling})")
  boolean insertMajor(@Param("major") Major major);

  @Update("UPDATE major "
      + "SET number=#{major.number},"
      + " name=#{major.name},"
      + " schooling=#{major.schooling} "
      + "WHERE id=#{major.id}")
  boolean updateMajor(@Param("major") Major major);

}
