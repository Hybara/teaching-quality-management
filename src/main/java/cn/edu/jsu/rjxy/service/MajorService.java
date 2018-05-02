package cn.edu.jsu.rjxy.service;

import cn.edu.jsu.rjxy.entity.vo.Major;
import cn.edu.jsu.rjxy.mappers.MajorMapper;
import cn.edu.jsu.rjxy.util.QueryConditionsUitl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MajorService {

  @Autowired
  private MajorMapper majorMapper;

  public Major getById(long majorId) {
    return majorMapper.getById(majorId);
  }

  public List<Major> getMajorList(Integer page, Integer size, String search) {
    Integer step = null;
    if (page != null && page >= 1 && size != null) {
      step = (page - 1) * size;
    }
    search = QueryConditionsUitl.constructQueryConditions(search);
    return majorMapper.getPage(step, size, search);
  }

  public int getMajorCount(String search) {
    search = QueryConditionsUitl.constructQueryConditions(search);
    return majorMapper.getCount(search);
  }

  public boolean deleteMajor(long majorId) {
    return majorMapper.deleteMajor(majorId);
  }

  public boolean insertMajor(Major major) {
    return majorMapper.insertMajor(major);
  }

  public boolean updateMajor(Major major) {
    return majorMapper.updateMajor(major);
  }
}
