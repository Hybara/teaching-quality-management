package cn.edu.jsu.rjxy.service;

import cn.edu.jsu.rjxy.entity.dto.ReportDTO;
import cn.edu.jsu.rjxy.entity.vo.Evaluate;
import cn.edu.jsu.rjxy.entity.vo.ForQuestion;
import cn.edu.jsu.rjxy.entity.vo.Question;
import cn.edu.jsu.rjxy.entity.vo.Report;
import cn.edu.jsu.rjxy.entity.vo.ScoreForTeacher;
import cn.edu.jsu.rjxy.mappers.EvaluateMapper;
import cn.edu.jsu.rjxy.mappers.ForQuestionMapper;
import cn.edu.jsu.rjxy.mappers.QuestionMapper;
import cn.edu.jsu.rjxy.mappers.ReportMapper;
import cn.edu.jsu.rjxy.mappers.ScoreForTeacherMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

  @Autowired
  ReportMapper reportMapper;
  @Autowired
  EvaluateMapper evaluateMapper;
  @Autowired
  QuestionMapper questionMapper;
  @Autowired
  ForQuestionMapper forQuestionMapper;
  @Autowired
  ScoreForTeacherMapper scoreForTeacherMapper;

  private static final String REPORTED_TITLE = "";
  private static final String REPORTED_TEXT = "内容被折叠";

  public List<ReportDTO> getNoHandlerReportPage(int page, int size) {
    int step = 0;
    if (page > 1) {
      step = (page - 1) * size;
    }
    List<Report> reports = reportMapper.getNoHandlerReports(step, size);
    List<ReportDTO> reportDTOS = new ArrayList<>(reports.size());
    for (Report report: reports) {
      if ("evaluate".equals(report.getReportedType())) {
        reportDTOS.add(new ReportDTO(report, evaluateMapper.getById(report.getReportedId())));
      } else if ("question".equals(report.getReportedType())) {
        reportDTOS.add(new ReportDTO(report, questionMapper.getById(report.getReportedId())));
      } else if ("forQuestion".equals(report.getReportedType())) {
        reportDTOS.add(new ReportDTO(report, forQuestionMapper.getById(report.getReportedId())));
      }
    }
    return reportDTOS;
  }

  public int getNoHandlerReportCount() {
    return reportMapper.getNoHandlerReportsCount();
  }

  public boolean addReport(long reportedId, String reportedType, long creater, String reason) {
    return reportMapper.insertReport(new Report(reportedId, reportedType, reason, creater, null));
  }

  public boolean notAllowed(long reportId) {
    return reportMapper.deleteReport(reportId);
  }

  public boolean allow(long reportId, long creater) {
    Report report = reportMapper.getById(reportId);

    boolean flag = false;
    if ("evaluate".equals(report.getReportedType())) {
      Evaluate evaluate = evaluateMapper.getById(report.getReportedId());
      ScoreForTeacher score = evaluate.getScoreForTeacher();
      score.setEvaluateCount(score.getEvaluateCount()-1);
      score.setEvaluateGrade(score.getEvaluateGrade()-evaluate.getResult());
      evaluate.setResult(0);
      evaluate.setTitle(REPORTED_TITLE);
      evaluate.setText(REPORTED_TEXT);
      flag = scoreForTeacherMapper.updateScoreForTeacher(score) && evaluateMapper.updateScoreEvaluate(evaluate);
    } else if ("question".equals(report.getReportedType())) {
      Question question = questionMapper.getById(report.getReportedId());
      ScoreForTeacher score = question.getScoreForTeacher();
      score.setQuestionCount(score.getQuestionCount()-1);
      score.setQuestionGrade(score.getQuestionGrade()-question.getResult());
      question.setResult(0.0);
      question.setTitle(REPORTED_TITLE);
      question.setText(REPORTED_TEXT);
      flag =  scoreForTeacherMapper.updateScoreForTeacher(score) && questionMapper.updateScoreQuestion(question);
    } else if ("forQuestion".equals(report.getReportedType())) {
      ForQuestion forQuestion = forQuestionMapper.getById(report.getReportedId());
      forQuestion.setText(REPORTED_TEXT);
      flag = forQuestionMapper.updateForQuestionText(forQuestion);
    }
    if (flag) {
      if (reportMapper.updateReportHandler(reportId, creater)) {
        return true;
      }
    }
    return false;
  }
}
