package cn.edu.jsu.rjxy.service;

import cn.edu.jsu.rjxy.entity.vo.Report;
import cn.edu.jsu.rjxy.mappers.ReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

  @Autowired
  ReportMapper reportMapper;

  public boolean addReport(long reportedId, String reportedType, long creater, String reason) {
    return reportMapper.insertReport(new Report(reportedId, reportedType, reason, creater, null));
  }
}
