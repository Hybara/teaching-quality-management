package cn.edu.jsu.rjxy.mappers;

import cn.edu.jsu.rjxy.entity.vo.FillInQuestionnaire;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface FillInQuestionnaireMapper {

  @Select("SELECT * FROM Fill_in_questionnaire WHERE id=#{id}")
  FillInQuestionnaire getById(@Param("id") long id);

  @Select("SELECT * FROM Fill_in_questionnaire "
      + "WHERE questionnaire=#{questionnaire} "
      + " AND creater=#{creater} "
      + " AND creater_type=#{createrType}")
  FillInQuestionnaire getByQuestionnaireAndCreater(@Param("questionnaire") long questionnaire,
      @Param("creater") long creater,
      @Param("createrType") String createrType);

  @Insert("INSERT INTO Fill_in_questionnaire(questionnaire, result, creater, create_time, creater_type) "
      + " VALUE(#{questionnaire.questionnaire},"
      + "  #{questionnaire.result},"
      + "  #{questionnaire.creater},"
      + "  NOW(),"
      + "  #{questionnaire.createrType})")
  boolean insertFillInQuestionnaire(@Param("questionnaire") FillInQuestionnaire questionnaire);
}
