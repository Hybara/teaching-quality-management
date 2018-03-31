package cn.edu.jsu.rjxy.service;

import cn.edu.jsu.rjxy.entity.vo.Admin;
import cn.edu.jsu.rjxy.entity.vo.Teacher;
import cn.edu.jsu.rjxy.mappers.AdminMapper;
import cn.edu.jsu.rjxy.mappers.TeacherMapper;
import cn.edu.jsu.rjxy.util.QueryConditionsUitl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TeacherService {

  @Autowired
  private TeacherMapper teacherMapper;

  public Teacher getById(long teacherId) {
    return teacherMapper.getById(teacherId);
  }

  public Teacher getLoginer(String account, String password) {
    return teacherMapper.getByNumberAndPassword(account, password);
  }

  public List<Teacher> getTeacherListByMajor(long major, Integer page, Integer size,
      String search) {
    Integer step = null;
    if (page != null && page >= 1 && size != null) {
      step = (page - 1) * size;
    }
    return teacherMapper
        .getListByMajor(major, step, size, QueryConditionsUitl.constructQueryConditions(search));
  }
  public int getTeacherCountByMajor(long major, String search) {
    return teacherMapper.getCountByMajor(major, QueryConditionsUitl.constructQueryConditions(search));
  }

  public boolean updateTeacher(Teacher teacher) {
    if (teacher.getHeader() == null || "".equals(teacher.getHeader())) {
      teacher.setHeader("/img/header.jpg");
    }
    return teacherMapper.updateTeacher(teacher);
  }

}
