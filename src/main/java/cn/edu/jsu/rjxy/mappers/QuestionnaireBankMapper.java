package cn.edu.jsu.rjxy.mappers;

import cn.edu.jsu.rjxy.entity.dto.QuestionnaireBankItemDTO;
import cn.edu.jsu.rjxy.entity.vo.QuestionnaireBank;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
      @Param("search") String search,
      @Param("exclusions") List<Long> exclusions);

  int getCountByType(@Param("type") long typeId, @Param("search") String search,
      @Param("exclusions") List<Long> exclusions);

  @Insert("INSERT INTO questionnaire_bank(title, remarks, type, "
      + "content_A, result_A, content_B, result_B, content_C, result_C, content_D, result_D) "
      + "VALUE(#{bank.title},"
      + "#{bank.remarks},"
      + "#{bank.typeId},"
      + "#{bank.contentA},"
      + "#{bank.resultA},"
      + "#{bank.contentB},"
      + "#{bank.resultB},"
      + "#{bank.contentC},"
      + "#{bank.resultC},"
      + "#{bank.contentD},"
      + "#{bank.resultD})")
  boolean insertBank(@Param("bank") QuestionnaireBankItemDTO bank);

  @Update("UPDATE questionnaire_bank "
      + "SET type=#{bank.typeId},"
      + " title=#{bank.title},"
      + " remarks=#{bank.remarks},"
      + " content_A=#{bank.contentA},"
      + " result_A=#{bank.resultA},"
      + " content_B=#{bank.contentB},"
      + " result_B=#{bank.resultB},"
      + " content_C=#{bank.contentC},"
      + " result_C=#{bank.resultC},"
      + " content_D=#{bank.contentD},"
      + " result_D=#{bank.resultD} "
      + "WHERE id=#{bank.id}")
  boolean updateBank(@Param("bank") QuestionnaireBankItemDTO bank);

  @Delete("DELETE FROM questionnaire_bank WHERE id=#{id}")
  boolean deleteBank(@Param("id") long id);
}
