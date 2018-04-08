package cn.edu.jsu.rjxy.service;

import cn.edu.jsu.rjxy.entity.dto.QuestionnaireBankItemDTO;
import cn.edu.jsu.rjxy.entity.vo.QuestionnaireBank;
import cn.edu.jsu.rjxy.entity.vo.QuestionnaireQuestionType;
import cn.edu.jsu.rjxy.entity.vo.QuestionnaireTemplate;
import cn.edu.jsu.rjxy.mappers.QuestionnaireBankMapper;
import cn.edu.jsu.rjxy.mappers.QuestionnaireQuestionTypeMapper;
import cn.edu.jsu.rjxy.mappers.QuestionnaireTemplateMapper;
import cn.edu.jsu.rjxy.util.QueryConditionsUitl;
import java.util.ArrayList;
import java.util.List;
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

  //QuestionnaireQuestionTypeMapper

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

  public List<QuestionnaireBankItemDTO> getQuestionListByType(long typeId, Integer page, Integer size,
      String search) {
    Integer step = null;
    if (page != null && page > 0 && size != null && size > 0) {
      step = (page - 1) * size;
    }
    search = QueryConditionsUitl.constructQueryConditions(search);

    List<QuestionnaireBank> questionnaireBanks = questionnaireBankMapper.getPageByType(typeId, step, size, search);
    List<QuestionnaireBankItemDTO> questionnaireBankItems = new ArrayList<>(questionnaireBanks.size());
    for (QuestionnaireBank questionnaireBank: questionnaireBanks) {
      questionnaireBankItems.add(new QuestionnaireBankItemDTO(questionnaireBank));
    }
    return questionnaireBankItems;
  }

  public int getQuestionCountByType(long typeId, String search) {
    search = QueryConditionsUitl.constructQueryConditions(search);
    return questionnaireBankMapper.getCountByType(typeId, search);
  }

  public boolean addQuestionBank(QuestionnaireBankItemDTO bank) {
    if ("".equals(bank.getContentC()) || bank.getResultC()==null || bank.getResultC()==0) {
      bank.setContentC(null);
      bank.setResultC(null);
    }
    if ("".equals(bank.getContentD()) || bank.getResultD()==null || bank.getResultD()==0) {
      bank.setContentD(null);
      bank.setResultD(null);
    }
    return questionnaireBankMapper.insertBank(bank);
  }

  public boolean updateQuestionBank(QuestionnaireBankItemDTO bank) {
    if ("".equals(bank.getContentC()) || bank.getResultC()==null || bank.getResultC()==0) {
      bank.setContentC(null);
      bank.setResultC(null);
    }
    if ("".equals(bank.getContentD()) || bank.getResultD()==null || bank.getResultD()==0) {
      bank.setContentD(null);
      bank.setResultD(null);
    }
    return questionnaireBankMapper.updateBank(bank);
  }

  public boolean deleteQuestionBank(long id) {
    return questionnaireBankMapper.deleteBank(id);
  }

  //QuestionnaireTemplateMapper

  public QuestionnaireTemplate getTemplateById(long id) {
    return questionnaireTemplateMapper.getById(id);
  }

  public List<QuestionnaireTemplate> getTemplatePage(Integer page, Integer size, String search) {
    Integer step = null;
    if (page!=null && page>1 && size!=null) {
      step = (page-1)*size;
    }
    search = QueryConditionsUitl.constructQueryConditions(search);
    return questionnaireTemplateMapper.getPage(step, size, search);
  }

  public int getTemplateCount(String search) {
    search = QueryConditionsUitl.constructQueryConditions(search);
    return questionnaireTemplateMapper.getCount(search);
  }

}
