package cn.edu.jsu.rjxy.mappers;

import cn.edu.jsu.rjxy.entity.vo.Report;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ReportMapper {


  @Insert("INSERT INTO report(reported_id, reported_type, reason, creater, create_time)"
      + " VALUE(#{report.reportedId},"
      + "  #{report.reportedType},"
      + "  #{report.reason},"
      + "  #{report.creater},"
      + "  NOW())")
  boolean insertReport(@Param("report") Report report);
}
