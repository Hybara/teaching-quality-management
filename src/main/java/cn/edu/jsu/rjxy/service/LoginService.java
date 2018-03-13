package cn.edu.jsu.rjxy.service;

import cn.edu.jsu.rjxy.mappers.AdminMapper;
import cn.edu.jsu.rjxy.mappers.RegisterMapper;
import cn.edu.jsu.rjxy.mappers.StudentMapper;
import cn.edu.jsu.rjxy.mappers.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class LoginService {


  @Autowired
  private AdminMapper adminMapper;
  @Autowired
  private RegisterMapper registerMapper;
  @Autowired
  private TeacherMapper teacherMapper;
  @Autowired
  private StudentMapper studentMapper;

  public boolean login(String account, String password, String type) {
    if ("student".equals(type)) {
      if (studentMapper.getByNumberAndPassword(account, password)!=null) {
        return true;
      }
    }
    if ("teacher".equals(type)) {
      if (teacherMapper.getByNumberAndPassword(account, password)!=null) {
        return true;
      }
    }
    if ("register".equals(type)) {
      if (registerMapper.getByNumberAndPassword(account, password)!=null) {
        return true;
      }
    }
    if ("admin".equals(type)) {
      if (adminMapper.getByAccountAndPassword(account, password)!=null) {
        return true;
      }
    }
    return false;
  }


}
