package cn.edu.jsu.rjxy.mappers;

import cn.edu.jsu.rjxy.entity.vo.QuestionnaireBank;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface QuestionnaireBankMapper {

  @Select("SELECT * FROM questionnaire_bank WHERE id=#{id}")
  @ResultMap("bankMap")
  QuestionnaireBank getById(@Param("id") long id);

  List<QuestionnaireBank> getPageByType(@Param("type") long typeId,
      @Param("step") Integer step,
      @Param("size") Integer size,
      @Param("search") String search);

  int getCountByType(@Param("type") long typeId, @Param("search") String search);
}
