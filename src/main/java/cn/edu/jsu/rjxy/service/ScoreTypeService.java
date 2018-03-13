package cn.edu.jsu.rjxy.service;

import cn.edu.jsu.rjxy.entity.vo.ScoreType;
import cn.edu.jsu.rjxy.mappers.ScoreTypeMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ScoreTypeService {

  @Autowired
  ScoreTypeMapper scoreTypeMapper;

  public List<ScoreType> getAll() {
    return scoreTypeMapper.getAll();
  }
}
