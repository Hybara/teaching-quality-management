package cn.edu.jsu.rjxy.service;

import cn.edu.jsu.rjxy.entity.vo.Admin;
import cn.edu.jsu.rjxy.entity.vo.Teacher;
import cn.edu.jsu.rjxy.mappers.AdminMapper;
import cn.edu.jsu.rjxy.mappers.TeacherMapper;
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

  public boolean updateTeacher(Teacher teacher) {
    if (teacher.getHeader()==null || "".equals(teacher.getHeader())) {
      teacher.setHeader("/img/header.jpg");
    }
    return teacherMapper.updateTeacher(teacher);
  }

}
