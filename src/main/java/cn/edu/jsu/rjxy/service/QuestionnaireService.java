package cn.edu.jsu.rjxy.service;

import cn.edu.jsu.rjxy.entity.dto.QuestionnaireBankItemDTO;
import cn.edu.jsu.rjxy.entity.vo.FillInQuestionnaire;
import cn.edu.jsu.rjxy.entity.vo.QuestionnaireBank;
import cn.edu.jsu.rjxy.entity.vo.QuestionnaireForTeacher;
import cn.edu.jsu.rjxy.entity.vo.QuestionnaireForTeacherQuestion;
import cn.edu.jsu.rjxy.entity.vo.QuestionnaireQuestionType;
import cn.edu.jsu.rjxy.entity.vo.QuestionnaireTemplate;
import cn.edu.jsu.rjxy.entity.vo.QuestionnaireTemplateQuestion;
import cn.edu.jsu.rjxy.mappers.QuestionnaireBankMapper;
import cn.edu.jsu.rjxy.mappers.QuestionnaireForTeacherMapper;
import cn.edu.jsu.rjxy.mappers.QuestionnaireForTeacherQuestionMapper;
import cn.edu.jsu.rjxy.mappers.QuestionnaireQuestionTypeMapper;
import cn.edu.jsu.rjxy.mappers.QuestionnaireTemplateMapper;
import cn.edu.jsu.rjxy.mappers.QuestionnaireTemplateQuestionMapper;
import cn.edu.jsu.rjxy.util.QueryConditionsUitl;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionnaireService {

  @Autowired
  QuestionnaireQuestionTypeMapper questionnaireQuestionTypeMapper;
  @Autowired
  QuestionnaireBankMapper questionnaireBankMapper;
  @Autowired
  QuestionnaireTemplateMapper questionnaireTemplateMapper;
  @Autowired
  QuestionnaireTemplateQuestionMapper questionnaireTemplateQuestionMapper;

  @Autowired
  QuestionnaireForTeacherMapper questionnaireForTeacherMapper;
  @Autowired
  QuestionnaireForTeacherQuestionMapper questionnaireForTeacherQuestionMapper;

  // QuestionnaireQuestionTypeMapper

  public QuestionnaireQuestionType getQuestionTypeById(long id) {
    return questionnaireQuestionTypeMapper.getById(id);
  }

  public QuestionnaireQuestionType getQuestionTypeByName(String name) {
    return questionnaireQuestionTypeMapper.getByName(name);
  }

  public List<QuestionnaireQuestionType> getAllQuestionType() {
    return questionnaireQuestionTypeMapper.getAllType();
  }

  public boolean addQuestionType(String name) {
    if (name == null || "".equals(name)) {
      return false;
    }
    if (questionnaireQuestionTypeMapper.getByName(name) != null) {
      return false;
    }
    String reg = "[\\u4e00-\\u9fa5]+";
    if (name.matches(reg)) {
      return questionnaireQuestionTypeMapper.addType(name);
    }
    return false;
  }

  // QuestionnaireBankMapper

  public QuestionnaireBank getQuestionById(long id) {
    return questionnaireBankMapper.getById(id);
  }

  public List<QuestionnaireBankItemDTO> getQuestionListByType(long typeId, Integer page,
      Integer size,
      String search) {
    Integer step = null;
    if (page != null && page > 0 && size != null && size > 0) {
      step = (page - 1) * size;
    }
    search = QueryConditionsUitl.constructQueryConditions(search);

    List<QuestionnaireBank> questionnaireBanks = questionnaireBankMapper
        .getPageByType(typeId, step, size, search, null);
    List<QuestionnaireBankItemDTO> questionnaireBankItems = new ArrayList<>(
        questionnaireBanks.size());
    for (QuestionnaireBank questionnaireBank : questionnaireBanks) {
      questionnaireBankItems.add(new QuestionnaireBankItemDTO(questionnaireBank));
    }
    return questionnaireBankItems;
  }

  public int getQuestionCountByType(long typeId, String search) {
    search = QueryConditionsUitl.constructQueryConditions(search);
    return questionnaireBankMapper.getCountByType(typeId, search, null);
  }

  public boolean addQuestionBank(QuestionnaireBankItemDTO bank) {
    if ("".equals(bank.getContentC()) || bank.getResultC() == null || bank.getResultC() == 0) {
      bank.setContentC(null);
      bank.setResultC(null);
    }
    if ("".equals(bank.getContentD()) || bank.getResultD() == null || bank.getResultD() == 0) {
      bank.setContentD(null);
      bank.setResultD(null);
    }
    return questionnaireBankMapper.insertBank(bank);
  }

  public boolean updateQuestionBank(QuestionnaireBankItemDTO bank) {
    if ("".equals(bank.getContentC()) || bank.getResultC() == null || bank.getResultC() == 0) {
      bank.setContentC(null);
      bank.setResultC(null);
    }
    if ("".equals(bank.getContentD()) || bank.getResultD() == null || bank.getResultD() == 0) {
      bank.setContentD(null);
      bank.setResultD(null);
    }
    return questionnaireBankMapper.updateBank(bank);
  }

  public boolean deleteQuestionBank(long id) {
    return questionnaireBankMapper.deleteBank(id);
  }

  // QuestionnaireTemplateMapper

  public QuestionnaireTemplate getTemplateById(long id) {
    return questionnaireTemplateMapper.getById(id);
  }

  public List<QuestionnaireTemplate> getTemplatePage(Integer page, Integer size, String search) {
    Integer step = null;
    if (page != null && page > 1 && size != null) {
      step = (page - 1) * size;
    }
    search = QueryConditionsUitl.constructQueryConditions(search);
    return questionnaireTemplateMapper.getPage(step, size, search);
  }

  public int getTemplateCount(String search) {
    search = QueryConditionsUitl.constructQueryConditions(search);
    return questionnaireTemplateMapper.getCount(search);
  }

  public boolean updateTemplateName(long templateId, String name, long updaterId) {
    if (name == null || "".equals(name)) {
      return false;
    }
    return questionnaireTemplateMapper.updateTemplateName(templateId, name, updaterId);
  }

  public boolean templateNameIsExist(String templateName) {
    if (questionnaireTemplateMapper.getByName(templateName) != null) {
      return true;
    } else {
      return false;
    }
  }

  public boolean addTemplate(String templateName, List<Long> questionIds, long creater) {
    QuestionnaireTemplate template = new QuestionnaireTemplate();
    template.setName(templateName);
    template.setCreater(creater);
    template.setUpdater(creater);
    if (questionnaireTemplateMapper.insertTemplate(template)) {
      template = questionnaireTemplateMapper.getByName(templateName);
      if (questionnaireTemplateQuestionMapper.addTemplateQuestions(template.getId(), questionIds)) {
        return true;
      }
    }
    return false;
  }

  // QuestionnaireTemplateQuestionMapper

  public QuestionnaireTemplateQuestion getTemplateQuestionById(long questionId) {
    return questionnaireTemplateQuestionMapper.getById(questionId);
  }

  public List<QuestionnaireTemplateQuestion> getTemplate(long templateId) {
    return questionnaireTemplateQuestionMapper.getQuestionnaire(templateId);
  }

  public List<QuestionnaireQuestionType> getTemplateQuestionType(long templateId) {
    List<QuestionnaireQuestionType> types = questionnaireQuestionTypeMapper.getAllType();
    List<QuestionnaireTemplateQuestion> questions = questionnaireTemplateQuestionMapper
        .getQuestionnaire(templateId);
    for (int i = 0; i < types.size(); i++) {
      boolean flag = false;
      for (QuestionnaireTemplateQuestion question : questions) {
        if (types.get(i).getId() == question.getQuestion().getType().getId()) {
          flag = true;
          break;
        }
      }
      if (!flag) {
        types.remove(types.get(i));
        i--;
      }
    }
    return types;
  }

  public boolean updateCoefficients(List<Long> questionIds, List<Double> coefficients) {
    Map<Long, Double> params = new HashMap<>();
    for (int i = 0; i < questionIds.size(); i++) {
      params.put(questionIds.get(i), coefficients.get(i));
    }
    return questionnaireTemplateQuestionMapper.updateQuestionCoefficients(params);
  }

  public boolean changeQuestion(long templateId, long oldQuestionId, long newQuestionId,
      long updater) throws Exception {
    if (questionnaireTemplateMapper.updateTemplateUpdater(templateId, updater)) {
      if (questionnaireTemplateQuestionMapper
          .updateQuestion(templateId, oldQuestionId, newQuestionId)) {
        return true;
      }
    }
    throw new Exception("error");
  }

  public boolean removeQuestion(long templateId, long questionId,
      long updater) throws Exception {
    if (questionnaireTemplateMapper.updateTemplateUpdater(templateId, updater)) {
      if (questionnaireTemplateQuestionMapper
          .deleteQuestion(templateId, questionId)) {
        return true;
      }
    }
    throw new Exception("error");
  }

  public boolean addQuestion(long templateId, long questionId, long updater) throws Exception {
    if (questionnaireTemplateMapper.updateTemplateUpdater(templateId, updater)) {
      if (questionnaireTemplateQuestionMapper.addQuestion(templateId, questionId)) {
        return true;
      }
    }
    throw new Exception("error");
  }

  public List<QuestionnaireBankItemDTO> getQuestionListByType(long typeId, Integer page,
      Integer size,
      long templateId) {
    Integer step = null;
    if (page != null && page > 0 && size != null && size > 0) {
      step = (page - 1) * size;
    }

    List<Long> exclusions = new ArrayList<>();
    List<QuestionnaireTemplateQuestion> questions = questionnaireTemplateQuestionMapper
        .getQuestionnaire(templateId);
    for (QuestionnaireTemplateQuestion question : questions) {
      exclusions.add(question.getQuestion().getId());
    }

    List<QuestionnaireBank> questionnaireBanks = questionnaireBankMapper
        .getPageByType(typeId, step, size, null, exclusions);
    List<QuestionnaireBankItemDTO> questionnaireBankItems = new ArrayList<>(
        questionnaireBanks.size());
    for (QuestionnaireBank questionnaireBank : questionnaireBanks) {
      questionnaireBankItems.add(new QuestionnaireBankItemDTO(questionnaireBank));
    }
    return questionnaireBankItems;
  }

  public int getQuestionCountByType(long typeId, long templateId) {

    List<Long> exclusions = new ArrayList<>();
    List<QuestionnaireTemplateQuestion> questions = questionnaireTemplateQuestionMapper
        .getQuestionnaire(templateId);
    for (QuestionnaireTemplateQuestion question : questions) {
      exclusions.add(question.getQuestion().getId());
    }
    return questionnaireBankMapper.getCountByType(typeId, null, exclusions);
  }

  //QuestionnaireForTeacherMapper && QuestionnaireForTeacherQuestionMapper
  public QuestionnaireForTeacher teacherQuestionnaireIsExist(long scoreForTeacherId) {
    return questionnaireForTeacherMapper.getTeacherQuestionnaire(scoreForTeacherId);
  }

  public boolean createTeacherQuestionnaire(long scoreForTeacherId,
      long templateId,
      long creater,
      Date endTime) {
    QuestionnaireForTeacher questionnaireForTeacher =
        new QuestionnaireForTeacher(scoreForTeacherId, creater, new Date(), new Date(), endTime);
    if (questionnaireForTeacherMapper.insertQuestionnaireForTeacher(questionnaireForTeacher)) {
      List<QuestionnaireTemplateQuestion> templateQuestions =
          questionnaireTemplateQuestionMapper.getQuestionnaire(templateId);
      List<Long> questionIds = new ArrayList<>();
      for (QuestionnaireTemplateQuestion question : templateQuestions) {
        questionIds.add(question.getQuestion().getId());
      }
      if (questionnaireForTeacherQuestionMapper
          .insertQuestionnaireForTeacherQuestion(templateId, questionIds)) {
        return true;
      }
    }
    return false;
  }

  public List<QuestionnaireForTeacherQuestion> getQuestionnaireQuestions(long questionnaireId) {
    return questionnaireForTeacherQuestionMapper.getByQuestionnaireId(questionnaireId);
  }

  public List<QuestionnaireQuestionType> getQuestionnaireQuestionType(long questionnaireId) {
    List<QuestionnaireQuestionType> types = questionnaireQuestionTypeMapper.getAllType();
    List<QuestionnaireForTeacherQuestion> questions = questionnaireForTeacherQuestionMapper
        .getByQuestionnaireId(questionnaireId);
    for (int i = 0; i < types.size(); i++) {
      boolean flag = false;
      for (QuestionnaireForTeacherQuestion question : questions) {
        if (types.get(i).getId() == question.getQuestion().getType().getId()) {
          flag = true;
          break;
        }
      }
      if (!flag) {
        types.remove(types.get(i));
        i--;
      }
    }
    return types;
  }

  public double getQuestionnaireResult(long questionnaireId,
      List<Long> questionIds,
      List<Double> results) {

    List<QuestionnaireForTeacherQuestion> questions = questionnaireForTeacherQuestionMapper
        .getByQuestionnaireId(questionnaireId);

    double count_result = 0;
    double count_questionnaire = 0;
    for (Long id : questionIds) {
      for (QuestionnaireForTeacherQuestion question : questions) {
        if (id == question.getQuestion().getId()) {
          QuestionnaireBank bank = question.getQuestion();
          double max = Math
              .max(Math.max(Math.max(bank.getResultA(), bank.getResultB()), bank.getResultC()),
                  bank.getResultD());
          count_questionnaire += max * question.getCoefficient();
          count_result += results.get(questionIds.indexOf(id)) * question.getCoefficient();
          break;
        }
      }
    }
    return count_result / count_questionnaire * 100;
  }
}
