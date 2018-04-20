package cn.edu.jsu.rjxy.mappers;

import cn.edu.jsu.rjxy.entity.vo.Report;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
@Mapper
public interface ReportMapper {

  @Select("SELECT * FROM report WHERE id=#{id}")
  Report getById(@Param("id") long id);

  @Select("SELECT * FROM report WHERE `handler` IS NULL ORDER BY create_time LIMIT #{step},#{size}")
  List<Report> getNoHandlerReports(@Param("step") Integer step, @Param("size") Integer size);

  @Select("SELECT COUNT(*) FROM report WHERE `handler` IS NULL")
  int getNoHandlerReportsCount();

  @Insert("INSERT INTO report(reported_id, reported_type, reason, creater, create_time)"
      + " VALUE(#{report.reportedId},"
      + "  #{report.reportedType},"
      + "  #{report.reason},"
      + "  #{report.creater},"
      + "  NOW())")
  boolean insertReport(@Param("report") Report report);

  @Update("UPDATE report "
      + " SET `handler`=#{creater}, handle_time=NOW()"
      + "WHERE id=#{id}")
  boolean updateReportHandler(@Param("id") long id, @Param("creater") long creater);

  @Delete("DELETE FROM report WHERE id=#{id}")
  boolean deleteReport(@Param("id") long id);


}
