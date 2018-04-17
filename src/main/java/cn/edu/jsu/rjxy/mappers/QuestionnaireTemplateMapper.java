package cn.edu.jsu.rjxy.mappers;

import cn.edu.jsu.rjxy.entity.vo.QuestionnaireTemplate;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface QuestionnaireTemplateMapper {

  @Select("SELECT * FROM questionnaire_template WHERE id=#{id}")
  QuestionnaireTemplate getById(@Param("id") long id);

  @Select("SELECT * FROM questionnaire_template WHERE name=#{name}")
  QuestionnaireTemplate getByName(@Param("name") String name);

  List<QuestionnaireTemplate> getPage(@Param("step") Integer step, @Param("size") Integer size,
      @Param("search") String search);

  int getCount(@Param("search") String search);

  @Insert("INSERT INTO questionnaire_template(name, creater, create_time, updater, update_time) "
      + "VALUES(#{template.name}, #{template.creater}, NOW(), #{template.updater}, NOW())")
  boolean insertTemplate(@Param("template") QuestionnaireTemplate template);

  @Update("UPDATE questionnaire_template "
      + " SET name=#{name}, "
      + "  updater=#{updater}, "
      + "  update_time=NOW() "
      + "WHERE id=#{id}")
  boolean updateTemplateName(@Param("id") long templateId, @Param("name") String name,
      @Param("updater") long updaterId);

  @Update("UPDATE questionnaire_template "
      + " SET updater=#{updater}, "
      + "  update_time=NOW() "
      + "WHERE id=#{id}")
  boolean updateTemplateUpdater(@Param("id") long templateId, @Param("updater") long updaterId);
}
