package cn.edu.jsu.rjxy.service;

import cn.edu.jsu.rjxy.entity.vo.Admin;
import cn.edu.jsu.rjxy.entity.vo.Student;
import cn.edu.jsu.rjxy.mappers.AdminMapper;
import cn.edu.jsu.rjxy.mappers.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentService {

  @Autowired
  private StudentMapper studentMapper;

  public Student getLoginer(String account, String password) {
    return studentMapper.getByNumberAndPassword(account, password);
  }

}
